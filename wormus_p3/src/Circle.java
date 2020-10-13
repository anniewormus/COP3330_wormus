public class Circle extends Shape2D {
    private double radius;

    //constructor
    public Circle(double radius){
        this.radius = radius;
    }

    @Override
    public String getName() {
        return "circle";
    }

    //area: pi * r^2
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}
