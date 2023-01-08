package veikkola;

import java.text.DecimalFormat;

public class Triangle implements ShapeSpecial, CanBeColored {
    private double a;
    private double b;
    private double c;
    private DecimalFormat df2 = new DecimalFormat("#.##");
    private String color = "No color";

    public Triangle(){
    }

    public Triangle(double a1, double b1, double c1){
        this.a = a1;
        this.b = b1;
        this.c = c1;
    }

    public double calculateArea(){
        double p = (this.a+this.b+this.c)/2;
        return Math.sqrt(p*(p-this.a) * (p-this.b) * (p-this.c));
    }

    public double calculatePerimeter(){
        return this.a+this.b+this.c;
    }
    public void drawShape () {
        System.out.println("Drawing a triangle with area: "+ df2.format(this.calculateArea()) + " and perimeter: "+df2.format(this.calculatePerimeter()) + ". Color: " + this.getColor());
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
