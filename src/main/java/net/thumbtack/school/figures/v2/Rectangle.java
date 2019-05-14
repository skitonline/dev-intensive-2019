package net.thumbtack.school.figures.v2;

import net.thumbtack.school.figures.v2.iface.Stretchable;

import java.util.Objects;

public class Rectangle extends Figure implements Stretchable{
    private Point topLeft, bottomRight;

    public Rectangle(Point leftTop, Point rightBottom){
        topLeft = leftTop;
        bottomRight = rightBottom;
    }
    public Rectangle(int xLeft, int yTop, int xRight, int yBottom){
        topLeft = new Point(xLeft, yTop);
        bottomRight = new Point(xRight, yBottom);
    }
    public Rectangle(int length, int width){
        topLeft = new Point(0, -width);
        bottomRight = new Point(length, 0);
    }
    public Rectangle(){
        topLeft = new Point(0, -1);
        bottomRight = new Point(1, 0);
    }

    public Point getTopLeft(){
        return  topLeft;
    }
    public Point getBottomRight(){
        return bottomRight;
    }

    public void setTopLeft(Point topLeft){
        this.topLeft = topLeft;
    }
    public void setBottomRight(Point bottomRight){
        this.bottomRight = bottomRight;
    }

    public int getLength(){
        return bottomRight.getX() - topLeft.getX();
    }
    public int getWidth(){
        return bottomRight.getY() - topLeft.getY();
    }

    public void moveTo(int x, int y){
        bottomRight.moveTo(x + getLength(), y + getWidth());
        topLeft.moveTo(x, y);
    }
    public void moveRel(int dx, int dy){
        topLeft.moveRel(dx, dy);
        bottomRight.moveRel(dx, dy);
    }

    public void resize(double ratio){
        bottomRight.setX((int)(topLeft.getX() + getLength() * ratio));
        bottomRight.setY((int)(topLeft.getY() + getWidth() * ratio));
    }
    public void stretch(double xRatio, double yRatio){
        bottomRight.setX((int)(topLeft.getX() + getLength() * xRatio));
        bottomRight.setY((int)(topLeft.getY() + getWidth() * yRatio));
    }

    public double getArea(){
        return getLength() * getWidth();
    }
    public double getPerimeter(){
        return (getLength() + getWidth()) * 2;
    }

    public boolean isInside(int x, int y){
        return topLeft.getX() <= x && x <= bottomRight.getX()
                && topLeft.getY() <= y && y <= bottomRight.getY();
    }
    public boolean isIntersects(Rectangle rectangle){
        return isInside(rectangle.topLeft) || isInside(rectangle.bottomRight)
                || isInside(rectangle.topLeft.getX(), rectangle.bottomRight.getY())
                || isInside(rectangle.bottomRight.getX(), rectangle.topLeft.getY())
                || rectangle.isInside(topLeft) || rectangle.isInside(bottomRight)
                || rectangle.isInside(topLeft.getX(), bottomRight.getY())
                || rectangle.isInside(bottomRight.getX(), topLeft.getY());
    }
    public boolean isInside(Rectangle rectangle){
        return isInside(rectangle.topLeft) && isInside(rectangle.bottomRight)
                && isInside(rectangle.topLeft.getX(), rectangle.bottomRight.getY())
                && isInside(rectangle.bottomRight.getX(), rectangle.topLeft.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(topLeft, rectangle.topLeft) &&
                Objects.equals(bottomRight, rectangle.bottomRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, bottomRight);
    }
}
