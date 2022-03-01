package by.epam.lab;


public class LenNum implements Comparable<LenNum> {
    private int len;
    private int num;

    public LenNum() {
        len = 0;
        num = 0;
    }

    public LenNum(int len, int num) {
        this.len = len;
        this.num = num;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public void setNum(int num) {
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

    @Override
    public int compareTo(LenNum o) {
        return len - o.len;
    }
}
