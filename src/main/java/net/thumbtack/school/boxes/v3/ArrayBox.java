package net.thumbtack.school.boxes.v3;

public class ArrayBox<T> {
    private T[] content;

    public T[] getContent() {
        return content;
    }

    public void setContent(T[] content) {
        this.content = content;
    }

    public ArrayBox(T[] content) {
        this.content = content;
    }

    public T getElement(int index){
        return content[index];
    }

    public void setElement(int index, T el){
        content[index] = el;
    }

    public boolean isSameSize(ArrayBox arrayBox){
        return content.length == arrayBox.content.length;
    }
}
