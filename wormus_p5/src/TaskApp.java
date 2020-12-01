import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskApp {

    private TaskList Tlist;
    private Scanner in = new Scanner(System.in);

    public void taskMainMenu() {
        int input = App.displayMainMenu();
        switch (input) {
            case 1:
                Tlist = new TaskList();
                displayTaskOperationMenu(Tlist);
                break;
            case 2:
                Tlist = readTaskList(App.getFileName());
                displayTaskOperationMenu(Tlist);
                break;
            case 3:
                App.displayAppMenu();
                break;
        }
    }
    private void displayTaskOperationMenu(TaskList list) {
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
    private void taskOpMenu(int input, TaskList list) {
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
                if (list.getSize() > 0) {
                    list.view();
                    list.edit(editTaskItem(list), getTitle(), getDescription(), getdate());
                } else {
                    System.out.println("¯\\_(ツ)_/¯ You don't have any items to edit");
                }
                break;
            case 4:     //remove item
                if (list.getSize() > 0) {
                    list.view();
                    System.out.println("Which task would you like to remove: ");
                    list.remove(getTaskItem(list));
                } else {
                    System.out.println("¯\\_(ツ)_/¯ You don't have any items to remove");
                }

                break;
            case 5:     //mark item as completed
                if (list.getSize() > 0) {
                    list.view();
                    System.out.println("Which task would you like to complete: ");
                    list.markComplete(getTaskItem(list));
                } else {
                    System.out.println("¯\\_(ツ)_/¯ You don't have any items to complete");
                }

                break;
            case 6:     //unmark an item
                if (list.getSize() > 0) {
                    list.view();
                    System.out.println("Which task would you like to un-complete: ");
                    list.unmarkComplete(getTaskItem(list));
                } else {
                    System.out.println("¯\\_(ツ)_/¯ You don't have any items");
                }
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
    private TaskItem addTaskItem() {
        String title = getTitle();
        String desc = getDescription();
        LocalDate date = getdate();
        return new TaskItem(title, desc, date, false);
    }

    private int editTaskItem(TaskList list) {
        while (true) {
            try {
                System.out.println("Which item would you like to edit: ");
                int item = in.nextInt();
                in.nextLine();
                isTaskValid(item, list);
                return item;
            } catch (InvalidTaskSelection e) {
                System.out.println("◉_◉ ERROR: The task you entered doesn't exist yet. Press Y to continue or press x to go to Operation Menu.");
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

    private int getTaskItem(TaskList list) {
        while (true) {
            try {
                int item = in.nextInt();
                in.nextLine();
                isTaskValid(item, list);
                return item;
            } catch (InvalidTaskSelection e) {
                System.out.println("◉_◉ ERROR: The task you entered doesn't exist yet. Press Y to continue or press x to go to Operation Menu.");
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
        if(item >= list.getSize() || item <= 0){
            throw new InvalidTaskSelection("◉_◉ ERROR: The task you entered doesn't exist yet. PLease enter a valid task number.");
        }
    }

    private String getTitle() {
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

    private String getDescription() {
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

    private LocalDate getdate() {
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
    private TaskList readTaskList(String file) {
        while (true) {
            try {
                TaskList temp = new TaskList();
                File taskListFile = new File(file);
                Scanner infile = new Scanner(taskListFile);

                while (infile.hasNextLine()) {
                    String title = infile.nextLine();
                    String description = infile.nextLine();
                    String date = infile.nextLine();
                    boolean complete = Boolean.parseBoolean(infile.nextLine());
                    TaskItem newItem = new TaskItem(title, description, LocalDate.parse(date), complete);
                    temp.add(newItem);
                }
                System.out.println("File successfully loaded");
                return temp;
            } catch (FileNotFoundException e) {
                System.out.println("(╯°□°）╯︵ ┻━┻ The file was not found. Please enter the correct file name or press X to return to menu.");
                file = in.next();
                if(file.equalsIgnoreCase("x")){
                    taskMainMenu();
                }
            }
        }

    }
}
