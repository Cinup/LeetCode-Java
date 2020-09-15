package leetcode.entity;

public class Entry {
    char c;
    int count;
    int index;
    double sum;

    public Entry(int count, int index) {
        this.count = count;
        this.index = index;
    }

    public Entry(int index, char c) {
        this.index = index;
        this.c = c;
    }

    public Entry(int count, double sum) {
        this.count = count;
        this.sum = sum;
    }

    public char getC() {
        return c;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIndex() {
        return index;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getAverage() {
        return sum / count;
    }
}
