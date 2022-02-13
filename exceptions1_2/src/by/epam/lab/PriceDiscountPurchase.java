package by.epam.lab;

import by.epam.lab.exceptions.NonPositiveArgumentException;

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
        this(getValidPriceDiscountPurchase(strings));
    }

    public PriceDiscountPurchase(String name, Byn price, int number, Byn discount) {
        super(name, price, number);
        if(discount.compareTo(this.getPrice()) >= 0) {
            throw new IllegalArgumentException(Causes.WRONG + Constants.DISCOUNT);
        }
        if (discount.compareTo(new Byn(0)) == 0){
            throw new NonPositiveArgumentException(Causes.WRONG + Constants.DISCOUNT);
        }
        this.discount = discount;
    }

    private static PriceDiscountPurchase getValidPriceDiscountPurchase(String[] fields) {
        if(fields.length != Constants.NUMBER_OF_PURCHASE_DISCOUNT_INDEXES) {
            throw new IllegalArgumentException(" wrong args number");
        }
        return new PriceDiscountPurchase(fields[Constants.NAME_INDEX], new Byn(Integer.parseInt(fields[Constants.PRICE_INDEX])),
                Integer.parseInt(fields[Constants.NUMBER_INDEX]), new Byn(Integer.parseInt(fields[Constants.DISCOUNT_INDEX])));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PriceDiscountPurchase that = (PriceDiscountPurchase) o;

        return discount.equals(that.discount);
    }
}