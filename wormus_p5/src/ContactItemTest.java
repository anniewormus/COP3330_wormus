import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactItemTest {
    @Test
    public void creationFailsWithAllBlankValues() throws Exception{
        assertThrows(InvalidContactItemException.class, () -> new ContactItem("", "", "", ""));
    }
    @Test
    public void creationSucceedsWithBlankFirstName(){
        assertDoesNotThrow(() -> new ContactItem("", "Z", "1234567890", "bigpimpin@gmail.com"));
    }
    @Test
    public void creationSucceedsWithBlankLastName(){
        assertDoesNotThrow(() -> new ContactItem("Jay", "", "1234567890", "bigpimpin@gmail.com"));
    }
    @Test
    public void creationSucceedsWithBlankEmail(){
        assertDoesNotThrow(() -> new ContactItem("Jay", "Z", "1234567890", ""));
    }
    @Test
    public void creationSucceedsWithBlankPhoneNumber(){
        assertDoesNotThrow(() -> new ContactItem("Jay", "Z", "", "bigpimpin@gmail.com"));
    }
    @Test
    public void creationSucceedsWithAllFieldsFilled(){
        assertDoesNotThrow(() -> new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com"));
    }
    @Test
    public void editingFailsWithAllBlankValues() throws InvalidContactItemException {
        ContactItem test = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        assertThrows(InvalidContactItemException.class, () -> test.edit("", "", "", ""));
    }
    @Test
    public void editingSucceedsWithBlankFirstName() throws InvalidContactItemException {
        ContactItem test = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        assertDoesNotThrow(() -> test.edit("", "Knowles", "0987654321", "singlelady@gmail.com"));
    }
    @Test
    public void editingSucceedsWithBlankLastName() throws InvalidContactItemException {
        ContactItem test = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        assertDoesNotThrow(() -> test.edit("Beyonce", "", "0987654321", "singlelady@gmail.com"));
    }
    @Test
    public void editingSucceedsWithBlankEmail() throws InvalidContactItemException {
        ContactItem test = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        assertDoesNotThrow(() -> test.edit("Beyonce", "Knowles", "0987654321", ""));
    }
    @Test
    public void editingSucceedsWithBlankPhoneNumber() throws InvalidContactItemException {
        ContactItem test = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        assertDoesNotThrow(() -> test.edit("Beyonce", "Knowles", "", "singlelady@gmail.com"));
    }
    @Test
    public void editingSucceedsWithAllItemsFilled() throws InvalidContactItemException {
        ContactItem test = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
        assertDoesNotThrow(() -> test.edit("Beyonce", "Knowles", "0987654321", "singlelady@gmail.com"));
    }
    @Test
    public void testToString() throws InvalidContactItemException {
        ContactItem test = new ContactItem("Jay", "Z", "1234567890", "bigpimpin@gmail.com");
    assertEquals(test.toString(), "\tName: Jay Z\n\tPhone Number: 1234567890\n\tEmail: bigpimpin@gmail.com");
    }
    @Test
    public void ContactOperationMenuFailsWithOutOfBoundsNumber(){

    }
    @Test
    public void ContactOperationMenuFailsWithInvalidDataType(){

    }
}
