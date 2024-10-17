package com.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
enum Status{
    TO_DO, IN_PROGRESS, COMPLETED, CANCELLED
}
class TaskItem{
    public int taskId;
    public String taskDescription;
    public Status taskStatus;
    public TaskItem(int taskId, String taskDescription, Status taskStatus) {
        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;    
}}
public class DbConnect {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/lab3";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "11235813";
 
    private static Connection connection = null;
 
   static {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<TaskItem> fetchTaskEntities(){
        List<TaskItem> fetchedTaskEntities = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet statementResultSet = statement.executeQuery();
            while(statementResultSet.next()){
                int taskId = statementResultSet.getInt("id");
                String taskDescription = statementResultSet.getString("task_description");
                String statusString = statementResultSet.getString("task_status");
                Status taskStatus = Status.valueOf(statusString.toUpperCase());
                TaskItem task = new TaskItem(taskId, taskDescription, taskStatus);
                fetchedTaskEntities.add(task);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return fetchedTaskEntities;
    }
    public static TaskItem fetchById(int id){
        String query="SELECT id, task_description, task_status FROM tasks WHERE id=?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet statementResultSet = statement.executeQuery();
            if(statementResultSet.next()){
                int taskId = statementResultSet.getInt("id");
                String taskDescription = statementResultSet.getString("task_description");
                String statusString = statementResultSet.getString("task_status");
                Status taskStatus = Status.valueOf(statusString.toUpperCase());
                TaskItem task = new TaskItem(taskId, taskDescription, taskStatus);
                return task;
            }
            else{
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
 }
 public static void createTask(int taskId, String taskDescription, Status taskStatus) {
    String query = "INSERT INTO tasks (id, task_description, task_status) VALUES (?, ?, ?)";
    try {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, taskId);
        statement.setString(2, taskDescription);
        statement.setString(3, taskStatus.name());
        statement.executeUpdate();
        System.out.println("Task created successfully!");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

 public static void updateTaskNameById(int id, String newTaskDescription) {
    String query = "UPDATE tasks SET task_description = ? WHERE id = ?";
    try {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, newTaskDescription);
        statement.setInt(2, id);
        int rowsAffected = statement.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("No task found with the given ID.");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
    public static void main(String[] args) {
        List<TaskItem> taskItemsFromDb = fetchTaskEntities();
        for(TaskItem task : taskItemsFromDb){
            System.out.println(task);
        }
        TaskItem idToFetch = fetchById(2);
        System.out.println("Id for search was: "+idToFetch);
        createTask(5, "New Task", Status.TO_DO);
        updateTaskNameById(5, "Good job! You've updated this task");
    }
 }