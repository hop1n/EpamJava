package by.epam.lab;
import java.util.Objects;
import java.util.Scanner;
import java.lang.*;

public class Purchase {
    private String name;
    private Byn price;
    private int number;

    public Purchase() {
        price = new Byn(0);
        number = 0;
    }

    public Purchase(String name, Byn price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public Purchase(String name, String price, String number) {
        this.name = name;
        this.price = new Byn(Integer.parseInt(price));
        this.number = Integer.parseInt(number);
    }

    public Purchase(Scanner sc) {
        this.name = sc.next();
        this.price = new Byn(sc);
        this.number = sc.nextInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byn getPrice() {
        return price;
    }

    public void setPrice(Byn price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Byn getCost() {
        return new Byn(price).mul(number);
    }

    @Override
    public String toString() {
        return String.format("%10s%10s",fieldsToString(), getCost());
    }

    protected String fieldsToString() {
        return String.format("%10s%10s%10d", name, price, number);
        //return name + ";" + price + ";" + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return name.equals(purchase.name) && price.equals(purchase.price);
    }
    public String getTableOfPurchases(){
        return String.format("%10s%10s%10d%10s%10s", name, price, number, "-", getCost());
    }
}
