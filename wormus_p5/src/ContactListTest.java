import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactListTest {
    @Test
    public void AddingItemsIncreasesSize() throws Exception {
        ContactList test = new ContactList();
        ContactItem testItem = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        int size1 = test.getSize();
        test.add(testItem);
        int size2 = test.getSize();
        assert (size2 > size1);
    }

    @Test
    public void EditingItemFailsWithAllBlanks() throws InvalidContactItemException {
        ContactList test = new ContactList();
        ContactItem testItem = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        test.add(testItem);
        assertThrows(InvalidContactItemException.class, () -> test.contactCheck("", "", "", ""));
    }

    @Test
    public void EditingItemsFailsWithInvalidIndex() throws Exception {
        ContactList test = new ContactList();
        ContactItem testItem = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        test.add(testItem);
        assertThrows(IndexOutOfBoundsException.class, () -> test.edit(1, "", "", "", ""));
    }

    @Test
    public void editingSucceedsWithBlankFirstName() throws Exception {
        ContactList test = new ContactList();
        ContactItem testItem = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        test.add(testItem);
        assertDoesNotThrow(() -> test.edit(0, "", "Z", "1234567890", "bigpimpin@gmail.com"));
    }

    @Test
    public void editingSucceedsWithBlankLastName() throws Exception {
        ContactList test = new ContactList();
        ContactItem testItem = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        test.add(testItem);
        assertDoesNotThrow(() -> test.edit(0, "Jay", "", "1234567890", "bigpimpin@gmail.com"));
    }

    @Test
    public void editingSucceedsWithBlankEmail() throws Exception {
        ContactList test = new ContactList();
        ContactItem testItem = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        test.add(testItem);
        assertDoesNotThrow(() -> test.edit(0, "Jay", "Z", "1234567890", ""));
    }

    @Test
    public void editingSucceedsWithBlankPhoneNumber() throws Exception {
        ContactList test = new ContactList();
        ContactItem testItem = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        test.add(testItem);
        assertDoesNotThrow(() -> test.edit(0, "Jay", "Z", "", "bigpimpin@gmail.com"));
    }

    @Test
    public void editingSucceedsWithAllItemsFilled() throws Exception {
        ContactList test = new ContactList();
        ContactItem testItem = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        test.add(testItem);
        assertDoesNotThrow(() -> test.edit(0, "BeYonce", "Knowles", "0987654321", "singlelady@gmail.com"));
    }

    @Test
    public void newListIsEmpty() throws Exception {
        ContactList test = new ContactList();
        assertEquals(test.getSize(), 0);
    }

    @Test
    public void removingItemsDecreasesSize() throws Exception {
        ContactList test = new ContactList();
        ContactItem testItem = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        test.add(testItem);
        int size1 = test.getSize();

        test.remove(0);
        int size2 = test.getSize();
        assert (size1 > size2);
    }

    @Test
    public void addingItemsIncreasesSize() throws Exception {
        ContactList test = new ContactList();
        ContactItem testItem = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        test.add(testItem);
        int size1 = test.getSize();

        ContactItem testItem2 = new ContactItem("Beyonce", "Knowles", "0987654321", "singlelady@gmail.com");
        test.add(testItem2);
        int size2 = test.getSize();
        assert (size1 < size2);
    }

    @Test
    public void removingItemsFailsWithInvalidIndex() throws Exception {
        ContactList test = new ContactList();
        ContactItem testItem = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        test.add(testItem);
        assertThrows(IndexOutOfBoundsException.class, () -> test.remove(1));
    }

    @Test
    public void savedContactListCanBeLoaded() throws Exception {
        ContactList test = new ContactList();
        ContactItem testItem1 = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        //ContactItem testItem2 = new ContactItem("Beyonce", "Knowles", "1234567890", "singlelady@gmail.com");
        test.add(testItem1);
        //test.add(testItem2);

        test.write("list.txt");
        ContactApp app = new ContactApp();
        test = app.readContactList("list.txt");

        assertEquals(testItem1.toString(), test.getEntry(0));
    }
}
