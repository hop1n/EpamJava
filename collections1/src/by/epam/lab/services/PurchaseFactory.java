package by.epam.lab.services;

import by.epam.lab.Constants;
import by.epam.lab.beans.PricePurchase;
import by.epam.lab.beans.Purchase;

import java.util.Scanner;

public class PurchaseFactory {
    private enum PurchaseKind {
        PURCHASE {
            Purchase getPurchase(String[] strings) {
                return new Purchase(strings);
            }
        },
        PRICE_PURCHASE {
            Purchase getPurchase(String[] strings) {
                return new PricePurchase(strings);
            }
        };

        abstract Purchase getPurchase(String[] strings);
    }

    public static Purchase getPurchaseFromFactory(String csvLine) {
        String[] parts = csvLine.split(Constants.DELIMITER);
        return getPurchaseKind(parts.length).getPurchase(parts);
    }

    private static PurchaseKind getPurchaseKind(int length) {
        return PurchaseKind.values()[length - Constants.NUMBER_OF_PURCHASE_INDEXES];
    }
}
