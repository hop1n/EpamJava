package by.gsu.epamlab;

public class TransportPurchase extends AbstractPurchase {
    private Byn transportExpenses;

    public TransportPurchase(Product product, int number, Byn transportExpenses) {
        super(number, product);
        this.transportExpenses = transportExpenses;
    }

    public Byn getTransportExpenses() {
        return transportExpenses;
    }

    public void setTransportExpenses(Byn transportExpenses) {
        this.transportExpenses = transportExpenses;
    }

    @Override
    Byn unRoundedCost(Byn cost){
        return cost.add(transportExpenses);
    }
}
