import java.time.LocalDate;

public class TaskItem {

    private String title;
    private String description;
    private LocalDate date;
    private boolean completed;

    TaskItem(String title, String description, LocalDate date, boolean completed){

    }
    public String getTitle( ){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public LocalDate getDate(){
        return this.date;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    public boolean getCompleted(){
        return this.completed;
    }
    public void setCompleted(boolean completed){
        this.completed = completed;
    }

}