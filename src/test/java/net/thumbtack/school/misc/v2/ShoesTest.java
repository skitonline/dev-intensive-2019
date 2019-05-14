package net.thumbtack.school.misc.v2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShoesTest {
    @Test
    public void testShoes() {
        Shoes shoes = new Shoes(255, 44);
        Assert.assertEquals(255, shoes.getColor());
        Assert.assertEquals(44, shoes.getSize());
        Assert.assertTrue(shoes.getIsClean());
    }

    @Test
    public void toCleanAndWalking() {
        Shoes shoes = new Shoes(255, 44);
        shoes.walking(true);
        Assert.assertTrue(shoes.getIsClean());
        shoes.walking(false);
        Assert.assertFalse(shoes.getIsClean());
        shoes.toClean();
        Assert.assertTrue(shoes.getIsClean());
    }

    @Test
    public void equals1() {
        Shoes shoes1 = new Shoes(255, 44);
        Shoes shoes2 = new Shoes(255, 43);
        Shoes shoes3 = new Shoes(255, 44);
        Assert.assertEquals(shoes1, shoes3);
        Assert.assertNotEquals(shoes1, shoes2);
    }
}