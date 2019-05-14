package net.thumbtack.school.misc.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorErrorCode;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.figures.v3.Point;
import net.thumbtack.school.iface.v3.HasArea;
import net.thumbtack.school.iface.v3.Colored;

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
    private Color color;

    public ColoredTriangle(Point left, Point right, Point top, String color) throws ColorException {
        this.left = left;
        this.right = right;
        this.top = top;
        setColor(color);
    }

    public ColoredTriangle(Point left, Point right, Point top, Color color) throws ColorException {
        this.left = left;
        this.right = right;
        this.top = top;
        setColor(color);
    }

    public ColoredTriangle(int leftX, int leftY, int rightX, int rightY,
                           int topX, int topY, String color) throws ColorException {
        this(new Point(leftX, leftY), new Point(leftX, leftY),
                new Point(topX, topY), color);
    }

    public ColoredTriangle(int leftX, int leftY, int rightX, int rightY,
                           int topX, int topY, Color color) throws ColorException {
        this(new Point(leftX, leftY), new Point(leftX, leftY),
                new Point(topX, topY), color);
    }

    @Override
    public double getArea() {
        int delta = (left.getX() - top.getX()) * (right.getY() - top.getY()) -
                (left.getY() - top.getY()) * (right.getX() - top.getX());
        return 0.5 * Math.abs(delta);
    }

    @Override
    public void setColor(Color color) throws ColorException {
        if (color == null)
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.color = color;
    }

    @Override
    public void setColor(String colorString) throws ColorException {
        if (colorString == null)
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.color = Color.colorFromString(colorString);
    }

    @Override
    public Color getColor() {
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
