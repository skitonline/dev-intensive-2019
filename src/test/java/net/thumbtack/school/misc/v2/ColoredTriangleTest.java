package net.thumbtack.school.misc.v2;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.figures.v3.Point;
import net.thumbtack.school.misc.v3.ColoredTriangle;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColoredTriangleTest {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testColoredTriangle1() throws ColorException {
        Point left = new Point(3, 2);
        Point right = new Point(7, 5);
        Point top = new Point(0, 0);
        ColoredTriangle coloredTriangle = new ColoredTriangle(left, right, top, Color.BLUE);
        Assert.assertEquals(3, coloredTriangle.getLeft().getX());
        Assert.assertEquals(2, coloredTriangle.getLeft().getY());
        Assert.assertEquals(7, coloredTriangle.getRight().getX());
        Assert.assertEquals(5, coloredTriangle.getRight().getY());
        Assert.assertEquals(0, coloredTriangle.getTop().getX());
        Assert.assertEquals(0, coloredTriangle.getTop().getY());
        assertEquals(Color.BLUE, coloredTriangle.getColor());
        coloredTriangle.setColor("GREEN");
        assertEquals(Color.GREEN, coloredTriangle.getColor());
        Assert.assertEquals(0.5, coloredTriangle.getArea(), DOUBLE_EPS);
    }

    @Test(expected = ColorException.class)
    public void testColoredTriangle2() throws ColorException {
        Point left = new Point(3, 2);
        Point right = new Point(7, 5);
        Point top = new Point(0, 0);
        ColoredTriangle coloredTriangle = new ColoredTriangle(left, right, top, (Color)null);
    }

    @Test
    public void equals1() throws ColorException {
        ColoredTriangle coloredTriangle1 =
                new ColoredTriangle(1, 2, 6, 4, 2, 11, Color.BLUE);
        ColoredTriangle coloredTriangle2 =
                new ColoredTriangle(1, 2, 6, 4, 2, 15, Color.BLUE);
        ColoredTriangle coloredTriangle3 =
                new ColoredTriangle(1, 2, 6, 4, 2, 11, Color.BLUE);
        Assert.assertEquals(coloredTriangle1, coloredTriangle3);
        Assert.assertNotEquals(coloredTriangle1, coloredTriangle2);
    }
}