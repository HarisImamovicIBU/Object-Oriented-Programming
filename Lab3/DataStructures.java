package Lab3;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
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
    public List<TaskItem> getByStatus(Status taskStatus){
        List<TaskItem> filteredListOfTasks = new ArrayList<>();
        for(TaskItem task : tasks){
            if(task.taskStatus==taskStatus){
                filteredListOfTasks.add(task);
            }
        }
        return filteredListOfTasks;
    }
    public List<TaskItem> getByParameterIdGreaterOrEqualToTwo(){
        List<TaskItem> tasksWithValidId = new ArrayList<>();
        for(TaskItem task : tasks){
            if(task.taskId>=2){
                tasksWithValidId.add(task);
            }
        }
        return tasksWithValidId;
    }
    public void printTasksDescriptions() {
        tasks.stream()
             .forEach(task -> System.out.println(task.taskDescription));
    }
    

    public static void main(String[] args){
        DataStructures ds=new DataStructures();
        System.out.println("Get all objects: ");
        for(TaskItem obj : ds.getAllObjects()){
            System.out.println(obj);
        }
        System.out.println("Id greater than or equal to 2: ");
        for(TaskItem task : ds.getByParameterIdGreaterOrEqualToTwo()){
            System.out.println("task.taskDescription" + "id: "+task.taskId);
        }
        System.out.println("Print descriptions: ");
        ds.printTasksDescriptions();
    }
}