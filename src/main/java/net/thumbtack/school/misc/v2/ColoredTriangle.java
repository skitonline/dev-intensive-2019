package net.thumbtack.school.misc.v2;

import net.thumbtack.school.figures.v2.Point;
import net.thumbtack.school.figures.v2.iface.HasArea;
import net.thumbtack.school.iface.v2.Colored;

import java.util.Objects;

public class ColoredTriangle implements HasArea, Colored {
    public Point getLeft() {
        return left;
    }

    public void setLeft(Point left) {
        this.left = left;
    }

    public Point getRight() {
        return right;
    }

    public void setRight(Point right) {
        this.right = right;
    }

    public Point getTop() {
        return top;
    }

    public void setTop(Point top) {
        this.top = top;
    }

    private Point left, right, top;
    private int color;

    public ColoredTriangle(Point left, Point right, Point top){
        this.left = left;
        this.right = right;
        this.top = top;
    }

    public ColoredTriangle(int leftX, int leftY, int rightX, int rightY,
                           int topX, int topY){
        this(new Point(leftX, leftY), new Point(leftX, leftY),
                new Point(topX, topY));
    }

    @Override
    public double getArea() {
        int delta = (left.getX() - top.getX()) * (right.getY() - top.getY()) -
                (left.getY() - top.getY()) * (right.getX() - top.getX());
        return 0.5 * Math.abs(delta);
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColoredTriangle that = (ColoredTriangle) o;
        return color == that.color &&
                Objects.equals(left, that.left) &&
                Objects.equals(right, that.right) &&
                Objects.equals(top, that.top);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, top, color);
    }
}
