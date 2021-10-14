package by.gsu.epamlab;

public class Product {
    private String name;
    private final Byn PRICE;

    public Product(String name, Byn price) {
        this.name = name;
        this.PRICE = price;
    }

    public Product() {
        this.PRICE = new Byn();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byn getPrice() {
        return PRICE;
    }

    @Override
    public String toString() {
        return name + ";" + PRICE;
    }
}
