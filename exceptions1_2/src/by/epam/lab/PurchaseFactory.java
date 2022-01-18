package by.epam.lab;

import by.epam.lab.exceptions.*;
import by.epam.lab.exceptions.InvalidNumberOfArgumentsException;
import sun.awt.CausedFocusEvent;

import java.util.Scanner;

public class PurchaseFactory {
    private enum PurchaseKind {
        PURCHASE {
            Purchase getPurchase(String[] strings) {
                return new Purchase(strings[0], strings[1], strings[2]);
            }
        },
        PRICE_DISCOUNT_PURCHASE {
            Purchase getPurchase(String[] strings) {
                return new PriceDiscountPurchase(strings[0], strings[1], strings[2], strings[3]);
            }
        };

        abstract Purchase getPurchase(String[] strings);
    }

    public static Purchase getPurchaseFromFactory(String csvLine) throws CsvLineException {
        String[] parts = csvLine.split(Constants.DELIMITER);
        Purchase returnPurchase = null;
        try {
            if (!(parts.length >= Constants.NUMBER_OF_PURCHASE_INDEXES &&
                    parts.length <= Constants.NUMBER_OF_PURCHASE_DISCOUNT_INDEXES)) {
                throw new InvalidNumberOfArgumentsException(Causes.ARGUMENTS_EXCEPTION);
            }
            if (!(allArgumentsNotNull(parts, csvLine))) {
                throw new NullArgumentException(Causes.NULL_ARG);
            }
            if (parts[Constants.NAME_INDEX] == null || parts[Constants.NAME_INDEX].equals(Constants.EMPTY_LINE)) {
                throw new InvalidNameException(Causes.WRONG_NAME);
            }
            checkIfPositive(parts[Constants.PRICE_INDEX], Constants.PRICE);
            checkIfPositive(parts[Constants.NUMBER_INDEX], Constants.NUMBER);
            if (parts.length == Constants.NUMBER_OF_PURCHASE_INDEXES) {
                if (!(Integer.parseInt(parts[Constants.PRICE_INDEX]) *
                        Integer.parseInt(parts[Constants.NUMBER_INDEX]) > 0)) {
                    throw new NonPositiveArgumentException(Causes.NEGATIVE_COST);
                }
                returnPurchase = PurchaseKind.PURCHASE.getPurchase(parts);

            } else {
                checkIfPositive(parts[Constants.DISCOUNT_INDEX], Constants.DISCOUNT);
                if (!((Integer.parseInt(parts[Constants.PRICE_INDEX]) - Integer.parseInt(parts[Constants.DISCOUNT_INDEX])) *
                        Integer.parseInt(parts[Constants.NUMBER_INDEX]) > 0)) {
                    throw new NonPositiveArgumentException(Causes.NEGATIVE_COST);
                }
                returnPurchase = PurchaseKind.PRICE_DISCOUNT_PURCHASE.getPurchase(parts);
            }
            return returnPurchase;
        } catch (InvalidNumberOfArgumentsException | InvalidNameException
                | NullArgumentException | NonPositiveArgumentException
                | NegativeArgumentException e) {
            throw new CsvLineException(csvLine + e.getMessage());
        } catch (NumberFormatException e){
            throw new CsvLineException(csvLine + Causes.CSV_EXCEPTION);
        }
    }

    private static void checkIfPositive(String element, String fieldName){
        if (!(Integer.parseInt(element)>0)){
            throw new NonPositiveArgumentException(Causes.WRONG + fieldName);
        }
    }

    private static boolean allArgumentsNotNull(String[] parts, String str) {
        boolean b = true;
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].length() == 0) {
                b = false;
                String output;
                switch (i) {
                    case Constants.NAME_INDEX:
                        output = str + Constants.EMPTY_NAME;
                        break;
                    case Constants.PRICE_INDEX:
                        output = str + Constants.EMPTY_PRICE;
                        break;
                    case Constants.NUMBER_INDEX:
                        output = str + Constants.EMPTY_NUMBER;
                        break;
                    case Constants.DISCOUNT_INDEX:
                        output = str + Constants.EMPTY_DISCOUNT;
                        break;
                }
                break;
            }
        }
        return b;
    }
}

