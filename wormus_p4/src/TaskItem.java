import java.time.LocalDate;

public class TaskItem {

    private String title;
    private String description;
    private LocalDate date;
    private boolean completed;

    TaskItem(String title, String description, LocalDate date, boolean completed) {
        if(title.length() < 1 || title.equals("")){
            throw new InvalidTitleException("ERROR: Title must be at least one character or more in length.");
        }else{
            this.title = title;
        }
        this.description = description;
        this.date = date;
        this.completed = completed;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if(title.length() < 1 || title.equals("")){
            throw new InvalidTitleException("ERROR: Title must be at least one character or more in length.");
        }else{
            this.title = title;
        }

    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return ("[" + date + "] " + title + ": " + description);
    }
}
