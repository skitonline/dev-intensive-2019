package net.thumbtack.school.misc.v2;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.misc.v3.Shoes;
import org.junit.Assert;
import org.junit.Test;

public class ShoesTest {
    @Test
    public void testShoes() throws ColorException {
        Shoes shoes = new Shoes("BLUE", 44);
        Assert.assertEquals(Color.BLUE, shoes.getColor());
        Assert.assertEquals(44, shoes.getSize());
        Assert.assertTrue(shoes.getIsClean());
    }

    @Test
    public void toCleanAndWalking() throws ColorException {
        Shoes shoes = new Shoes(Color.BLUE, 44);
        shoes.walking(true);
        Assert.assertTrue(shoes.getIsClean());
        shoes.walking(false);
        Assert.assertFalse(shoes.getIsClean());
        shoes.toClean();
        Assert.assertTrue(shoes.getIsClean());
    }

    @Test
    public void equals1() throws ColorException {
        Shoes shoes1 = new Shoes(Color.BLUE, 44);
        Shoes shoes2 = new Shoes(Color.BLUE, 43);
        Shoes shoes3 = new Shoes(Color.BLUE, 44);
        Assert.assertEquals(shoes1, shoes3);
        Assert.assertNotEquals(shoes1, shoes2);
    }
}