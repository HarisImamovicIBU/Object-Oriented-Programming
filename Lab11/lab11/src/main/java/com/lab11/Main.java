package com.lab11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws InvalidStudentDataException{
        StudentSystem system = new StudentSystem("C:\\xampp\\htdocs\\Object-Oriented-Programming\\Lab11\\lab11\\src\\main\\java\\com\\lab11\\students.csv");
        //Testing the methods
        for(Student student : system.getListOfStudents()){
            System.out.println(student);
            System.out.println("-----------\n");
        }
        System.out.println("Total number of students: "+system.noOfStudents());
        System.out.println("Student with the longest name: "+system.getLongestNameStudent().getName());
        System.out.println("Student with id 17: "+system.getStudentById(17));
    }
}
class Student{
    private int id;
    private String name;
    private String uni;
    private String department;
    private double gpa;

    public Student(int id, String name, String uni, String department, double gpa){
        this.id=id;
        this.name=name;
        this.uni=uni;
        this.department=department;
        this.gpa=gpa;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUni() {
        return uni;
    }
    public void setUni(String uni) {
        this.uni = uni;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    @Override
    public String toString(){
        return "Name: "+this.name+"\nID: "+this.id+"\nUniversity: "+this.uni+"\nDepartment: "+this.department+"\nGPA: "+this.gpa;
    }
}
class EmptyStudentListException extends RuntimeException{
    public EmptyStudentListException(String message){
        super(message);
    }
}
class InvalidStudentDataException extends Exception{
    public InvalidStudentDataException(String message){
        super(message);
    }
}
class StudentSystem{
    private List<Student> listOfStudents;
    //Constructor 1
    public StudentSystem(String filename) throws InvalidStudentDataException{
        try {
            this.listOfStudents = readStudents(filename);            
            validateStudentData(listOfStudents);

        } catch (IOException e) {
            this.listOfStudents = new ArrayList<>();
            throw new RuntimeException(e);
        }
    }
    //Constructor 2
    public StudentSystem(List<Student> listOfStudents){
        this.listOfStudents=listOfStudents;
    }
    public static List<Student> readStudents(String filename) throws IOException{
        List<Student> students = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<String> lines = reader.lines().collect(Collectors.toList());
        for(String line : lines){
            String[] parts = line.split(",");
            Student studentToAdd = new Student(
                parts[0]!="" ? Integer.parseInt(parts[0]) : 0,
                parts[1],
                parts[2],
                parts[3],
                parts[4]!="" ? Double.parseDouble(parts[4]) : 0.0
            );
            students.add(studentToAdd);
        }
        reader.close();
        return students;
    }
    public int noOfStudents(){
        if (this.listOfStudents.isEmpty()){
            throw new EmptyStudentListException("List of students is empty.");
        }
        int numberOfStudents=0;
        for(Student student : this.listOfStudents){
            numberOfStudents++;
        }
        return numberOfStudents;
    }
    public Optional<Student> getStudentById(int parameterId){
        if (this.listOfStudents.isEmpty()){
            throw new EmptyStudentListException("List of students is empty.");
        }
        for(Student student : this.listOfStudents){
            if(student.getId()==parameterId){
                return Optional.of(student);
            }
        }
            return Optional.ofNullable(null);
    }
    public double getHighestGPAStudent(){
        if (this.listOfStudents.isEmpty()){
            throw new EmptyStudentListException("List of students is empty.");
        }
        double gpa=0;
        for (Student student : this.listOfStudents){
            if(student.getGpa()>gpa){
                gpa=student.getGpa();
            }
        }
        return gpa;
    }
    public Student getLongestNameStudent(){
        if (this.listOfStudents.isEmpty()){
            throw new EmptyStudentListException("List of students is empty.");
        }
        Student longestNameStudent = this.listOfStudents.get(0);
        for(Student student : this.listOfStudents){
            if(student.getName().length()>longestNameStudent.getName().length()){
                longestNameStudent=student;
            }
        }
        return longestNameStudent;
    }
    private void validateStudentData(List<Student> listOfStudents) throws InvalidStudentDataException{
        for(Student student : listOfStudents){
            if(student.getGpa()<6 || student.getGpa()>10){
                throw new InvalidStudentDataException("Read data has invalid rows");
            }
        }
    }
    public List<Student> getListOfStudents() {
        return listOfStudents;
    }
    public void setListOfStudents(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }
}
