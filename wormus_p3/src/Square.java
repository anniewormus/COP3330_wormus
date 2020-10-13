public class Square extends Shape2D {
    private double length;

    //constructor
    public Square(double length){
        this.length = length;
    }

    @Override
    public String getName() {
        return "square";
    }

    //area: length^2
    @Override
    public double getArea() {
        return length*length;
    }
}
