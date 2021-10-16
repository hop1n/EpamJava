package by.gsu.epamlab;

public class TransportPurchase extends AbstractPurchase {
    private Byn transportExpenses;

    public TransportPurchase(Product p, int n, Byn transportExpenses) {
        super(n, p);
        this.transportExpenses = transportExpenses;
    }

    public Byn getTransportExpenses() {
        return transportExpenses;
    }

    public void setTransportExpenses(Byn transportExpenses) {
        this.transportExpenses = transportExpenses;
    }

    @Override
    Byn unRoundedCost(){
        return getProduct().getPrice().mul(getNumber()).add(transportExpenses);
    }

    @Override
    public Byn getCost() {
        return getProduct().getPrice().mul(getNumber()).add(transportExpenses).round(RoundMethod.FLOOR,  2);
    }
}
