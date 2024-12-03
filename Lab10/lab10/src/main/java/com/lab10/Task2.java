package com.lab10;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

class Student{
    private String name;
    private int id;
    private List<Integer> listOfGrades;
    public Student(int id, List<Integer> listOfGrades, String name) {
        this.id = id;
        this.listOfGrades = listOfGrades;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<Integer> getListOfGrades() {
        return listOfGrades;
    }
    public void setListOfGrades(List<Integer> listOfGrades) {
        this.listOfGrades = listOfGrades;
    }
    public String printInfo(){
        return "Name: "+this.name+", Grades: "+this.listOfGrades+", Id: "+this.id;
    }
}
class GradeAnalyzer{
    private List<Integer> listOfGrades;
    public GradeAnalyzer(List<Integer> listOfGrades){
        this.listOfGrades=listOfGrades;
    }
    public double calculateAverage(){
        int counter=0;
        int sum=0;
        for(Integer grade : listOfGrades){
            sum+=grade;
            counter++;
        }
        return sum/counter;
    }
    public void printSummary(){
        System.out.println("Grades: ");
        for(Integer grade : listOfGrades){
            System.out.println(grade);
        }
    }
}
class Task2 {
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Student student1 = new Student(1, Arrays.asList(6,7,6,10,10,9), "Ime");
        GradeAnalyzer ga = new GradeAnalyzer(student1.getListOfGrades());
        System.out.println("Fields for Student class: ");
        for(Field field : student1.getClass().getDeclaredFields()){
            field.setAccessible(true);
            System.out.println(field.getName() + ": " + field.get(student1));
        }
        System.out.println("---------------\nFields for GradeAnalyzer class: ");
        for(Field field : ga.getClass().getDeclaredFields()){
            field.setAccessible(true);
            System.out.println(field.getName() + ": " + field.get(ga));
        }
        System.out.println("---------------\nMethods for Student class: ");
        for(Method method : student1.getClass().getDeclaredMethods()){
            if(method.getName().startsWith("calculate") || method.getName().startsWith("print")){
                method.setAccessible(true);
                if(method.getReturnType()!=void.class){
                    System.out.println(method.getName()+"(): "+method.invoke(student1));
                } 
                else{
                    //There are no void methods in our Student class, so this will never be executed
                    method.invoke(student1);
                    System.out.println(method.getName()+"() Method invoked.");
                }
            }
        }
        System.out.println("--------------\nMethods for GradeAnalyzer class: ");
        for(Method method : ga.getClass().getDeclaredMethods()){
            if(method.getName().startsWith("calculate") || method.getName().startsWith("print")){
                method.setAccessible(true);
                if(method.getReturnType()!=void.class){
                    System.out.println(method.getName()+"(): "+method.invoke(ga));
                }
                else{
                    method.invoke(ga);
                    System.out.println(method.getName()+"() Method invoked.");
                }
        }
        }
    }
}
