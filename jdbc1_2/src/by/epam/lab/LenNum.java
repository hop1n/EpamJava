package by.epam.lab;


public class LenNum {
    final private int len;
    final private int num;

    public LenNum(int len, int num) {
        this.len = len;
        this.num = num;
    }

    public int getLen() {
        return len;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return len + Constants.DELIMITER + num;
    }
}
