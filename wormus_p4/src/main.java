/* What up it's Andrea Wormus
 * 11/12/2020
 * This program ahhh I'll do this later
 */

import java.util.Scanner;

public class main {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args){
        mainMenu();

    }
    private static void mainMenu(){

        while(true){
            try{
                System.out.println("MAIN MENU\n1. Create a new task list\n2. Load an existing task list\n3. Exit\n");
                int choice = in.nextInt();
                mainMenuOpt(choice);
                break;
            }catch(InvalidOptionException e){
               System.out.println("ERROR: The option you entered was not one of the choices. Please try again.");
            }
        }
    }
    private static void mainMenuOpt(int input){
        if(!isMainMenuOptValid(input)){
            throw new InvalidOptionException("your choice is not valid; please choose one of the options");
        }
    }
    private static boolean isMainMenuOptValid(int input){
        return input == 1 || input == 2 || input == 3;
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
