package com.lab11;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
public class Tests {
    @Test
    public void testifStudentisPresent() throws InvalidStudentDataException{
        StudentSystem studentSystem = new StudentSystem("C:\\\\xampp\\\\htdocs\\\\Object-Oriented-Programming\\\\Lab11\\\\lab11\\\\src\\\\main\\\\java\\\\com\\\\lab11\\\\students.csv");
        assertFalse(studentSystem.getListOfStudents().isEmpty(), "There should be at least one student in the file.");
    }
    @Test
    void testStudentWithId100() throws InvalidStudentDataException{
        StudentSystem studentSystem = new StudentSystem("C:\\\\xampp\\\\htdocs\\\\Object-Oriented-Programming\\\\Lab11\\\\lab11\\\\src\\\\main\\\\java\\\\com\\\\lab11\\\\students.csv");
        assertTrue(studentSystem.getStudentById(100).isEmpty(), "Student with ID 100 should not exist.");
    }

    @Test
    void testHighestGPAStudent() throws InvalidStudentDataException{
        StudentSystem studentSystem = new StudentSystem("C:\\\\xampp\\\\htdocs\\\\Object-Oriented-Programming\\\\Lab11\\\\lab11\\\\src\\\\main\\\\java\\\\com\\\\lab11\\\\students.csv");
        assertEquals(9.8, studentSystem.getHighestGPAStudent().getGpa(), "The highest GPA should be 9.8.");
    }
    
    @Test
    void testExceptionForEmptyStudentList() throws InvalidStudentDataException{
        StudentSystem studentSystem = new StudentSystem(new ArrayList<>());
        assertThrows(EmptyStudentListException.class, studentSystem::getHighestGPAStudent, "Exception thrown for empty list.");
    }

    @Test
    void testExceptionMessageForEmptyStudentList() throws InvalidStudentDataException {
        StudentSystem emptySystem = new StudentSystem(new ArrayList<>());
        EmptyStudentListException exception = assertThrows(EmptyStudentListException.class, emptySystem::getHighestGPAStudent);
        assertEquals("List of students is empty.", exception.getMessage(), "Exception message should match expected.");
    }

    @Test
    void testNamesArray() throws IOException {
        List<Student> students = StudentSystem.readStudents("C:\\\\\\\\xampp\\\\\\\\htdocs\\\\\\\\Object-Oriented-Programming\\\\\\\\Lab11\\\\\\\\lab11\\\\\\\\src\\\\\\\\main\\\\\\\\java\\\\\\\\com\\\\\\\\lab11\\\\\\\\students.csv");
        List<String> names = students.stream().map(Student::getName).limit(5).toList();
        List<String> expectedNames = List.of("Camila Wood", "Alexander Thompson", "Liam Taylor", "Evelyn Jenkins", "Michael Jackson");
        assertEquals(expectedNames, names, "The first five student names should match the expected list.");
    }

    @Test
    void testSameStudent() throws InvalidStudentDataException{
        StudentSystem studentSystem = new StudentSystem("C:\\\\xampp\\\\htdocs\\\\Object-Oriented-Programming\\\\Lab11\\\\lab11\\\\src\\\\main\\\\java\\\\com\\\\lab11\\\\students.csv");
        Student highestGpaStudent = studentSystem.getHighestGPAStudent();
        Optional<Student> studentWithId12 = studentSystem.getStudentById(12);
        assertTrue(studentWithId12.isPresent() && studentWithId12.get() == highestGpaStudent, "The student with the highest GPA should be the same as the student with ID 12.");
    }

    @Test
    void testNoOfStudents() throws InvalidStudentDataException{
        StudentSystem studentSystem = new StudentSystem("C:\\\\xampp\\\\htdocs\\\\Object-Oriented-Programming\\\\Lab11\\\\lab11\\\\src\\\\main\\\\java\\\\com\\\\lab11\\\\students.csv");
        assertEquals(70, studentSystem.noOfStudents(), "The number of students should be 70.");
    }

