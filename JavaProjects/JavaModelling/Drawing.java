package veikkola;

import java.util.List;
import java.util.*;

public class Drawing {

    List<ShapeBasic> basicShapes = new ArrayList<ShapeBasic>();

    public Drawing() {
    }

    public void addBasicShape(ShapeBasic shape) {
        this.basicShapes.add(shape);
    }

    public List<ShapeBasic> getBasicShapes() {
        return this.basicShapes;
    }

    public void draw() {
        if (this.basicShapes.isEmpty()) {
            System.out.println("List is empty");
            return;
        } else {

            int leng = this.basicShapes.size();
            for (int i = 0; i < leng; i++) {
                basicShapes.get(i).draw();
            }
            System.out.println();
        }
    }

    public void showVisibility() {
        if (this.basicShapes.isEmpty()) {
            System.out.println("List is empty");
            return;
        } else {

            int leng = this.basicShapes.size();
            for (int i = 0; i < leng; i++) {
                ShapeBasic temp = basicShapes.get(i);
                if (temp.isVisible()) {
                    System.out.println(i + 1 + ". shape is visible: " + temp.printText());
                } else {
                    System.out.println(i + 1 + ". shape is in the background: " + temp.printText());
                }
            }
        }

    }

    public void grow() {
        if (this.basicShapes.isEmpty()) {
            System.out.println("List is empty");
            return;
        } else {
            int leng = this.basicShapes.size();
            for (int i = 0; i < leng; i++) {
                ShapeBasic temp = basicShapes.get(i);
                temp.grow();
            }
            System.out.println();
        }
    }
}