public class Triangle extends Shape2D {
    private double base;
    private double height;

    //constructor
    public Triangle(double base, double height){
        this.base = base;
        this.height = height;
    }

    @Override
    public String getName() {
        return "triangle";
    }

    //area: 0.5 * b * h
    @Override
    public double getArea() {
        return 0.5 * base * height;
    }
}
