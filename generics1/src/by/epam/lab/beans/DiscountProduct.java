package by.epam.lab.beans;

public class DiscountProduct extends Product {
    private final Byn discount;

    public DiscountProduct(String name, Byn price, Byn discount) {
        super(name, price);
        this.discount = discount;
    }

    public Byn getDiscount() {
        return discount;
    }

    protected String fieldsToString() {
        return super.fieldsToString() + discount;
    }

}
