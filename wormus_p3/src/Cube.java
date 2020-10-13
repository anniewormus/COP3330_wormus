public class Cube extends Shape3D{
    private double length;

    public Cube(double length){
        this.length = length;
    }

    @Override
    public String getName() {
        return "cube";
    }

    @Override
    public double getArea() {
        Shape2D shape = new Square(length);
        return 6 * shape.getArea();
    }

    @Override
    public double getVolume() {
        return length*length*length;
    }
}
