import java.util.ArrayList;

public class App {
    //DO NOT TOUGH OR YOU WILL BE CRUCIFIED
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

    private static boolean moreInput() {
        return true;
    }

    private static double getUserWeight() {
        return 0;
    }

    private static double getUserHeight() {
        return 0;
    }

    public static void displayBmiInfo(BodyMassIndex bmi){

    }
    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData){

    }
}
