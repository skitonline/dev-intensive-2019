package net.thumbtack.school.misc.v2;

import net.thumbtack.school.figures.v2.Point;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ColoredTriangleTest {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testColoredTriangle() {
        Point left = new Point(3, 2);
        Point right = new Point(7, 5);
        Point top = new Point(0, 0);
        ColoredTriangle coloredTriangle = new ColoredTriangle(left, right, top);
        Assert.assertEquals(3, coloredTriangle.getLeft().getX());
        Assert.assertEquals(2, coloredTriangle.getLeft().getY());
        Assert.assertEquals(7, coloredTriangle.getRight().getX());
        Assert.assertEquals(5, coloredTriangle.getRight().getY());
        Assert.assertEquals(0, coloredTriangle.getTop().getX());
        Assert.assertEquals(0, coloredTriangle.getTop().getY());
        Assert.assertEquals(0.5, coloredTriangle.getArea(), DOUBLE_EPS);
    }

    @Test
    public void equals1() {
        ColoredTriangle coloredTriangle1 =
                new ColoredTriangle(1, 2, 6, 4, 2, 11);
        ColoredTriangle coloredTriangle2 =
                new ColoredTriangle(1, 2, 6, 4, 2, 15);
        ColoredTriangle coloredTriangle3 =
                new ColoredTriangle(1, 2, 6, 4, 2, 11);
        Assert.assertEquals(coloredTriangle1, coloredTriangle3);
        Assert.assertNotEquals(coloredTriangle1, coloredTriangle2);
    }
}