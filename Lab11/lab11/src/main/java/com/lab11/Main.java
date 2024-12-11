package com.lab11;

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