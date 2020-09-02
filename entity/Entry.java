package leetcode.entity;

public class Entry {
    char c;
    int index;

    public Entry(int index, char c) {
        this.index = index;
        this.c = c;
    }

    public int getIndex() {
        return index;
    }

    public char getC() {
        return c;
    }
}
