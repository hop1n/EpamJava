package by.epam.lab;
import by.epam.lab.exceptions.InvalidNameException;

public final class PriceDiscountPurchase extends Purchase {
    private final Byn discount;

    public Byn getDiscount() {
        return discount;
    }

//    public PriceDiscountPurchase(String[] strings) throws InvalidNameException {
//        this(strings[Constants.NAME_INDEX], strings[Constants.PRICE_INDEX],
//                strings[Constants.NUMBER_INDEX], strings[Constants.DISCOUNT_INDEX]);
//    }

    public PriceDiscountPurchase(PriceDiscountPurchase p) throws InvalidNameException {
        super(p.getName(), p.getPrice(), p.getNumber());
        discount = p.discount;
    }
    public PriceDiscountPurchase(String[] strings) throws InvalidNameException {
        super();
        CheckIfPositive.check(strings[Constants.DISCOUNT_INDEX], Constants.DISCOUNT);
        this.discount = new Byn(Integer.parseInt(strings[Constants.DISCOUNT_INDEX]));
    }

    @Override
    public Byn getCost() {
        return getPrice().sub(discount).mul(getNumber());
    }

    protected String fieldsToString() {
        return super.fieldsToString() + discount;
    }
}