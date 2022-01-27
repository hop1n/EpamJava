package by.epam.lab;

import by.epam.lab.exceptions.*;
import by.epam.lab.exceptions.InvalidNumberOfArgumentsException;
import sun.awt.CausedFocusEvent;

import java.util.Scanner;

public class PurchaseFactory {
    private enum PurchaseKind {
        PURCHASE {
            Purchase getPurchase(String[] strings) throws InvalidNameException, InvalidNumberOfArgumentsException {
                return new Purchase(strings); // тут и ниже левчук говорит использовать вместо конструктора с 4-мя стрингами
                                              // я переделал и у меня не работает инициализация в тестах
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
        String[] parts = csvLine.split(Constants.DELIMITER);
        Purchase returnPurchase;
        try {
            if (parts.length == Constants.NUMBER_OF_PURCHASE_INDEXES) {
                returnPurchase = PurchaseKind.PURCHASE.getPurchase(parts);
            } else {
                returnPurchase = PurchaseKind.PRICE_DISCOUNT_PURCHASE.getPurchase(parts);
            }
            return returnPurchase;
        } catch (InvalidNumberOfArgumentsException | InvalidNameException
                | NonPositiveArgumentException
                | NegativeArgumentException e) {
            throw new CsvLineException(csvLine + e.getMessage());
        } catch (NumberFormatException e){
            throw new CsvLineException(csvLine + Causes.CSV_EXCEPTION);
        }
    }
}

