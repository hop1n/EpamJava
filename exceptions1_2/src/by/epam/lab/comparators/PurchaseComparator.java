package by.epam.lab.comparators;

import by.epam.lab.PriceDiscountPurchase;
import by.epam.lab.Purchase;

import java.util.Comparator;

public class PurchaseComparator implements Comparator<Purchase> {

    @Override
    public int compare(Purchase p1, Purchase p2) {
        int comp = p1.getName().compareTo(p2.getName());
        if (comp == 0) {
            if (p1.getClass().equals(p2.getClass())) {
                comp = p1.getCost().compareTo(p2.getCost());
            } else {
                if (p1.getClass() == PriceDiscountPurchase.class) {
                    comp = 1;
                } else {
                    comp = -1;
                }
            }
        }
        return comp;
    }

}
