import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactApp {
    private static ContactList Clist;
    private Scanner in = new Scanner(System.in);

    public void contactMainMenu(){
        App app = new App();
        int input = app.displayMainMenu();
        switch(input){
            case 1:
                Clist = new ContactList();
                displayContactOperationMenu(Clist);
                break;
            case 2:
                Clist = readContactList(app.getFileName());
                displayContactOperationMenu(Clist);
                break;
            case 3:
                app.displayAppMenu();
                break;
        }
    }
    private void displayContactOperationMenu(ContactList list) {
        while (true) {
            try {
                System.out.println("OPERATION MENU\n1. View list\n2. Add a contact\n" +
                        "3. Edit a contact\n4. Remove a contact\n5. Save current list\n6. Exit to main menu");
                int choice = in.nextInt();
                in.nextLine();
                if (choice == 6) {
                    contactMainMenu();
                }
                isContactOpMenuValid(choice);
                contactOpMenu(choice, list);
                //break;
            } catch (InvalidOptionException e) {
                System.out.println("◉_◉ ERROR: The number you entered was not one of the choices. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("◉_◉ ERROR: What you entered wasn't even an option. Please try again.");
                in.nextLine();
            }
        }
    }
    private void contactOpMenu(int input, ContactList list){
        switch(input){
            case 1:     //view list
                if (list.getSize() > 0) {
                    list.view();
                } else {
                    System.out.println("¯\\_(ツ)_/¯ You don't have any contacts to view");
                }
                break;
            case 2:     //add item
                list.add(addContactItem());
                System.out.println("Contact added successfully!");
                break;
            case 3:     //edit item
                if (list.getSize() > 0) {
                    list.view();
                    list.edit(editContactItem(list), getFirstName(), getLastName(), getPhoneNumber(), getEmail());
                } else {
                    System.out.println("¯\\_(ツ)_/¯ You don't have any contacts to edit");
                }

                break;
            case 4:     //remove item
                if (list.getSize() > 0) {
                    list.view();
                    list.remove(getContactItem(list));
                    System.out.println("Contact successfully removed");
                } else {
                    System.out.println("¯\\_(ツ)_/¯ You don't have any contacts to remove");
                }

                break;
            case 5:     //save current list
                System.out.println("What do you want to name your list: ");
                String filename = in.nextLine();
                list.write(filename);
                break;
            case 6:     //quit to main menu
                contactMainMenu();
                break;
        }
    }

    private static void isContactOpMenuValid(int input) {
        if(!(input > 0 || input <= 7)){
            throw new InvalidOptionException("◉_◉ ERROR: Your choice is not valid; please choose one of the options");
        }
    }

    private ContactItem addContactItem() {
        while(true){
            try{
                String firstName = getFirstName();
                String lastName = getLastName();
                String email = getEmail();
                String phoneNum = getPhoneNumber();
                return new ContactItem(firstName, lastName, phoneNum, email);
            }catch(InvalidContactItemException e){
                System.out.println("ERROR: You must enter at least 1 contact field. Please try again.");
            }
        }
    }

    private int editContactItem(ContactList list) {
        while (true) {
            try {
                System.out.println("Which item would you like to edit: ");
                int item = in.nextInt();
                in.nextLine();
                isContactNumberValid(item, list);
                return item;
            } catch (InvalidTaskSelection e) {
                System.out.println("◉_◉ ERROR: The contact you entered doesn't exist yet. Press Y to continue or press x to go to Operation Menu.");
                String next = in.next();
                if (next.equalsIgnoreCase("x")) {
                    displayContactOperationMenu(list);
                }
            } catch (InputMismatchException e) {
                System.out.println("◉_◉ ERROR: Please enter the number of the contact you would like to edit.");
                in.nextLine();
            } catch (InvalidContactSelection e){
                System.out.println("◉_◉ ERROR: The contact you entered doesn't exist yet. Press Y to continue or press X to go to Operation Menu");
                String next = in.next();
                if(next.equalsIgnoreCase("x")){
                    displayContactOperationMenu(list);
                }
            }
        }
    }
    private static void isContactNumberValid(int item, ContactList list){
        if(item >= list.getSize()) {
            throw new InvalidContactSelection("");
        }
    }

    private String getFirstName(){
        while (true) {
            try {
                System.out.println("First Name: ");
                String name = in.nextLine();
                return name;
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Wrong input type. Please type the name of the title.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private String getLastName(){
        while (true) {
            try {
                System.out.println("Last Name: ");
                String name = in.nextLine();
                return name;
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Wrong input type. Please type the name of the title.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private String getEmail(){
        while (true) {
            try {
                System.out.println("Email: ");
                String email = in.nextLine();
                return email;
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Wrong input type. Please type the name of the title.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private String getPhoneNumber(){
        while (true) {
            try {
                System.out.println("Phone Number: ");
                String phoneNum = in.nextLine();
                return phoneNum;
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Wrong input type. Please type the name of the title.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int getContactItem(ContactList list) {
        while (true) {
            try {
                System.out.println("Which contact would you like to remove?");
                int item = in.nextInt();
                in.nextLine();
                isContactNumberValid(item, list);
                return item;
            } catch (InvalidContactSelection e) {
                System.out.println("◉_◉ ERROR: The contact you entered doesn't exist yet. Press Y to continue, or press x to go to Operation Menu.");
                String next = in.next();
                if(next.equalsIgnoreCase("x")){
                    displayContactOperationMenu(list);
                }
            } catch (InputMismatchException e) {
                System.out.println("◉_◉ ERROR: You did not enter a valid number.");
                in.nextLine();
            }
        }
    }
    public ContactList readContactList(String file) {
        while (true) {
            try {
                ContactList temp = new ContactList();
                File contactListFile = new File(file);
                Scanner infile = new Scanner(contactListFile);

                while (infile.hasNextLine()) {
                    String fname = infile.nextLine();
                    String lname = infile.nextLine();
                    String phonenum = infile.nextLine();
                    String email = infile.nextLine();

                    ContactItem newItem = new ContactItem(fname, lname, phonenum, email);
                    temp.add(newItem);
                }
                System.out.println("List successfully loaded");
                return temp;
            } catch (FileNotFoundException e) {
                System.out.println("(╯°□°）╯︵ ┻━┻ The file was not found. Please enter the correct file name or press X to return to menu.");
                file = in.next();
                if(file.equalsIgnoreCase("x")){
                    contactMainMenu();
                }
                in.nextLine();
            } catch (InvalidContactItemException e) {
                System.out.println("ERROR: Invalid contact item encountered.");
                in.nextLine();
            }
        }

    }
}
