package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.Figure;
import net.thumbtack.school.iface.v3.HasArea;

public class Box<T extends HasArea>{
    public Box(T content) {
        this.content = content;
    }

    public T getContent(){
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    private T content;

    public double getArea() {
        return content.getArea();
    }

    public boolean isAreaEqual(Box box){
        return content.getArea() == box.getArea();
    }

    static public boolean isAreaEqual(Box box1, Box box2){
        return box1.getArea() == box2.getArea();
    }
}
