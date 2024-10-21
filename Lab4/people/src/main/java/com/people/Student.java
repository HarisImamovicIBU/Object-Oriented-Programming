package com.people;
import java.util.ArrayList;
import java.util.List;
public class Student extends Person {
    private int credits;
    private int studentId;
    private List<Integer> grades=new ArrayList<>();
    public Student(String name, String address) {
        super(name, address);
        this.credits = 0;
    }
    public int getStudentId(){
        return this.studentId;
    }
    public void setStudentId(int studentId){
        this.studentId=studentId;
    }
    public List<Integer> getGrades(){
        return this.grades;
    }
    public void addGrade(int gradeToAdd){
        this.grades.add(gradeToAdd);
    }
    public void study() {
        this.credits++;
    }
    public int credits() {
        return this.credits;
    }
    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
        Student olli = new Student("Olli", "Ida Albergintie Street 1 00400 Helsinki");
        System.out.println(olli);
        System.out.println("credits " + olli.credits());
        olli.study();
        System.out.println("credits " + olli.credits());
        //Testing the addGrade and getGrades from last task.
        olli.addGrade(3);
        olli.addGrade(5);
        olli.addGrade(4);
        System.out.println("Ollis grades are: "+olli.getGrades());
    }
}
