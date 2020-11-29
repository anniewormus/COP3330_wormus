import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void addingTaskItemsIncreasesSize() {
        TaskList t = new TaskList();
        int size1 = t.size();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        int size2 = t.size();
        assert(size2 > size1);
    }
    @Test
    public void completingTaskItemChangesStatus(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        t.get(0).setCompleted(true);
        assertEquals(true, t.get(0).getCompleted());
    }
    @Test
    public void completingTaskItemFailsWithInvalidIndex(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        assertThrows(IndexOutOfBoundsException.class, () -> t.get(1).getCompleted());

    }
    @Test
    public void editingTaskItemChangesValues(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        String title1 = t.get(0).getTitle();
        t.edit(0, "title update", "desc", LocalDate.parse("2020-11-20"));
        String title2 = t.get(0).getTitle();
        assertNotEquals(title1, title2);
    }
    @Test
    public void editingTaskItemDescriptionChangesValue(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        String description1 = t.get(0).getDescription();
        t.edit(0, "title", "desc update", LocalDate.parse("2020-11-20"));
        String description2 = t.get(0).getDescription();
        assertNotEquals(description1, description2);
    }
    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        assertThrows(IndexOutOfBoundsException.class, () -> t.edit(1, "title", "desc update", LocalDate.parse("2020-11-20")));
    }
    @Test
    public void editingTaskItemDueDateChangesValue(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        LocalDate date1 = t.get(0).getDate();
        t.edit(0, "title", "desc", LocalDate.parse("2020-12-25"));
        LocalDate date2 = t.get(0).getDate();
        assertNotEquals(date1, date2);
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        assertThrows(IndexOutOfBoundsException.class, () -> t.edit(1, "title", "desc", LocalDate.parse("2020-12-25")));
    }
    @Test
    public void editingTaskItemTitleChangesValue(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        String title1 = t.get(0).getTitle();
        t.edit(0, "title update", "desc", LocalDate.parse("2020-12-25"));
        String title2 = t.get(0).getTitle();
        assertNotEquals(title1, title2);
    }
    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        assertThrows(IndexOutOfBoundsException.class, () -> t.edit(1, "title update", "desc", LocalDate.parse("2020-11-20")));
    }
    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        assertThrows(IndexOutOfBoundsException.class, () -> t.get(1).getDescription());
    }
    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        assertDoesNotThrow(() -> t.get(0).getDescription());
    }
    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        assertThrows(IndexOutOfBoundsException.class, () -> t.get(1).getDate());
    }
    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        assertDoesNotThrow(() -> t.get(0).getDate());
    }
    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        assertThrows(IndexOutOfBoundsException.class, () -> t.get(1).getTitle());
    }
    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        assertDoesNotThrow(() -> t.get(0).getTitle());
    }
    @Test
    public void newTaskListIsEmpty(){
        TaskList t = new TaskList();
        assertEquals(t.getSize(), 0);
    }
    @Test
    public void removingTaskItemsDecreasesSize(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        t.add(a);
        int size1 = t.getSize();
        t.remove(0);
        int size2 = t.getSize();
        assert(size1 > size2);
    }
    @Test
    public void removingTaskItemsFailsWithInvalidIndex(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), false);
        assertThrows(IndexOutOfBoundsException.class, () -> t.remove(1));
    }
    @Test
    public void savedTaskListCanBeLoaded(){
        TaskList t = new TaskList();
        String file = "file.txt";
        assertDoesNotThrow(() -> t.write(file));
    }
    @Test
    public void uncompletingTaskItemChangesStatus(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), true);
        t.add(a);
        boolean status1 = t.get(0).getCompleted();
        t.unmarkComplete(0);
        boolean status2 = t.get(0).getCompleted();
        assert(status1 != status2);
    }
    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex(){
        TaskList t = new TaskList();
        TaskItem a = new TaskItem("title", "desc", LocalDate.parse("2020-11-20"), true);
        t.add(a);
        assertThrows(IndexOutOfBoundsException.class, () -> t.unmarkComplete(1));
    }
}
