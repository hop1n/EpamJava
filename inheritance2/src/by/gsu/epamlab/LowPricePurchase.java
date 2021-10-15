package by.gsu.epamlab;

public class LowPricePurchase extends AbstractPurchase {
    private Byn discount;


    public LowPricePurchase(Product p, int n, Byn discount) {
        super(n, p);
        this.discount = discount;
    }

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
    }

    @Override
    public Byn getCost() {
        return new Byn(getProduct().getPrice()).sub(discount).mul(getNumber());
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }
}
