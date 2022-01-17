package by.epam.lab;

import by.epam.lab.exceptions.*;
import by.epam.lab.exceptions.InvalidNumberOfArgumentsException;

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
                throw new InvalidNumberOfArgumentsException(csvLine + " wrong number of arguments");
            }
            if (!(allArgumentsNotNull(parts, csvLine))) {
                throw new NullArgumentException(csvLine + " some argument is null");
            }
            if (parts[Constants.NAME_INDEX] == null || parts[Constants.NAME_INDEX].equals(Constants.EMPTY_LINE)) {
                throw new InvalidNameException(csvLine + " Invalid name");
            }
            if (!(Integer.parseInt(parts[Constants.PRICE_INDEX]) > 0)) {
                throw new NonPositiveArgumentException(csvLine + " wrong price value");
            }
            if (!(Integer.parseInt(parts[Constants.NUMBER_INDEX]) > 0)) {
                throw new NonPositiveArgumentException(csvLine + " wrong number of purchase");
            }
            if (parts.length == Constants.NUMBER_OF_PURCHASE_INDEXES) {
                if (!(Integer.parseInt(parts[Constants.PRICE_INDEX]) *
                        Integer.parseInt(parts[Constants.NUMBER_INDEX]) > 0)) {
                    throw new NonPositiveArgumentException(csvLine + " non positive total cost of purchase");
                }
                returnPurchase = PurchaseKind.PURCHASE.getPurchase(parts);

            } else {
                if (!(Integer.parseInt(parts[Constants.DISCOUNT_INDEX]) > 0)) {
                    throw new NonPositiveArgumentException(csvLine + " wrong discount value");
                }
                if (!((Integer.parseInt(parts[Constants.PRICE_INDEX]) - Integer.parseInt(parts[Constants.DISCOUNT_INDEX])) *
                        Integer.parseInt(parts[Constants.NUMBER_INDEX]) > 0)) {
                    throw new NonPositiveArgumentException(csvLine + " non positive total cost of purchase");
                }
                returnPurchase = PurchaseKind.PRICE_DISCOUNT_PURCHASE.getPurchase(parts);
            }
            return returnPurchase;
        } catch (InvalidNumberOfArgumentsException | InvalidNameException
                | NullArgumentException | NonPositiveArgumentException
                | NegativeArgumentException e) {
            throw new CsvLineException(e.getMessage());
        } catch (NumberFormatException e){
            throw new CsvLineException(csvLine + " wrong type of variable number, price or discount");
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

