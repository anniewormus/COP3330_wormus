/* What up it's Andrea Wormus
 * 11/12/2020
 * This program ahhh I'll do this later
 */

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
                    break;
                case 3:     //edit item
                    break;
                case 4:     //remove item
                    break;
                case 5:     //mark item as completed
                    break;
                case 6:     //unmark an item
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
    /* Main menu option with 3 options
        1) Make a new task list
        2) Load an existing task list
        3) Quit
       Operation menu 8 options
        1. View list
        2. Add task
        3. Edit task
        4. Remove task
        5. Mark task as completed
        6. Unmark task as completed
        7. save current list
        8. Exit to main menu

     */
    /*create an array list of task objects
    task object contains a title
        check that title has at least one or more characters before adding with
        an exception
    task object contains a description
        This is optional, task doesn't need to have any characters in it's description
    task object contains a due date
        Should be a string in the format YYYY-MM-DD
        Have a throws exception to catch any dates not in this format
        **if you want to be extra check that date has to be in the future/or today

    create new task list

     */

}
