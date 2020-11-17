import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<TaskItem> taskList;

    public TaskList(){
        taskList = new ArrayList<>();
    }

    public void view(){
        //prints out task list
        System.out.println("Current Tasks-----------");
        for(int i = 0; i < taskList.size(); i++){
            TaskItem current = taskList.get(i);

            //prints out stars if task is completed
            if(current.getCompleted()){
                System.out.println(i + ") *** " + current.getDate() + " " + current.getTitle() + ": "
                        + current.getDescription());
            }else {
                System.out.println(i + ") " + current.getDate() + " " + current.getTitle() + ": "
                        + current.getDescription());
            }
        }
    }
    public void save(){

    }
    public void add(TaskItem task){
        taskList.add(task);
    }
    public void edit(TaskItem item, String title, String description, LocalDate date){
        item.setTitle(title);
        item.setDescription(description);
        item.setDate(date);
    }
    public void remove(TaskItem task){
        taskList.remove(task);
    }
    public void markComplete(TaskItem task){
        task.setCompleted(true);
    }
    public void unmarkComplete(TaskItem task){
        task.setCompleted(false);
    }
}
