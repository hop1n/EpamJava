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
    public Byn getCost() {
        return super.getCost();
    }
}
