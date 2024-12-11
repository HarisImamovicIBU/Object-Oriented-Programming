package com.project2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

record Employee(int id, String name, String department, int salary){}
class EmployeeNotFoundException extends Exception{
    public EmployeeNotFoundException(String message){
        super(message);
    }
}
class EmptyEmployeeListException extends RuntimeException{
    public EmptyEmployeeListException(String message){
        super(message);
    }
}
class InvalidEmployeeDataException extends Exception{
    public InvalidEmployeeDataException(String message){
        super(message);
    }
}
class EmployeeSystem {
    private List<Employee> employees;
    public EmployeeSystem(List<Employee> employees) throws InvalidEmployeeDataException{
        this.employees=employees;
        validateEmployeeData(employees);
    }
    public EmployeeSystem(String filename) throws IOException{
        try {
            this.employees=readEmployees(filename);
        } catch (IOException e) {
            throw new IOException();
        }
    }
    public static List<Employee> readEmployees(String filename) throws IOException{
        List<Employee> newList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<String> lines = reader.lines().collect(Collectors.toList());
        for(String line : lines){
            String[] parts = line.split(",");
            Employee e = new Employee(
                parts[0]!="" ? Integer.parseInt(parts[0]) : 0,
                parts[1],
                parts[2],
                parts[3]!="" ? Integer.parseInt(parts[0]) : 0
            );
            newList.add(e);
        }
        reader.close();
        return newList;
    }
    public int noOfEmployees(){
        int counter=0;
        for(Employee e : this.employees){
            counter++;
        }
        return counter;
    }
    public Optional<Employee> getEmployeeById(int id){
        for(Employee e : this.employees){
            if(id==e.id()){
                return Optional.of(e);
            }
        }
        return Optional.ofNullable(null);
    }
    public Employee getEmployeeWithHighestSalary(){
        if(this.employees.isEmpty()){
            throw new EmptyEmployeeListException("No employees found in the system.");
        }
        Employee employeeWithHighestSalary = this.employees.get(0);
        for(Employee e : this.employees){
            if(employeeWithHighestSalary.salary()<e.salary()){
                employeeWithHighestSalary=e;
            }
        }
        return employeeWithHighestSalary;
    }
    public List<Employee> getEmployeeFromDepartment(String department){
        if(this.employees.isEmpty()){
            throw new EmptyEmployeeListException("No employees found in the system.");
        }
        List<Employee> newList = new ArrayList<>();
        for(Employee e : this.employees){
            if(e.department().equals(department)){
                newList.add(e);
            }
        }
        return newList;
    }
    public Employee getLongestNameEmployee(){
        if(this.employees.isEmpty()){
            throw new EmptyEmployeeListException("No employees found in the system.");
        }
        Employee longestNameEmployee = this.employees.get(0);
        for(Employee e : this.employees){
            if(e.name().length()>longestNameEmployee.name().length()){
                longestNameEmployee=e;
            }
        }
        return longestNameEmployee;
    }
    private void validateEmployeeData(List<Employee> employees) throws InvalidEmployeeDataException{
        for(Employee e : employees){
            if(e.salary()<=0){
                throw new InvalidEmployeeDataException("Employee data contains invalid entries.");
            }
        }
    }
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}