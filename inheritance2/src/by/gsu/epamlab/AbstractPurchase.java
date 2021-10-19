package by.gsu.epamlab;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase> {
    private final Product product;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    abstract Byn unRoundedCost(Byn cost);

    public Byn getCost() {
        return unRoundedCost(product.getPrice().mul(number)).round(RoundMethod.FLOOR, 2);
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
