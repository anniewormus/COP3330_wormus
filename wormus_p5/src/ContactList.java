import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class ContactList{

    private List<ContactItem> contactList;

    public ContactList(){
        contactList = new ArrayList<>();
    }
    public void view(){
        System.out.println("Current Contacts-----------");
        for(int i = 0; i < contactList.size(); i++){
            ContactItem current = contactList.get(i);
            System.out.println(i + ") " + current);
        }
    }
    public void write(String filename){
        try (Formatter output = new Formatter(filename)) {
            for (int i = 0; i < contactList.size(); i++) {
                ContactItem item = contactList.get(i);
                output.format("%s;%s;%s;%s%n", item.getFirstName(), item.getLastName(), item.getEmail(), item.getPhoneNumber());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Unable to find the file...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(ContactItem contact){
        contactList.add(contact);
        System.out.println("\\ (•◡•) / \nTask was successfully added!");
    }

    public void edit(int index, String fname, String lname, String email, String phonenum){
        ContactItem item = contactList.get(index);

        item.setFirstName(fname);
        item.setLastName(lname);
        item.setEmail(email);
        item.setPhoneNumber(phonenum);
    }

    public void remove(int index){
        ContactItem contact = contactList.get(index);
        contactList.remove(contact);
    }

}