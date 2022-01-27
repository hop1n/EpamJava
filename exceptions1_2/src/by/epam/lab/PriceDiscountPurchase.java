package by.epam.lab;
import by.epam.lab.exceptions.InvalidNameException;
import by.epam.lab.exceptions.InvalidNumberOfArgumentsException;

public final class PriceDiscountPurchase extends Purchase {
    private final Byn discount;

    public Byn getDiscount() {
        return discount;
    }

    public PriceDiscountPurchase(PriceDiscountPurchase p) throws InvalidNameException {
        super(p.getName(), p.getPrice(), p.getNumber());
        discount = p.discount;
    }
    public PriceDiscountPurchase(String[] strings) throws InvalidNameException, InvalidNumberOfArgumentsException {
        super(strings);
        CheckIfPositive.check(strings[Constants.DISCOUNT_INDEX], Constants.DISCOUNT);
        this.discount = new Byn(Integer.parseInt(strings[Constants.DISCOUNT_INDEX]));
        CheckIfPositive.check(getCost(), Constants.TOTAL_COST);
    }

    public PriceDiscountPurchase(String name, Byn price, int number, Byn discount){
        super(name, price, number);
        this.discount = discount;
    }

    @Override
    public Purchase getPurchaseClone(){
        return new PriceDiscountPurchase(getName(), new Byn(getPrice()), getNumber(), new Byn(discount));
    }

    @Override
    public Byn getCost() {
        return getPrice().sub(discount).mul(getNumber());
    }

    protected String fieldsToString() {
        return super.fieldsToString() + discount;
    }
}