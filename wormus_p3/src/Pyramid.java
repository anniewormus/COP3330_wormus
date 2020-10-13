public class Pyramid extends Shape3D{
    private double length;
    private double width;
    private double height;

    //constructor
    public Pyramid(double length, double width, double height){
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public String getName() {
        return "pyramid";
    }

    //SA equation
    @Override
    public double getArea() {
        return (length * width) + (length * Math.sqrt(Math.pow((width/2), 2) + (height*height)))+ (width * Math.sqrt(Math.pow((length/2), 2) + height*height)); //well that was kinda rude
    }

    //volume: l*w*h/3
    @Override
    public double getVolume() {
        return (length * width * height)/3;
    }
}
