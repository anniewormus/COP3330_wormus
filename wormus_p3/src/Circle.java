public class Circle extends Shape2D {
    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }

    @Override
    public String getName() {
        return "circle";
    }

    @Override
    public double getArea() {
        return 3.1416 * radius * radius;
    }
}
