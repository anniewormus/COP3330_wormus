public class Cube extends Shape3D{
    private double length;

    //constructor
    public Cube(double length){
        this.length = length;
    }

    @Override
    public String getName() {
        return "cube";
    }

    //SA: 6 * b * h
    @Override
    public double getArea() {
        Shape2D shape = new Square(length);
        return 6 * shape.getArea();
    }

    //volume : length^3
    @Override
    public double getVolume() {
        return Math.pow(length, 3);
    }
}
