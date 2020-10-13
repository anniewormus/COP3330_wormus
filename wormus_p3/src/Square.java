public class Square extends Shape2D {
    private double length;

    public Square(double length){
        this.length = length;
    }

    @Override
    public String getName() {
        return "square";
    }

    @Override
    public double getArea() {
        return length*length;
    }
}