    @Test
    void testIfLongestNameStudentIsBenjaminRichardson() throws InvalidStudentDataException{
        StudentSystem studentSystem = new StudentSystem("C:\\xampp\\htdocs\\Object-Oriented-Programming\\Lab11\\lab11\\src\\main\\java\\com\\lab11\\students.csv");
        Student longestNameStudent = studentSystem.getLongestNameStudent();
        assertEquals(studentSystem.getLongestNameStudent().getName(), longestNameStudent.getName(), "The student with the longest name should be Benjamin Richardson.");
    }

    @Test
    void testIfStudentWithId57IsNicholasJones() throws InvalidStudentDataException{
        StudentSystem studentSystem = new StudentSystem("C:\\xampp\\htdocs\\Object-Oriented-Programming\\Lab11\\lab11\\src\\main\\java\\com\\lab11\\students.csv");
        Optional<Student> studentWithId57 = studentSystem.getStudentById(57);
        assertEquals(studentSystem.getStudentById(57), studentWithId57);
    }

    @Test
    void testStudentListNotEmpty() throws InvalidStudentDataException{
        StudentSystem studentSystem = new StudentSystem("C:\\xampp\\htdocs\\Object-Oriented-Programming\\Lab11\\lab11\\src\\main\\java\\com\\lab11\\students.csv");
        assertFalse(studentSystem.getListOfStudents().isEmpty(), "List of students should not be empty.");
    }
    @Test
    void testGetStudentByIdNonexistent() throws InvalidStudentDataException{
        StudentSystem studentSystem = new StudentSystem("C:\\xampp\\htdocs\\Object-Oriented-Programming\\Lab11\\lab11\\src\\main\\java\\com\\lab11\\students.csv");
        Optional<Student> result = studentSystem.getStudentById(999);
        assertTrue(result.isEmpty(), "Searching for a nonexistent student ID should return an empty Optional.");
    }

    @Test
    void testToString() throws InvalidStudentDataException{
        StudentSystem s = new StudentSystem("C:\\xampp\\htdocs\\Object-Oriented-Programming\\Lab11\\lab11\\src\\main\\java\\com\\lab11\\students.csv");
        Student student = s.getListOfStudents().get(0);
        String expectedOutput = 
            "Name: Camila Wood\n" +
            "ID: 0\n" +
            "University: \"Stanford University\"\n" +
            "Department: Finance\n" +
            "GPA: 9.0";
        assertEquals(expectedOutput, student.toString(), "The toString method should return the correct format.");
    }
    @Test
    void testSetListOfStudents() throws InvalidStudentDataException{
        StudentSystem studentSystem = new StudentSystem("C:\\xampp\\htdocs\\Object-Oriented-Programming\\Lab11\\lab11\\src\\main\\java\\com\\lab11\\students.csv");
        List<Student> newStudents = List.of(
            new Student(11, "New Student", "New Uni", "New Dept", 8.0)
        );
        studentSystem.setListOfStudents(newStudents);
        assertEquals(1, studentSystem.getListOfStudents().size(), "After setting a new list, its size should match the new list.");
    }
    @Test
    void testGettersAndSetters() {
        Student student = new Student(1, "Mujo", "University XYZ", "Computer Science", 7.9);
        student.setId(2);
        assertEquals(2, student.getId());
        student.setName("Haso");
        assertEquals("Haso", student.getName());
        student.setUni("University ABC");
        assertEquals("University ABC", student.getUni());
        student.setDepartment("Mathematics");
        assertEquals("Mathematics", student.getDepartment());
        student.setGpa(9.0);
        assertEquals(9.0, student.getGpa());
    }

    @Test
    void testInvalidStudentDataHighGPA() throws InvalidStudentDataException {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John Doe", "University A", "Computer Science", 10.5));
        InvalidStudentDataException exception = assertThrows(InvalidStudentDataException.class, () -> {
            new StudentSystem(students);
        });
        
        assertEquals("Read data has invalid rows", exception.getMessage());
    }

    @Test
    void testInvalidStudentDataLowGPA() throws InvalidStudentDataException{
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John Doe", "University A", "Computer Science", 5.5));
        InvalidStudentDataException exception = assertThrows(InvalidStudentDataException.class, () -> {
            new StudentSystem(students);
        });
        assertEquals("Read data has invalid rows", exception.getMessage());
    }
}