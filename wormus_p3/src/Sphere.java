public class Sphere extends Shape3D{
    private double radius;

    //constructor
    public Sphere(double radius){
        this.radius = radius;
    }

    @Override
    public String getName() {
        return "sphere";
    }

    //SA: 4*pi*r^2
    @Override
    public double getArea() {
        Shape2D shape = new Circle(radius);
        return 4.0 * shape.getArea();
    }

    //volume: 4/3 * pi * r^3
    @Override
    public double getVolume() {
        return (4.0/3.0)*Math.PI*Math.pow(radius, 3.0);
    }
}
