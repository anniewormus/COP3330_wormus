/* Andrea Wormus
 * 11/30/2020
 * This program allows the user to create either a to-do list or a contact list.
 * The items in either list are saved in a file so the user can pull up a previos list after
 * program completes.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class app {

    private static Scanner in = new Scanner(System.in);
    private static TaskList Tlist;
    private static ContactList Clist;

    public static void main(String[] args) {
        displayAppMenu();
    }

    private static void displayAppMenu(){
        while(true){
            try{
                System.out.println("SELECT YOUR APPLICATION\n1. Task List\n2. Contact List\n3. Quit");
                int choice = in.nextInt();
                in.nextLine();          //resets after nextInt for a string entry
                isMenuValid(choice);
                applicationMenu(choice);
                break;
            }catch (InvalidOptionException e){
                System.out.println("ERROR: The option you entered was not one of the choices. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Please enter one of the number options. Please try again.");
                in.nextLine();
            }
        }
    }

    private static void applicationMenu(int input){
        int choice;
        switch(input){
            case 1:
                taskMainMenu();
                break;
            case 2:
                contactMainMenu();
                break;
            case 3:
                System.exit(0);
        }
    }

    private static int displayMainMenu() {
        while (true) {
            try {
                System.out.println("MAIN MENU\n1. Create a new list\n2. Load an existing list\n3. Return to Application Menu");
                int choice = in.nextInt();
                in.nextLine();      //resets after nextInt for a string entry
                isMenuValid(choice);
                return choice;
            } catch (InvalidOptionException e) {
                System.out.println("ERROR: The option you entered was not one of the choices. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Please enter one of the number options. Please try again.");
                in.nextLine();
            }
        }
    }

    private static void taskMainMenu() {
        int input = displayMainMenu();
        switch (input) {
            case 1:
                Tlist = new TaskList();
                displayTaskOperationMenu(Tlist);
                break;
            case 2:
                Tlist = readTaskList(getFileName());
                displayTaskOperationMenu(Tlist);
                break;
            case 3:
                displayAppMenu();
                break;
        }
    }

    private static void contactMainMenu(){
        int input = displayMainMenu();
        switch(input){
            case 1:
                Clist = new ContactList();
                displayContactOperationMenu(Clist);
                break;
            case 2:
                Clist = readContactList(getFileName());
                displayContactOperationMenu(Clist);
                break;
            case 3:
                displayAppMenu();
                break;
        }
    }

    private static void isMenuValid(int input) {
        if (!(input == 1 || input == 2 || input == 3)) {
            throw new InvalidOptionException("◉_◉ ERROR: Your choice is not valid; please choose one of the options");
        }
    }

    private static void displayTaskOperationMenu(TaskList list) {
        while (true) {
            try {
                System.out.println("OPERATION MENU\n1. View list\n2. Add a task\n" +
                        "3. Edit a task\n4. Remove a task\n5. Mark task as completed\n" +
                        "6. Unmark task as complete\n7. Save current list\n8. Exit to main menu");
                int choice = in.nextInt();
                in.nextLine();
                if (choice == 8) {
                    taskMainMenu();
                }
                isTaskOpMenuValid(choice);
                taskOpMenu(choice, list);
                //break;
            } catch (InvalidOptionException e) {
                System.out.println("◉_◉ ERROR: The number you entered was not one of the choices. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("◉_◉ ERROR: What you entered wasn't even an option. Please try again.");
                in.nextLine();
            }
        }
    }
    private static void displayContactOperationMenu(ContactList list) {
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

    private static void taskOpMenu(int input, TaskList list) {
        switch (input) {
            case 1:     //view list
                if (list.getSize() > 0) {
                    list.view();
                } else {
                    System.out.println("¯\\_(ツ)_/¯ You don't have any items to print");
                }
                break;
            case 2:     //add item
                list.add(addTaskItem());
                System.out.println("Task successfully added!");
                break;
            case 3:     //edit item
                list.view();
                list.edit(editTaskItem(list), getTitle(), getDescription(), getdate());
                break;
            case 4:     //remove item
                list.view();
                System.out.println("Which task would you like to remove: ");
                list.remove(getTaskItem(list));
                break;
            case 5:     //mark item as completed
                list.view();
                System.out.println("Which task would you like to complete: ");
                list.markComplete(getTaskItem(list));
                break;
            case 6:     //unmark an item
                list.view();
                System.out.println("Which task would you like to un-complete: ");
                list.unmarkComplete(getTaskItem(list));
                break;
            case 7:     //save current list
                System.out.println("What do you want to name your list: ");
                String filename = in.nextLine();
                list.write(filename);
                break;
            case 8:     //quit to main menu
                taskMainMenu();
                break;
        }
    }

    private static void isTaskOpMenuValid(int input) {
        if(!(input > 0 || input < 9)){
            throw new InvalidOptionException("◉_◉ ERROR: Your choice is not valid; please choose one of the options");
        }
    }
    private static TaskItem addTaskItem() {
        String title = getTitle();
        String desc = getDescription();
        LocalDate date = getdate();
        return new TaskItem(title, desc, date, false);
    }

    private static int editTaskItem(TaskList list) {
        while (true) {
            try {
                System.out.println("Which item would you like to edit: ");
                int item = in.nextInt();
                in.nextLine();
                isTaskValid(item, list);
                return item;
            } catch (InvalidTaskSelection e) {
                System.out.println("◉_◉ ERROR: The task you entered doesn't exist yet. Press any key to continue or press x to go to Operation Menu.");
                String next = in.next();
                if(next.equalsIgnoreCase("x")){
                    displayTaskOperationMenu(list);
                }
            } catch (InputMismatchException e) {
                System.out.println("◉_◉ ERROR: Please enter the number of the task you would like to edit.");
                in.nextLine();
            }
        }
    }

    private static int getTaskItem(TaskList list) {
        while (true) {
            try {
                int item = in.nextInt();
                in.nextLine();
                isTaskValid(item, list);
                return item;
            } catch (InvalidTaskSelection e) {
                System.out.println("◉_◉ ERROR: The task you entered doesn't exist yet. Press any key to continue or press x to go to Operation Menu.");
                String next = in.next();
                if(next.equalsIgnoreCase("x")){
                    displayTaskOperationMenu(list);
                }
                System.out.println("Task Number: ");
            } catch (InputMismatchException e) {
                System.out.println("◉_◉ ERROR: Please enter the number of the task you would like to edit.");
                in.nextLine();
            }
        }
    }
    private static void isTaskValid(int item, TaskList list){
        if(item >= list.getSize()){
            throw new InvalidTaskSelection("◉_◉ ERROR: The task you entered doesn't exist yet. PLease enter a valid task number.");
        }
    }

    private static String getTitle() {
        while (true) {
            try {
                System.out.println("Title: ");
                String title = in.nextLine();
                isTitleValid(title);
                return title;
            } catch (InvalidTitleException e) {
                System.out.println("ERROR: Title must be at least one character or more in length.");
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Wrong input type. Please type the name of the title.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //title must be at least one character in length
    private static void isTitleValid(String title){
        if(title.length() < 1 || title.equals("")){
            throw new InvalidTitleException("ERROR: Title must be at least one character or more in length.");
        }
    }

    private static String getDescription() {
        while (true) {
            try {
                System.out.println("Description: ");
                String desc = in.nextLine();
                return desc;
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Wrong input type. Please type your task description.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static LocalDate getdate() {
        while (true) {
            try {
                System.out.println("Date (YYYY-MM-DD): ");
                LocalDate date = LocalDate.parse(in.nextLine());
                return date;
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Please type in your date (YYYY-MM-DD)");
            } catch (DateTimeException e) {
                System.out.println("ERROR: The date you entered was not valid. Pleas re-enter in the form YYYY-MM-DD.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void contactOpMenu(int input, ContactList list){
        switch(input){
            case 1:     //view list
                if (list.getSize() > 0) {
                    list.view();
                } else {
                    System.out.println("¯\\_(ツ)_/¯ You don't have any items to print");
                }
                break;
            case 2:     //add item
                list.add(addContactItem());
                System.out.println("Contact added successfully!");
                break;
            case 3:     //edit item
                list.view();
                list.edit(editContactItem(list), getFirstName(), getLastName(), getPhoneNumber(), getEmail());
                break;
            case 4:     //remove item
                list.view();
                list.remove(getContactItem(list));
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
        if(!(input > 0 || input < 7)){
            throw new InvalidOptionException("◉_◉ ERROR: Your choice is not valid; please choose one of the options");
        }
    }

    private static ContactItem addContactItem() {
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

    private static int editContactItem(ContactList list) {
        while (true) {
            try {
                System.out.println("Which item would you like to edit: ");
                int item = in.nextInt();
                in.nextLine();
                isContactNumberValid(item, list);
                return item;
            } catch (InvalidTaskSelection e) {
                System.out.println("◉_◉ ERROR: The contact you entered doesn't exist yet. Press any key to continue or press x to go to Operation Menu.");
                String next = in.next();
                if (next.equalsIgnoreCase("x")) {
                    displayContactOperationMenu(list);
                }
            } catch (InputMismatchException e) {
                System.out.println("◉_◉ ERROR: Please enter the number of the contact you would like to edit.");
                in.nextLine();
            } catch (InvalidContactSelection e){
                System.out.println("◉_◉ ERROR: The contact you entered doesn't exist yet. Press any key to continue or press X to go to Operation Menu");
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

    private static String getFirstName(){
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
    private static String getLastName(){
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
    private static String getEmail(){
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
    private static String getPhoneNumber(){
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

    private static int getContactItem(ContactList list) {
        while (true) {
            try {
                System.out.println("Which contact would you like to remove?");
                int item = in.nextInt();
                in.nextLine();
                isContactNumberValid(item, list);
                return item;
            } catch (InvalidContactSelection e) {
                System.out.println("◉_◉ ERROR: The contact you entered doesn't exist yet. Press any key to continue, or press x to go to Operation Menu.");
                String next = in.next();
                if(next.equalsIgnoreCase("x")){
                    displayContactOperationMenu(list);
                }
            } catch (InputMismatchException e) {
                System.out.println("◉_◉ ERROR: You did not enter a valid number. Press 1 to continue, or press x to go to Operation Menu.");
                in.nextLine();
            }
        }
    }

    private static String getFileName() {
        System.out.println("Enter the filename to load: ");
        String file = in.nextLine();
        return file;
    }

    private static TaskList readTaskList(String file) {
        while (true) {
            try {
                TaskList temp = new TaskList();
                File taskListFile = new File(file);
                Scanner infile = new Scanner(taskListFile);
                infile.useDelimiter(";");
                while (infile.hasNext()) {
                    String title = infile.next();
                    String description = infile.next();
                    String date = infile.next();
                    boolean complete = Boolean.parseBoolean(infile.next());
                    TaskItem newItem = new TaskItem(title, description, LocalDate.parse(date), complete);
                    temp.add(newItem);
                    System.out.println("File successfully loaded");
                }
                return temp;
            } catch (FileNotFoundException e) {
                System.out.println("(╯°□°）╯︵ ┻━┻ The file was not found. Please enter the correct file name or press X to return to menu.");
                String next = in.next();
                if(next.equalsIgnoreCase("x")){
                    taskMainMenu();
                }
            }
        }

    }

    private static ContactList readContactList(String file) {
        while (true) {
            try {
                ContactList temp = new ContactList();
                File contactListFile = new File(file);
                Scanner infile = new Scanner(contactListFile);
                infile.useDelimiter(";");
                while (infile.hasNext()) {
                    String fname = infile.next();
                    String lname = infile.next();
                    String phonenum = infile.next();
                    String email = infile.next();
                    ContactItem newItem = new ContactItem(fname, lname, phonenum, email);
                    temp.add(newItem);
                }
                System.out.println("List successfully loaded");
                return temp;
            } catch (FileNotFoundException e) {
                System.out.println("(╯°□°）╯︵ ┻━┻ The file was not found. Please enter the correct file name or press X to return to menu.");
                String next = in.next();
                if(next.equalsIgnoreCase("x")){
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
