package veikkola;

public class AdapterMain {

    public AdapterMain() {

    }

    public static void main(String[] args) {
        Drawing drawing = new Drawing();

        Rectangle r1 = new Rectangle();
        r1.changeVisibility();
        drawing.addBasicShape(r1);

        Rectangle r2 = new Rectangle();
        drawing.addBasicShape(r2);

        Circle c1 = new Circle();
        drawing.addBasicShape(c1);

        Triangle t1 = new Triangle(1.0, 1.0, 1.0);
        t1.setColor("Red");
        ShapeAdapter sa1 = new ShapeAdapter(t1);
        sa1.changeVisibility();
        drawing.addBasicShape(sa1);


        ShapeDiamond sd1 = new ShapeDiamond(1.0,1.0);
        sd1.setColor("Blue");
        ShapeAdapter sa2 = new ShapeAdapter(sd1);
        sa2.changeVisibility();
        drawing.addBasicShape(sa2);


        ShapeDiamond sd2 = new ShapeDiamond(1.0, 1.0);
        sd2.setColor("Red");
        ShapeAdapter sa3 = new ShapeAdapter(sd2);
        drawing.addBasicShape(sa3);

        Triangle t2 = new Triangle(1.0,1.0,1.0);
        t2.setColor("Blue");
        ShapeAdapter sa4 = new ShapeAdapter(t2);
        drawing.addBasicShape(sa4);

        BrushStroke bs1 = new BrushStroke();
        drawing.addBasicShape(bs1);

        BrushStroke bs2 = new BrushStroke();
        bs2.changeVisibility();
        drawing.addBasicShape(bs2);




        drawing.draw();
        drawing.grow();
        drawing.showVisibility();

    }
}
