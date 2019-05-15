package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.Figure;
import net.thumbtack.school.iface.v3.HasArea;

public class PairBox<T extends Figure, S extends Figure> implements HasArea {
    public T getContentFirst() {
        return contentFirst;
    }

    public void setContentFirst(T contentFirst) {
        this.contentFirst = contentFirst;
    }

    public S getContentSecond() {
        return contentSecond;
    }

    public void setContentSecond(S contentSecond) {
        this.contentSecond = contentSecond;
    }

    public PairBox(T contentFirst, S contentSecond) {
        this.contentFirst = contentFirst;
        this.contentSecond = contentSecond;
    }

    private T contentFirst;
    private S contentSecond;

    @Override
    public double getArea() {
        return contentFirst.getArea() + contentSecond.getArea();
    }

    public boolean isAreaEqual(PairBox pairBox){
        return getArea() == pairBox.getArea();
    }

    static public boolean isAreaEqual(PairBox pairBox1, PairBox pairBox2){
        return pairBox1.getArea() == pairBox2.getArea();
    }
}
