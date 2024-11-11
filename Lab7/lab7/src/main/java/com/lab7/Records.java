package com.lab7;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
public class Records {
    
}
record EmployeeRecord(String name, String position, double salary, Date employmentDate){
    public static EmployeeRecord createIntern(String name){
        return new EmployeeRecord(name, "Intern",30000, new Date());
    }
    public static List<EmployeeRecord> filterEmployeesOverFiveYears(List<EmployeeRecord> employees) {
        List<EmployeeRecord> result=new ArrayList<>();
        Date fiveYearsAgo=new Date(System.currentTimeMillis()-(5L*365*24*60*60*1000));
        for (EmployeeRecord emp : employees) {
            if (emp.employmentDate().before(fiveYearsAgo)) {
                result.add(emp);
            }
        }
        return result;
    }
    public static double calculateAverageSalary(List<EmployeeRecord> employees, String department){
        double totalSalary=0;
        int counter=0;
        for (EmployeeRecord emp : employees) {
            if (emp.position().equals(department)) {
                totalSalary += emp.salary();
                counter++;
            }
        }
        if(counter>0){
            return totalSalary/counter;
        }
        return 0.0;
    }
    public static void printEmployeesInThisRole(List<EmployeeRecord> employees, String role){
        for(EmployeeRecord emp : employees){
            if(emp.position().equals(role)){
                System.out.println(emp.name);
            }
        }
    }
    public static void main(String[] args) {
        List<EmployeeRecord> employees=new ArrayList<>(
            Arrays.asList(new EmployeeRecord("Haris", "Senior Developer", 80000.00, new Date()),
            new EmployeeRecord("Rocky", "Junior Developer", 50000.00, new Date()))
        );
        employees.add(EmployeeRecord.createIntern("Apollo"));        
        System.out.println("Employees with more than 5 years of experience:");
        List<EmployeeRecord> overFiveYears=filterEmployeesOverFiveYears(employees);
        for(EmployeeRecord emp : overFiveYears){
            System.out.println(emp);
        }
        System.out.println("Average salary for Senior Developers: "+calculateAverageSalary(employees, "Senior Developer"));
        System.out.println("Senior Developers:");
        printEmployeesInThisRole(employees, "Senior Developer");
    }
}