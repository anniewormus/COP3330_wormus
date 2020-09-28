public class BodyMassIndex {
    public double h;
    public double w;
    public double BMI;

    //counstructor
    public BodyMassIndex(double height, double weight){
        h = height;
        w = weight;
    }
    public double getBMI(){
        double h2 = h * h;
        BMI = 703 * w / h2;
        return BMI;
    }
    public String getCategories(){
        if(BMI < 18.50){
            return "underweight";
        }else if (BMI >= 18.50 && BMI < 25.00){
            return "normal weight";
        }else if (BMI >= 25.00 && BMI < 30.00){
            return "overweight";
        }else if (BMI >= 30.00){
            return "obese";
        }
        return "";
    }
}
