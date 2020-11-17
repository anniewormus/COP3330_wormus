/* What up it's Andrea Wormus
 * 11/12/2020
 * This program ahhh I'll do this later
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {

    private static Scanner in = new Scanner(System.in);
    private static TaskList list;

    public static void main(String[] args) {
        displayMainMenu();
    }

    private static void displayMainMenu() {
        while (true) {
            try {
                System.out.println("MAIN MENU\n1. Create a new task list\n2. Load an existing task list\n3. Exit");
                int choice = in.nextInt();
                in.nextLine();      //resets after nextInt for a string entry
                isMainMenuValid(choice);
                mainMenu(choice);
                break;
            } catch (InvalidOptionException e) {
                System.out.println("◉_◉ ERROR: The option you entered was not one of the choices. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("◉_◉ ERROR: What you entered wasn't even an option. Please try again.");
                in.nextLine();
            }
        }
    }

    private static void mainMenu(int choice) {
        switch (choice) {
            case 1:
                list = new TaskList();
                displayOperationMenu(list);
                break;
            case 2:
                list = read(getFileName());
                displayOperationMenu(list);
                break;
            case 3:
                System.exit(0);
        }
    }

    private static void isMainMenuValid(int input) {
        if (!(input == 1 || input == 2 || input == 3)) {
            throw new InvalidOptionException("◉_◉ ERROR: Your choice is not valid; please choose one of the options");
        }
    }

    private static void displayOperationMenu(TaskList list) {
        while (true) {
            try {
                System.out.println("OPERATION MENU\n1. View list\n2. Add a task\n" +
                        "3. Edit a task\n4. Remove a task\n5. Mark task as completed\n" +
                        "6. Unmark task as complete\n7. Save current list\n8. Exit to main menu");
                int choice = in.nextInt();
                in.nextLine();
                if (choice == 8) {
                    displayMainMenu();
                }
                isOpMenuValid(choice);
                opMenu(choice, list);
               // break;
            } catch (InvalidOptionException e) {
                System.out.println("◉_◉ ERROR: The number you entered was not one of the choices. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("◉_◉ ERROR: What you entered wasn't even an option. Please try again.");
                in.nextLine();
            }
        }
    }

    private static void opMenu(int input, TaskList list) {
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
                    break;
                case 3:     //edit item
                    list.view();
                    list.edit(editTaskItem(), getTitle(), getDescription(), getdate());
                    break;
                case 4:     //remove item
                    list.view();
                    System.out.println("Which task would you like to remove: ");
                    list.remove(getTaskItem());
                    break;
                case 5:     //mark item as completed
                    list.view();
                    System.out.println("Which task would you like to complete: ");
                    list.markComplete(getTaskItem());
                    break;
                case 6:     //unmark an item
                    list.view();
                    System.out.println("Which task would you like to un-complete: ");
                    list.unmarkComplete(getTaskItem());
                    break;
                case 7:     //save current list
                    System.out.println("What do you want to name your list: ");
                    String filename = in.nextLine();
                    list.write(filename);
                    break;
                case 8:     //quit to main menu
                    return;
            }
    }

    private static void isOpMenuValid(int input) {
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

    private static int editTaskItem() {
        while (true) {
            try {
                System.out.println("Which item would you like to edit: ");
                int item = in.nextInt();
                in.nextLine();
                return item;
            } catch (InputMismatchException e) {
                System.out.println("◉_◉ ERROR: Please enter the number of the task you would like to edit.");
                in.nextLine();
            }
        }
    }

    private static int getTaskItem() {
        while (true) {
            try {
                int item = in.nextInt();
                in.nextLine();
                return item;
            } catch (InputMismatchException e) {
                System.out.println("◉_◉ ERROR: Please enter the number of the task you would like to edit.");
                in.nextLine();
            }
        }
    }

    private static String getFileName() {
        System.out.println("Enter the filename to load: ");
        String file = in.nextLine();
        return file;
    }

    private static TaskList read(String file) {
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
                }
                return temp;
            } catch (FileNotFoundException e) {
                System.out.println("(╯°□°）╯︵ ┻━┻ The file was not found. Please make sure you entered the name correctly.");
                in.nextLine();
            }
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
        if(title.length() < 1){
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

}
