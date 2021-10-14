package by.gsu.epamlab;

import sun.java2d.cmm.ProfileDeferralInfo;

import java.util.Objects;
import java.util.Scanner;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase>{
    private Product product;
    private int number;

    public AbstractPurchase() {
        number = 0;
        product = new Product();
    }

    public AbstractPurchase(int number, Product product) {
        this.number = number;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Byn getCost() {
        return new Byn(product.getPrice()).mul(number);
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }

    protected String fieldsToString() {
        return product + ";" + number;
    }

    @Override
    public int compareTo(AbstractPurchase purchase) {
        return purchase.getCost().compareTo(getCost());
    }
}
