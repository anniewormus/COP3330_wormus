/* Andrea Wormus
 * 11/30/2020
 * This program allows the user to create either a to-do list or a contact list.
 * The items in either list are saved in a file so the user can pull up a previous list after
 * program completes.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        displayAppMenu();
    }

    public static void displayAppMenu(){
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

    public static void applicationMenu(int input){
        int choice;
        switch(input){
            case 1:
                TaskApp t = new TaskApp();
                t.taskMainMenu();
                break;
            case 2:
                ContactApp c = new ContactApp();
                c.contactMainMenu();
                break;
            case 3:
                System.exit(0);
        }
    }

    public static int displayMainMenu() {
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
    private static void isMenuValid(int input) {
        if (!(input == 1 || input == 2 || input == 3)) {
            throw new InvalidOptionException("◉_◉ ERROR: Your choice is not valid; please choose one of the options");
        }
    }

    public static String getFileName() {
        System.out.println("Enter the filename to load: ");
        String file = in.nextLine();
        return file;
    }
}
