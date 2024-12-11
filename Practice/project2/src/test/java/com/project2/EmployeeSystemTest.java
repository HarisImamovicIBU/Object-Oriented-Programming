package com.project2;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class EmployeeSystemTest {
    @Test
    void testIfEmployeesArePresent() throws IOException{
        EmployeeSystem es = new EmployeeSystem("C:\\xampp\\htdocs\\Object-Oriented-Programming\\Practice\\project2\\src\\main\\java\\com\\project2\\employees.csv");
        assertTrue(!es.getEmployees().isEmpty(), "There should be at least one employee in the system.");
    }
}
