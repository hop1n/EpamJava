package by.epam.lab;

import by.epam.lab.exceptions.*;
import by.epam.lab.exceptions.InvalidNumberOfArgumentsException;

public class PurchaseFactory {
    private enum PurchaseKind {
        PURCHASE {
            Purchase getPurchase(String[] strings) {
                return new Purchase(strings);
            }
        },
        PRICE_DISCOUNT_PURCHASE {
            Purchase getPurchase(String[] strings) {
                return new PriceDiscountPurchase(strings);
            }
        };

        abstract Purchase getPurchase(String[] strings);
    }

    public static Purchase getPurchaseFromFactory(String csvLine) throws CsvLineException {
        try {
            String[] parts = csvLine.split(Constants.DELIMITER);
            return getPurchaseKind(parts.length).getPurchase(parts);
        } catch (NumberFormatException e) {
            throw new CsvLineException(csvLine + Causes.CSV_EXCEPTION);
        } catch (IllegalArgumentException e) {
            throw new CsvLineException(csvLine + e.getMessage());
        }
    }

    private static PurchaseKind getPurchaseKind(int length) {
        try {
            return PurchaseKind.values()[length - Constants.NUMBER_OF_PURCHASE_INDEXES];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidNumberOfArgumentsException(Causes.ARGUMENTS_EXCEPTION);
        }
    }
}

