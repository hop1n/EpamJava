package by.epam.lab;


public class LenNum {
    private int len;
    private int num;

    public LenNum() {
        len = 0;
        num = 0;
    }

    public LenNum(int len) {
        this.len = len;
        num = 1;
    }

    public void incNum() {
        num++;
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
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + len;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final LenNum other = (LenNum) obj;
        if (len != other.len)
            return false;
        other.num++;
        return true;
    }
}
