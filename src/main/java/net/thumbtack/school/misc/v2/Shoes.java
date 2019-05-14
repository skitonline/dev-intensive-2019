package net.thumbtack.school.misc.v2;

import net.thumbtack.school.iface.v2.Colored;

import java.util.Objects;

public class Shoes implements Colored {
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int size, color;

    public boolean getIsClean() {
        return isClean;
    }

    private boolean isClean;

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

    public Shoes(int color, int size){
        this.color = color;
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
