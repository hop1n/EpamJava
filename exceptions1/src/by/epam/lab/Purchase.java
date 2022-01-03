package by.epam.lab;
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;
import java.lang.*;

public class Purchase implements Comparable<Purchase> {
    private String name;
    private final Byn price;
    private final int number;

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

    public Byn getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public Byn getCost() {
        return new Byn(price).mul(number);
    }

    @Override
    public String toString() {
        return fieldsToString() + Constants.DELIMITER + getCost();
    }

    protected String fieldsToString() {
        return name + Constants.DELIMITER + price + Constants.DELIMITER + number;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return name.equals(purchase.name) && price.equals(purchase.price);
    }
    public String getTableOfPurchases(){
        return name + Constants.DELIMITER + price + Constants.DELIMITER + number + Constants.DASH + getCost();
    }

    @Override
    public int compareTo(Purchase o) {
        return 0;
    }
}
