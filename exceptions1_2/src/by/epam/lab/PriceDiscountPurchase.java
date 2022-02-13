package by.epam.lab;

import by.epam.lab.exceptions.InvalidNameException;
import by.epam.lab.exceptions.InvalidNumberOfArgumentsException;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import sun.rmi.server.InactiveGroupException;

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
        this.discount = discount;
    }

    private static PriceDiscountPurchase getValidPriceDiscountPurchase(String[] fields) {
        if(fields.length != Constants.NUMBER_OF_PURCHASE_DISCOUNT_INDEXES) {
            throw new ArrayIndexOutOfBoundsException(" wrong args number");
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

}