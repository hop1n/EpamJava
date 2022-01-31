package by.epam.lab;

import by.epam.lab.exceptions.*;
import by.epam.lab.exceptions.InvalidNumberOfArgumentsException;
import sun.awt.CausedFocusEvent;

import java.util.Scanner;

public class PurchaseFactory {
    private enum PurchaseKind {
        PURCHASE {
            Purchase getPurchase(String[] strings) throws InvalidNameException, InvalidNumberOfArgumentsException {
                return new Purchase(strings);
            }
        },
        PRICE_DISCOUNT_PURCHASE {
            Purchase getPurchase(String[] strings) throws InvalidNameException, InvalidNumberOfArgumentsException {
                return new PriceDiscountPurchase(strings);
            }
        };

        abstract Purchase getPurchase(String[] strings) throws InvalidNameException, InvalidNumberOfArgumentsException;
    }

    public static Purchase getPurchaseFromFactory(String csvLine) throws CsvLineException {
        try {
            String[] parts = csvLine.split(Constants.DELIMITER);
            return getPurchaseKind(parts.length).getPurchase(parts);
        } catch (InvalidNumberOfArgumentsException | InvalidNameException
                | NonPositiveArgumentException
                | NegativeArgumentException e) {
            throw new CsvLineException(csvLine + e.getMessage());
        } catch (NumberFormatException e) {
            throw new CsvLineException(csvLine + Causes.CSV_EXCEPTION);
        }
    }


    private static PurchaseKind getPurchaseKind(int length) {
        PurchaseKind returnPurchase;
        if (!(length >= Constants.NUMBER_OF_PURCHASE_INDEXES &&
                length <= Constants.NUMBER_OF_PURCHASE_DISCOUNT_INDEXES)) {
            throw new InvalidNumberOfArgumentsException(Causes.ARGUMENTS_EXCEPTION);
        }
        if (length == Constants.NUMBER_OF_PURCHASE_INDEXES) {
            returnPurchase = PurchaseKind.PURCHASE;
        } else {
            returnPurchase = PurchaseKind.PRICE_DISCOUNT_PURCHASE;
        }
        return returnPurchase;
    }
}

