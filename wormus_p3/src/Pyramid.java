public class Pyramid extends Shape3D{
    private double length;
    private double width;
    private double height;

    public Pyramid(double length, double width, double height){
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public String getName() {
        return "pyramid";
    }

    @Override
    public double getArea() {
        Shape2D shape = new Triangle(length, height);
        return (4 * shape.getArea()) + (length * width);
    }

    @Override
    public double getVolume() {
        return (length * width * height) / 3;
    }
}
