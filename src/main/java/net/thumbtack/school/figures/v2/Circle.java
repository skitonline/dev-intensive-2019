package net.thumbtack.school.figures.v2;

import java.util.Objects;

public class Circle extends Figure{
    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    private Point center;
    private int radius;

    public Circle(Point center, int radius){
        this.center = center;
        this.radius = radius;
    }
    public Circle(int xCenter, int yCenter, int radius){
        this(new Point(xCenter, yCenter), radius);
    }
    public Circle(int radius){
        this(0, 0, radius);
    }
    public Circle(){
        this(0, 0, 1);
    }

    public void moveTo(int x, int y){
        center.moveTo(x, y);
    }
    public void moveRel(int dx, int dy){
        center.moveRel(dx, dy);
    }

    public void resize(double ratio){
        radius = (int)(radius * ratio);
    }

    public double getArea(){
        return Math.PI * radius * radius;
    }
    public double getPerimeter(){
        return Math.PI * radius * 2;
    }

    public boolean isInside(int x, int y){
        return  Math.sqrt((center.getX() - x) * (center.getX() - x)
                + (center.getY() - y) * (center.getY() - y)) <= radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return radius == circle.radius &&
                Objects.equals(center, circle.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }
}
