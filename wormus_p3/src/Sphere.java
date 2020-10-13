public class Sphere extends Shape3D{
    private double radius;

    public Sphere(double radius){
        this.radius = radius;
    }

    @Override
    public String getName() {
        return "sphere";
    }

    @Override
    public double getArea() {
        Shape2D shape = new Circle(radius);
        return 4.0 * shape.getArea();
    }

    @Override
    public double getVolume() {
        return (4.0/3.0)*Math.PI*Math.pow(radius, 3.0);
    }
}
