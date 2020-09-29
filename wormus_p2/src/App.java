/* Andrea Wormus
    09.28.2020
    This program calculates BMI and BMI category based on user input of weight and height values.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    //NO TOUCHY
    public static void main(String[] args) {
        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while (moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }

        displayBmiStatistics(bmiData);
    }
    //scanner used throughout program
    public static Scanner in = new Scanner(System.in);

    //sees if user wants to continue running
    private static boolean moreInput() {
        System.out.println("Would you like to calculate a BMI? (Y/N) ");
        String cont = in.nextLine();
        if(cont.equals("Y")) return true;
        else if(cont.equals("N")) {
            System.out.println("Goodbye!");
            return false;
        }else{
            System.out.println("Sorry I didn't catch that. Goodbye!");
            return false;
        }
    }

    private static double getUserWeight() {
            System.out.println("Enter weight (in pounds): ");
            double weight = in.nextDouble();
            in.nextLine();
            //if an unacceptable negative value is entered
            while(weight < 0) {
                System.out.print("Wow what's your workout routine? Try again.");
                weight = in.nextDouble();
            }
        return weight;
    }

    private static double getUserHeight() {
        System.out.println("Enter height (in inches): ");
        double height = in.nextDouble();
        in.nextLine();
        //if an unacceptable negative value is entered
        while(height < 0) {
            System.out.print("How's the weather down there? Try again.");
            height = in.nextDouble();
        }
        return height;
    }

    //prints current bmi info depending on height and weight
    public static void displayBmiInfo(BodyMassIndex bmi){
        System.out.println("Your BMI is: " + bmi.getBMI() +
                            "\nYou are " + bmi.getCategories());

    }
    //prints average bmi of all bmi's entered
    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData) {
        double add = 0;
        double count = bmiData.size();
        for (int i = 0; i < count; i++) {
            BodyMassIndex current = bmiData.get(i);
            add += current.getBMI();
        }
        double avgBMI = add / count;
        System.out.println("Average BMI is " + avgBMI);
    }
}
