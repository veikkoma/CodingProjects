package veikkola;

public class ShapeAdapter extends Visibility implements ShapeBasic {

    private ShapeSpecial adadapter;

    public ShapeAdapter(ShapeSpecial adadapter) {
        this.adadapter = adadapter;
    }

    public void draw() {
        this.adadapter.drawShape();
    }

    public void grow() {
        System.out.println(printText() + ", cannot grow");
    }

    public String printText() {
        if (this.adadapter.getClass().equals((Triangle.class))){
            return "Triangle";
        } else if (this.adadapter.getClass().equals((ShapeDiamond.class))){
            return "ShapeDiamond";
        } else {
            return "Wrong kind of shape - not supported";
        }
    }
}