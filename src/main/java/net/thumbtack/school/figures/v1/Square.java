package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Square {
    private Point topLeft, bottomRight;

    public Square(Point leftTop, int size){
        topLeft = leftTop;
        bottomRight = new Point(leftTop.getX() + size, leftTop.getY() + size);
    }
    public Square(int xLeft, int yTop, int size){
        this(new Point(xLeft, yTop), size);
    }
    public Square(int size){
        topLeft = new Point(0, -size);
        bottomRight = new Point(size, 0);
    }
    public Square(){
        topLeft = new Point(0, -1);
        bottomRight = new Point(1, 0);
    }

    public Point getTopLeft() {
        return topLeft;
    }
    public Point getBottomRight() {
        return bottomRight;
    }

    public void setTopLeft(Point topLeft) {
        bottomRight = new Point(topLeft.getX() + getLength(), topLeft.getY() + getLength());
        this.topLeft = topLeft;
    }

    public int getLength(){
        return bottomRight.getX() - topLeft.getX();
    }

    public void moveTo(int x, int y){
        bottomRight.moveTo(x + getLength(), y + getLength());
        topLeft.moveTo(x, y);
    }
    public void moveTo(Point point){
        moveTo(point.getX(), point.getY());
    }
    public void moveRel(int dx, int dy){
        topLeft.moveRel(dx, dy);
        bottomRight.moveRel(dx, dy);
    }

    public void resize(double ratio){
        int size = getLength();
        bottomRight.setX((int)(topLeft.getX() + size * ratio));
        bottomRight.setY((int)(topLeft.getY() + size * ratio));
    }

    public double getArea(){
        return getLength() * getLength();
    }
    public double getPerimeter(){
        return getLength() * 4;
    }

    public boolean isInside(int x, int y){
        return topLeft.getX() <= x && x <= bottomRight.getX()
                && topLeft.getY() <= y && y <= bottomRight.getY();
    }
    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }
    public boolean isIntersects(Square square){
        return isInside(square.topLeft) || isInside(square.bottomRight)
                || isInside(square.topLeft.getX(), square.bottomRight.getY())
                || isInside(square.bottomRight.getX(), square.topLeft.getY())
                || square.isInside(topLeft) || square.isInside(bottomRight)
                || square.isInside(topLeft.getX(), bottomRight.getY())
                || square.isInside(bottomRight.getX(), topLeft.getY());
    }
    public boolean isInside(Square square){
        return isInside(square.topLeft) && isInside(square.bottomRight)
                && isInside(square.topLeft.getX(), square.bottomRight.getY())
                && isInside(square.bottomRight.getX(), square.topLeft.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Objects.equals(topLeft, square.topLeft) &&
                Objects.equals(bottomRight, square.bottomRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, bottomRight);
    }
}
