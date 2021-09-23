package by.gsu.epamlab;

public class Purchase implements Comparable<Purchase> {
    public static final String NAME = "ketchup";
    public static final int PRICE = 500;
    private int number;
    private double percent;
    private WeekDay day;

    public Purchase (){
        this(0,0,null);
    }

    public Purchase(int number, double percent, WeekDay days){
        this.number = number;
        this.percent = percent;
        this.day = days;
    }

    public Purchase(int number, double percent, int day){
        this(number, percent, WeekDay.values()[day]);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public WeekDay getDay() {
        return day;
    }

    public void setDay(WeekDay day) {
        this.day = day;
    }

    public int getCost(){
        return (int) Math.round(PRICE * number * (100 - percent) / 100 / 100) * 100;
    }

    @Override
    public int compareTo(Purchase purchase) {
        return this.number - purchase.number;
    }

    @Override
    public String toString() {
        return number + ";" + percent + ";" + KopsToByn.convert(getCost());
    }
}
