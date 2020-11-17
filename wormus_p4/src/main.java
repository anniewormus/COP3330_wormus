/* What up it's Andrea Wormus
 * 11/12/2020
 * This program ahhh I'll do this later
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {

    private static Scanner in = new Scanner(System.in);
    private static TaskList list;

    public main(){
        list = new TaskList();
    }

    public static void main(String[] args){
        displayMainMenu();
    }

    private static void displayMainMenu(){
        int choice;

        while(true){
            try{
                System.out.println("MAIN MENU\n1. Create a new task list\n2. Load an existing task list\n3. Exit\n");
                choice = in.nextInt();
                mainMenuChoice(choice);
                mainMenu(choice);
                break;
            }catch(InvalidOptionException e){
               System.out.println("ERROR: The option you entered was not one of the choices. Please try again.");
            }catch(InputMismatchException e){
                System.out.println("ERROR: The option you entered was not one of the choices. Please try again.");
                in.next();
            }
        }
    }
    private static void mainMenu(int choice){
        //TaskList list = null;
        switch(choice){
            case 1:
                //list = new TaskList();
                displayOperationMenu(list);
                //create new task
                break;
            case 2:
                    list = read(getFileName());
                    displayOperationMenu(list);
                //load an existing task
                break;
            case 3:
                //exit
                break;
        }
    }
    private static void mainMenuChoice(int input){
        if(!isMainMenuValid(input)){
            throw new InvalidOptionException("your choice is not valid; please choose one of the options");
        }
    }
    private static boolean isMainMenuValid(int input){
        return input == 1 || input == 2 || input == 3;
    }

    private static void displayOperationMenu(TaskList list){
        int choice;
        while(true){
            try{
                System.out.println("OPERATION MENU\n1. View list\n2. Add a task\n" +
                        "3. Edit a task\n4. Remove a task\n5. Mark task as completed\n" +
                        "6. Unmark task as complete\n7. Save current list\n8. Exit to main menu");
                choice = in.nextInt();
                opMenuChoice(choice, list);
                break;
            }catch(InvalidOptionException e){
                System.out.println("ERROR: The option you entered was not one of the choices. Please try again.");
            }catch(InputMismatchException e){
                System.out.println("ERROR: The option you entered was not one of the choices. Please try again.");
                in.next();
            }
        }
    }
    private static void opMenuChoice(int input, TaskList list){
        if(!isOpMenuValid(input)){
            throw new InvalidOptionException("your choice is not valid; please choose one of the options");
        }else{
            switch(input){
                case 1:     //view list
                    list.view();
                    break;
                case 2:     //add item
                    list.add(getTaskItem());
                    break;
                case 3:     //edit item
                    list.view();
                    list.edit(getTaskItem());
                    break;
                case 4:     //remove item
                    list.view();
                    list.remove(getTaskItem);
                    break;
                case 5:     //mark item as completed
                    list.view();
                    list.markComplete(getTaskItem);
                    break;
                case 6:     //unmark an item
                    list.view();
                    list.unmarkComplete(getTaskItem);
                    break;
                case 7:     //save current list
                    break;
                case 8:     //quit to main menu
                    break;
            }
        }
    }
    private static boolean isOpMenuValid(int input){
        return input > 0 || input < 9;
    }
    private static String getFileName(){
        System.out.println("Enter the filename to load: ");
        String file = in.nextLine();
        return file;
    }
    private static TaskList read(String file){
        TaskList temp = new TaskList();
        try{
            File taskListFile = new File(file);
            Scanner in = new Scanner(taskListFile);

            while(in.hasNext()){
                String title = in.next();
                String description = in.next();
                LocalDate date = LocalDate.parse(in.next());
                boolean complete = Boolean.parseBoolean(in.next());
                TaskItem newItem = new TaskItem(title, description, date, complete);
                temp.add(newItem);
            }

        } catch(FileNotFoundException e){
            System.out.println("The file was not found. Please make sure you entered the name correctly.");
        }
        return temp;
    }
}
