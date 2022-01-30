package by.epam.lab;

import by.epam.lab.exceptions.InvalidNameException;
import by.epam.lab.exceptions.InvalidNumberOfArgumentsException;

public final class PriceDiscountPurchase extends Purchase {
    private final Byn discount;

    public Byn getDiscount() {
        return discount;
    }

    public PriceDiscountPurchase(PriceDiscountPurchase p) {
        super(p.getName(), p.getPrice(), p.getNumber());
        discount = p.discount;
    }

    public PriceDiscountPurchase(String[] strings) {
        this(strings[Constants.NAME_INDEX], new Byn(Integer.parseInt(strings[Constants.PRICE_INDEX])),
                Integer.parseInt(strings[Constants.NUMBER_INDEX]),
                new Byn(Integer.parseInt(strings[Constants.DISCOUNT_INDEX])));
    }

    public PriceDiscountPurchase(String name, Byn price, int number, Byn discount) {
        super(name, price, number);
        CheckIfPositive.check(discount, Constants.DISCOUNT);
        this.discount = discount;
        CheckIfPositive.check(getCost(), Constants.TOTAL_COST);
    }

    @Override
    public Purchase getPurchaseClone() {
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