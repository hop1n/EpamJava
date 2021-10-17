package by.gsu.epamlab;

public class PercentDiscountPurchase extends AbstractPurchase {
    private double percent;
    private final int LIMIT = 2;

    public PercentDiscountPurchase(Product p, int n, double percent) {
        super(n, p);
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
    @Override
    Byn unRoundedCost(Byn cost){
        Byn purchasePrice = cost;
        if (getNumber() > LIMIT) {
            purchasePrice =  purchasePrice.mul(1 - percent / 100, RoundMethod.ROUND, 0);
        }
        return purchasePrice;
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + percent;
    }
}
