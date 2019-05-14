package net.thumbtack.school.figures.v2;

import net.thumbtack.school.figures.v2.iface.HasArea;
import net.thumbtack.school.iface.v2.Movable;
import net.thumbtack.school.figures.v2.iface.Resizable;

public abstract class Figure implements HasArea, Movable, Resizable {
    abstract public boolean isInside(int x, int y);
    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }
    abstract public double getPerimeter();
}
