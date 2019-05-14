package net.thumbtack.school.figures.v3;

import net.thumbtack.school.iface.v3.Stretchable;

import java.util.Objects;

public class Ellipse extends Figure implements Stretchable {
    public void setCenter(Point center) {
        this.center = center;
    }

    private Point center;

    public int getXAxis() {
        return xAxis;
    }

    public void setXAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    private int xAxis;

    public int getYAxis() {
        return yAxis;
    }

    public void setYAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    private int yAxis;

    public Ellipse(Point center, int xAxis, int yAxis){
        this.center = center;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }
    public Ellipse(int xCenter, int yCenter, int xAxis, int yAxis){
        this(new Point(xCenter, yCenter), xAxis, yAxis);
    }
    public Ellipse(int xAxis, int yAxis){
        this(0, 0, xAxis, yAxis);
    }
    public Ellipse(){
        this (0,0,1,1);
    }

    public Point getCenter(){
        return center;
    }

    public void moveTo(int x, int y){
        center.setX(x);
        center.setY(y);
    }
    public void moveRel(int dx, int dy){
        center.moveRel(dx, dy);
    }

    public void resize(double ratio){
        stretch(ratio, ratio);
    }
    public void stretch(double xRatio, double yRatio){
        xAxis = (int)(xAxis * xRatio);
        yAxis = (int)(yAxis * yRatio);
    }

    public double getArea(){
        return Math.PI * xAxis * yAxis / 4.0;
    }
    public double getPerimeter(){
        return 2 * Math.PI * Math.sqrt((xAxis * xAxis + yAxis * yAxis) / 8.0);
    }

    public boolean isInside(int x, int y){
        double k1 = (x - center.getX()) * (x - center.getX()) / (xAxis * xAxis / 4.0);
        double k2 = (y - center.getY()) * (y - center.getY()) / (yAxis * yAxis / 4.0);
        return k1 + k2 <= 1.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ellipse ellipse = (Ellipse) o;
        return xAxis == ellipse.xAxis &&
                yAxis == ellipse.yAxis &&
                Objects.equals(center, ellipse.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, xAxis, yAxis);
    }
}
