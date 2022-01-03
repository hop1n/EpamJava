package by.epam.lab;

import by.epam.lab.comparators.PurchaseComparator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PurchasesList {
    private List<Purchase> purchases;
    private final PurchaseComparator COMPARATOR = new PurchaseComparator();

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public PurchasesList() {
        this.purchases = new ArrayList<Purchase>();
    }

    public PurchasesList(String fileName) {
        this();
        String str = "";
        try (Scanner sc = new Scanner(new FileReader(fileName))) {
            while (sc.hasNextLine()) {
                try {
                    str = sc.nextLine();
                    String[] parts = str.split(Constants.DELIMITER);
                    if (parts.length >= Constants.NUMBER_OF_PURCHASE_INDEXES) {
                        if (allArgumentsNotNull(parts, str)) {
                            if (Integer.parseInt(parts[Constants.PRICE_INDEX]) > 0) {
                                if (parts.length == Constants.NUMBER_OF_PURCHASE_INDEXES) {
                                    purchases.add(new Purchase(parts[Constants.NAME_INDEX],
                                            parts[Constants.PRICE_INDEX],
                                            parts[Constants.NUMBER_INDEX]));
                                } else {
                                    if (parts.length ==
                                            Constants.NUMBER_OF_PURCHASE_DISCOUNT_INDEXES) {
                                        if (Integer.parseInt(parts[Constants.PRICE_INDEX]) -
                                                (Integer.parseInt(parts[Constants.DISCOUNT_INDEX])
                                                        * Integer.parseInt
                                                        (parts[Constants.NUMBER_INDEX])) > 0) {
                                            if (Integer.parseInt(parts[Constants.DISCOUNT_INDEX])
                                                    > 0) {
                                                purchases.add(new PriceDiscountPurchase
                                                        (parts[Constants.NAME_INDEX],
                                                        parts[Constants.PRICE_INDEX],
                                                        parts[Constants.NUMBER_INDEX],
                                                        parts[Constants.DISCOUNT_INDEX]));
                                            } else {
                                                System.err. println(str +
                                                        Constants.NON_POSITIVE_VALUE +
                                                        parts[Constants.DISCOUNT_INDEX] +
                                                        Constants.IN_DISCOUNT);
                                            }
                                        } else {
                                            System.err.println(str + Constants.WRONG_COST);
                                        }

                                    }
                                }
                            } else {
                                System.err.println(str + Constants.NON_POSITIVE_VALUE +
                                        parts[Constants.PRICE_INDEX] + Constants.IN_PRICE);
                            }
                        }
                    } else {
                        System.err.println(str + Constants.ARGUMENTS_MSG);
                    }
                } catch (NumberFormatException e) {
                    System.err.println(str + Constants.WRONG_FIELDS_NUMBER);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println(Constants.FILE_NOT_FOUND);
            purchases = new ArrayList<>();
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

    public void insertByIndex(int index, Purchase insertPurchase) {
        if (purchases.size() > index && index > 0) {
            purchases.add(index, insertPurchase);
        } else {
            if (index > purchases.size()) {
                purchases.add(insertPurchase);
            } else {
                purchases.add(0, insertPurchase);
            }
        }
    }

    public void deleteByIndexes(int startIndex, int endIndex) {
        int i = startIndex;
        try {
            for (i = startIndex; i <=endIndex; i++){
                purchases.remove(startIndex);
            }
        } catch (IndexOutOfBoundsException err) {
            System.out.println(Constants.WRONG_INDEX + i);
        }
    }

    public Byn getCost() {
        Byn cost = new Byn();
        for (Purchase purchase : purchases) {
            cost = cost.add(purchase.getCost());
        }
        return cost;
    }

    public void printPurchases() {
        for (Purchase purchase : purchases) {
            System.out.println(purchase);

        }
        System.out.println(Constants.TOTAL_COST_OUT + getCost());
    }

    public void purchaseSort() {
        purchases.sort(COMPARATOR);
    }

    public int searchAnElement(Purchase purchase) {
        return Collections.binarySearch(purchases, purchase, COMPARATOR);
    }
}
