package net.thumbtack.school.misc.v2;

import net.thumbtack.school.figures.v3.Point;
import net.thumbtack.school.misc.v3.Segment;
import org.junit.Test;

import org.junit.Assert;

public class SegmentTest {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testSegment1() {
        Point leftPoint = new Point(3, 1);
        Point rightPoint = new Point(7, -2);
        Segment segment = new Segment(leftPoint, rightPoint);
        Assert.assertEquals(3, segment.getLeftPoint().getX());
        Assert.assertEquals(1, segment.getLeftPoint().getY());
        Assert.assertEquals(7, segment.getRightPoint().getX());
        Assert.assertEquals(-2, segment.getRightPoint().getY());
        Assert.assertEquals(5, segment.getLength(), DOUBLE_EPS);
    }

    @Test
    public void testSegment2() {
        Segment segment = new Segment(3, 1, 7, -2);
        Assert.assertEquals(3, segment.getLeftPoint().getX());
        Assert.assertEquals(1, segment.getLeftPoint().getY());
        Assert.assertEquals(7, segment.getRightPoint().getX());
        Assert.assertEquals(-2, segment.getRightPoint().getY());
        Assert.assertEquals(5, segment.getLength(), DOUBLE_EPS);
    }

    @Test
    public void testSegment3() {
        Point point = new Point(3, 4);
        Segment segment = new Segment(point);
        Assert.assertEquals(0, segment.getLeftPoint().getX());
        Assert.assertEquals(0, segment.getLeftPoint().getY());
        Assert.assertEquals(3, segment.getRightPoint().getX());
        Assert.assertEquals(4, segment.getRightPoint().getY());
        Assert.assertEquals(5, segment.getLength(), DOUBLE_EPS);
    }

    @Test
    public void testSegment4() {
        Segment segment = new Segment(3, 4);
        Assert.assertEquals(0, segment.getLeftPoint().getX());
        Assert.assertEquals(0, segment.getLeftPoint().getY());
        Assert.assertEquals(3, segment.getRightPoint().getX());
        Assert.assertEquals(4, segment.getRightPoint().getY());
        Assert.assertEquals(5, segment.getLength(), DOUBLE_EPS);
    }

    @Test
    public void testSegment5() {
        Segment segment = new Segment(10);
        Assert.assertEquals(0, segment.getLeftPoint().getX());
        Assert.assertEquals(0, segment.getLeftPoint().getY());
        Assert.assertEquals(10, segment.getRightPoint().getX());
        Assert.assertEquals(0, segment.getRightPoint().getY());
        Assert.assertEquals(10, segment.getLength(), DOUBLE_EPS);
    }

    @Test
    public void resize() {
        Segment segment = new Segment(3, 1, 7, -2);
        segment.resize(2);
        Assert.assertEquals(3, segment.getLeftPoint().getX());
        Assert.assertEquals(1, segment.getLeftPoint().getY());
        Assert.assertEquals(14, segment.getRightPoint().getX());
        Assert.assertEquals(-4, segment.getRightPoint().getY());
    }

    @Test
    public void moveTo() {
        Segment segment = new Segment(-2, -2, 4, 1);
        segment.moveTo(0, 0);
        Assert.assertEquals(0, segment.getLeftPoint().getX());
        Assert.assertEquals(0, segment.getLeftPoint().getY());
        Assert.assertEquals(6, segment.getRightPoint().getX());
        Assert.assertEquals(3, segment.getRightPoint().getY());
    }

    @Test
    public void moveRel() {
        Segment segment = new Segment(-2, -2, 4, 1);
        segment.moveRel(3, 3);
        Assert.assertEquals(1, segment.getLeftPoint().getX());
        Assert.assertEquals(1, segment.getLeftPoint().getY());
        Assert.assertEquals(7, segment.getRightPoint().getX());
        Assert.assertEquals(4, segment.getRightPoint().getY());
    }

    @Test
    public void isInside() {
        Segment segment = new Segment(5, 5);
        Assert.assertTrue(segment.isInside(0, 0));
        Assert.assertTrue(segment.isInside(5, 5));
        Assert.assertTrue(segment.isInside(2, 2));
        Assert.assertTrue(segment.isInside(new Point(2, 2)));
        Assert.assertFalse(segment.isInside(5, 6));
        Assert.assertFalse(segment.isInside(30, 2));
        Assert.assertFalse(segment.isInside(1, 0));
        Assert.assertFalse(segment.isInside(new Point(1, 0)));
    }

    @Test
    public void testEqualsSegment() {
        Segment segment1 = new Segment(10, 20, 30, 40);
        Segment segment2 = new Segment(10, 20, 30, 40);
        Segment segment3 = new Segment(20, 30, 40, 50);
        Assert.assertEquals(segment1, segment2);
        Assert.assertNotEquals(segment1, segment3);
    }
}