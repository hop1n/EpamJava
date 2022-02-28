package by.epam.lab;


public class LenNum implements Comparable<LenNum>{
    private int len;
    private int num;

    public LenNum(){
        len = 0;
        num = 0;
    }

    public LenNum(int len, int num){
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
        return len + ";" + num;
    }

    @Override
    public int compareTo(LenNum o) {
        return len - o.len;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LenNum lenNum = (LenNum) o;
        return len == lenNum.len;
    }

    @Override
    public int hashCode() {
        int result = len;
        result = 31 * result + num;
        return result;
    }
}
