package net.thumbtack.school.misc.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorErrorCode;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.iface.v3.Colored;

import java.util.Objects;

public class Shoes implements Colored {
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int size;
    private Color color;

    public boolean getIsClean() {
        return isClean;
    }

    private boolean isClean;

    @Override
    public void setColor(Color color) throws ColorException {
        if (color == null)
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.color = color;
    }

    @Override
    public void setColor(String colorString) throws ColorException {
        if (colorString == null)
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.color = Color.colorFromString(colorString);
    }

    @Override
    public Color getColor() {
        return color;
    }

    public Shoes(String color, int size) throws ColorException {
        setColor(color);
        this.size = size;
        isClean = true;
    }

    public Shoes(Color color, int size) throws ColorException {
        setColor(color);
        this.size = size;
        isClean = true;
    }

    //Чистим обувь
    public void toClean(){
        isClean = true;
    }

    //Если гуляем в плохую погоду, обувь пачкается
    public void walking(boolean goodWeather){
        if (!goodWeather)
            isClean = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shoes shoes = (Shoes) o;
        return size == shoes.size &&
                color == shoes.color &&
                isClean == shoes.isClean;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, color, isClean);
    }
}
