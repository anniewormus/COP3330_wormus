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
                output.format("%s%n%s%n%s%n%s%n", item.getFirstName(), item.getLastName(), item.getPhoneNumber(), item.getEmail());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Unable to find the file...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(ContactItem contact){
        contactList.add(contact);
    }

    public void edit(int index, String fname, String lname, String phonenum, String email){
        ContactItem item = contactList.get(index);

        try {
            contactCheck(fname, lname, phonenum, email);

        } catch (InvalidContactItemException e) {
            System.out.println("ERROR: You must enter at least one field.");
            return;
        }
        item.setFirstName(fname);
        item.setLastName(lname);
        item.setEmail(email);
        item.setPhoneNumber(phonenum);
    }

    public void contactCheck(String fname, String lname, String phonenum, String email) throws InvalidContactItemException {
        if(fname.isEmpty() && lname.isEmpty() && phonenum.isEmpty() && email.isEmpty()){
            throw new InvalidContactItemException("ERROR: You must enter at least one field.");
        }
    }

    public void remove(int index){
        ContactItem contact = contactList.get(index);
        contactList.remove(contact);
    }

    public int getSize() {
        return contactList.size();
    }

    public String getEntry(int index){
        ContactItem contact = contactList.get(index);
        return ("\tName: " + contact.getFirstName() + " " + contact.getLastName() + "\n\tPhone Number: " + contact.getPhoneNumber() + "\n\tEmail: " + contact.getEmail());
    }
}