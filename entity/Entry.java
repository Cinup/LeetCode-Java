package leetcode.entity;

public class Entry {
    char c;
    int count;
    int index;

    public Entry(int count, int index) {
        this.count = count;
        this.index = index;
    }

    public Entry(int index, char c) {
        this.index = index;
        this.c = c;
    }

    public char getC() {
        return c;
    }

    public int getCount() {
        return count;
    }

    public int getIndex() {
        return index;
    }
}
