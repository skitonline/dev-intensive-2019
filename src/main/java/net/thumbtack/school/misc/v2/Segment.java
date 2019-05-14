package net.thumbtack.school.misc.v2;

import net.thumbtack.school.figures.v2.Point;
import net.thumbtack.school.figures.v2.iface.Resizable;
import net.thumbtack.school.iface.v2.Movable;

import java.util.Objects;

//Отрезок
public class Segment implements Movable, Resizable {
    public Point getLeftPoint() {
        return leftPoint;
    }

    public void setLeftPoint(Point leftPoint) {
        this.leftPoint = leftPoint;
    }

    public Point getRightPoint() {
        return rightPoint;
    }

    public void setRightPoint(Point rightPoint) {
        this.rightPoint = rightPoint;
    }

    private Point leftPoint, rightPoint;

    public Segment(Point leftPoint, Point rightPoint) {
        this.leftPoint = leftPoint;
        this.rightPoint = rightPoint;
    }

    public Segment(int x1, int y1, int x2, int y2) {
        this.leftPoint = new Point(x1, y1);
        this.rightPoint = new Point(x2, y2);
    }

    public Segment(Point point) {
        this(new Point(), point);
    }

    public Segment(int x, int y) {
        this(new Point(x , y));
    }

    public Segment(int length) {
        this(0, 0, length, 0);
    }

    public double getLength() {
        int cathetus1 = (leftPoint.getX() - rightPoint.getX()) * (leftPoint.getX() - rightPoint.getX());
        int cathetus2 = (leftPoint.getY() - rightPoint.getY()) * (leftPoint.getY() - rightPoint.getY());
        return Math.sqrt(cathetus1 + cathetus2);
    }

    @Override
    public void resize(double ratio) {
        rightPoint = new Point((int) (rightPoint.getX() * ratio),
                (int) (rightPoint.getY() * ratio));
    }

    @Override
    public void moveTo(int x, int y) {
        rightPoint.moveRel(x - leftPoint.getX(), y - leftPoint.getY());
        leftPoint.moveTo(x, y);
    }

    @Override
    public void moveRel(int dx, int dy) {
        leftPoint.moveRel(dx, dy);
        rightPoint.moveRel(dx, dy);
    }

    public boolean isInside(int x, int y) {
        return (x - leftPoint.getX()) / ((double) rightPoint.getX() - leftPoint.getX())
                == (y - leftPoint.getY()) / ((double) rightPoint.getY() - leftPoint.getY());
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment = (Segment) o;
        return Objects.equals(leftPoint, segment.leftPoint) &&
                Objects.equals(rightPoint, segment.rightPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftPoint, rightPoint);
    }
}
