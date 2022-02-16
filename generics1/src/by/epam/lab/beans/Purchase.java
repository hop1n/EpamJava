package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.services.RoundMethod;

public class Purchase {
    private final Object item;
    private final Number number;

    public Purchase() {
        this(null, 0);
    }

    public Purchase(Object item, Number number) {
        this.item = item;
        this.number = number;
    }

    public Object getItem() {
        return item;
    }

    public Number getNumber() {
        return number;
    }

    public Byn getCost() {
        Byn cost = new Byn();
        String type = item.getClass().getSimpleName();
        if (type.equals(Constants.PRODUCT)){
            cost = ((Product) item).getPrice().mul(number.doubleValue(), RoundMethod.ROUND, 0);
        }
        if (type.equals(Constants.DISCOUNT_PRODUCT)){
            cost = ((DiscountProduct) item).getPrice().mul(number.doubleValue(), RoundMethod.ROUND, 0);
        }
        if (type.equals(Constants.SERVICE)){
            cost = ((Service) item).getPrice().mul(number.doubleValue(), RoundMethod.ROUND, 0);
        }
        return cost;
    }
}
