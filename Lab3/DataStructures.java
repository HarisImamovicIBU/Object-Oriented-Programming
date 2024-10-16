package Lab3;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
enum Status{
    TO_DO, IN_PROGRESS, COMPLETED, CANCELLED
}
class TaskItem{
    private int taskId;
    private String taskDescription;
    enum Status{
        TO_DO, IN_PROGRESS, COMPLETED, CANCELLED
    }
}
public class DataStructures {
    private List<TaskItem> tasks = new ArrayList<>();
 
    public DataStructures() {
       tasks = Arrays.asList(
               new TaskItem(1,"Push lab code to the github", Status.TO_DO),
               new TaskItem(2,"Prepare for the quiz", Status.IN_PROGRESS),
               new TaskItem(3,"Go over tasks from lab2", Status.COMPLETED));
   }


    public List<TaskItem> getAllObjects(){
        return tasks;
    }
}