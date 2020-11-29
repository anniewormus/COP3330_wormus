import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskItemTest {
    @Test
    public void creatingTaskItemFailsWithInvalidDueDate() {
        assertThrows(DateTimeException.class, () -> new TaskItem("Title", "Description", LocalDate.parse("0000-00-00"), false));
    }
    @Test
    public void creatingTaskItemFailsWithInvalidTitle(){
        assertThrows(InvalidTitleException.class, () -> new TaskItem("", "Description", LocalDate.parse("2020-11-20"), false));
    }
    @Test
    public void creatingTaskItemSucceedsWithValidDueDate(){
        assertDoesNotThrow(() -> new TaskItem("Title", "Description", LocalDate.parse("2020-11-20"), false));
    }
    @Test
    public void creatingTaskItemSucceedsWithValidTitle(){
        assertDoesNotThrow(() -> new TaskItem("Title", "Description", LocalDate.parse("2020-11-20"), false));
    }
    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate(){
        TaskItem t = new TaskItem("Title", "Description", LocalDate.parse("2020-11-20"), false);
        assertThrows(DateTimeException.class, () -> t.setDate(LocalDate.parse("2020-20-20")));
    }
    @Test
    public void settingTaskItemDueDateSucceedsWithValidDate(){
        TaskItem t = new TaskItem("Title", "Description", LocalDate.parse("2020-11-20"), false);
        assertDoesNotThrow(() -> t.setDate(LocalDate.parse("2020-12-25")));
    }
    @Test
    public void settingTaskItemTitleFailsWithInvalidTitle(){
        TaskItem t = new TaskItem("Title", "Description", LocalDate.parse("2020-11-20"), false);
        assertThrows(InvalidTitleException.class, () -> t.setTitle(""));
    }
    @Test
    public void settingTaskItemTitleSucceedsWithValidTitle(){
        TaskItem t = new TaskItem("Title", "Description", LocalDate.parse("2020-11-20"), false);
        assertDoesNotThrow(() -> t.setTitle("Valid Title"));
    }
}

