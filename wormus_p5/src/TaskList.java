import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class TaskList {

    private List<TaskItem> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void view() {
        System.out.println("Current Tasks-----------");
        for (int i = 0; i < taskList.size(); i++) {
            TaskItem current = taskList.get(i);

            //prints out stars if task is completed
            if (current.getCompleted()) {
                System.out.println(i + ") *** " + current.getDate().toString() + " " + current.getTitle() + ": "
                        + current.getDescription());
            } else {
                System.out.println(i + ") " + current);
            }
        }
    }

    public void write(String filename) {
        try (Formatter output = new Formatter(filename)) {
            for (int i = 0; i < taskList.size(); i++) {
                TaskItem item = taskList.get(i);
                output.format("%s%n%s%n%s%n%s%n", item.getTitle(), item.getDescription(), item.getDate(), item.getCompleted());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Unable to find the file...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(TaskItem task) {
        taskList.add(task);
    }

    public void edit(int index, String title, String description, LocalDate date) {
        TaskItem item = taskList.get(index);

        //String title, String description, LocalDate date
        item.setTitle(title);
        item.setDescription(description);
        item.setDate(date);
    }

    public void remove(int index) {
        TaskItem task = taskList.get(index);
        taskList.remove(task);
    }

    public void markComplete(int index) {
        TaskItem task = taskList.get(index);
        task.setCompleted(true);
    }

    public void unmarkComplete(int index) {
        TaskItem task = taskList.get(index);
        if(task.getCompleted() == false){
            System.out.println("ERROR: That task is not complete.");
        }else{
            task.setCompleted(false);
        }

    }

    public int getSize() {
        return taskList.size();
    }

    public int size() {
        return taskList.size();
    }

    public TaskItem get(int i) {
        return taskList.get(i);
    }
}
