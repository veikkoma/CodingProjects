package veikkola;

import java.text.DecimalFormat;

public class ShapeDiamond implements ShapeSpecial, CanBeColored {
    private double a;
    private double b;
    private DecimalFormat df2 = new DecimalFormat("#.##");

    private String color = "No color";


    public ShapeDiamond(double a1, double b1){
        this.a = a1;
        this.b = b1;
    }

    public ShapeDiamond() {
    }

    public double calculateArea () {
        return this.a*this.b;
    }

    public double calculatePerimeter () {
        return (this.a+this.b)*2;
    }

    public void drawShape () {
        System.out.println("Drawing a diamond shape with area: "+ df2.format(this.calculateArea()) + " and perimeter: "+df2.format(this.calculatePerimeter()) + ". Color: " + this.getColor());
    }

    public boolean setColor(String scolor){
        if (scolor.equals("Red") || scolor.equals("Blue")) {
            this.color = scolor;
            return true;
        } else {
            return false;
        }
    }

    public String getColor(){
        return this.color;
    }
}

