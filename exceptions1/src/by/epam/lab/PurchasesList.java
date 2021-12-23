package by.epam.lab;

import by.epam.lab.comparators.PurchaseComparatorV1;
import by.epam.lab.comparators.PurchaseComparatorV2;
import com.sun.beans.editors.IntegerEditor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PurchasesList {
    private final List<Purchase> purchases;
    private int errLines;
    private final PurchaseComparatorV1 COMPARATOR_V1 = new PurchaseComparatorV1();
    private final PurchaseComparatorV2 COMPARATOR_V2 = new PurchaseComparatorV2();

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public PurchasesList() {
        this.purchases = new ArrayList<Purchase>();
    }

    public PurchasesList(String fileName) {
        this();
        final String ARGUMENTS_MSG = " -> wrong number of arguments";
        final String FILE_NOT_FOUND = "Input file is not found";
        final String NON_POSITIVE_VALUE = " -> non positive value ";
        final String IN_PRICE = " in price";
        final String IN_DISCOUNT = " in discount";
        final String WRONG_COST = " the total cost of this purchase non positive";
        try (Scanner sc = new Scanner(new FileReader(fileName))) {
            while (sc.hasNextLine()) {
                try {
                    String str = sc.nextLine();
                    String[] parts = str.split(";");
                    if (parts.length >= 3) {
                        if (allArgumentsNotNull(parts, str)) {
                            if (Integer.parseInt(parts[1]) > 0) {
                                if (parts.length == 3) {
                                    purchases.add(new Purchase(parts[0], parts[1], parts[2]));
                                } else {
                                    if (parts.length == 4) {
                                        if (Integer.parseInt(parts[1]) - (Integer.parseInt(parts[3]) * Integer.parseInt(parts[2])) > 0) {
                                            if (Integer.parseInt(parts[3]) > 0) {
                                                purchases.add(new PriceDiscountPurchase(parts[0], parts[1], parts[2], parts[3]));
                                            } else {
                                                System.out.println(str + NON_POSITIVE_VALUE + parts[3] + IN_DISCOUNT);
                                            }
                                        } else {
                                            System.out.println(str + WRONG_COST);
                                        }

                                    }
                                }
                            } else {
                                System.out.println(str + NON_POSITIVE_VALUE + parts[1] + IN_PRICE);
                            }
                        }
                    } else {
                        System.out.println(str + ARGUMENTS_MSG);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("wrong number format");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }

    private static boolean allArgumentsNotNull(String[] parts, String str) {
        boolean b = true;
        final String EMPTY_NAME = " -> purchase name is empty";
        final String EMPTY_PRICE = " -> purchase price is empty";
        final String EMPTY_DISCOUNT = " -> purchase discount is empty";
        final String EMPTY_NUMBER = " -> th number of purchases is empty";
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].length() == 0) {
                b = false;
                switch (i) {
                    case 0:
                        System.out.println(str + EMPTY_NAME);
                        break;
                    case 1:
                        System.out.println(str + EMPTY_PRICE);
                        break;
                    case 2:
                        System.out.println(str + EMPTY_NUMBER);
                        break;
                    case 3:
                        System.out.println(str + EMPTY_DISCOUNT);
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

    public void deleteByIndex(int index) {
        try {
            purchases.remove(index);
        } catch (IndexOutOfBoundsException err) {
            System.out.println("wrong index" + index);
        }
    }

    public Byn getCost() {
        Byn cost = new Byn();
        for (Purchase purchase : purchases) {
            cost = cost.add(purchase.getCost());
        }
        return cost;
    }

    public void printTableOfPurchases() {
        for (Purchase purchase : purchases) {
            System.out.println(purchase);

        }
        System.out.println("Total cost: " + getCost());
    }

    public void purchaseSort(String comparatorVersion) {
        int i = 0;
        switch (comparatorVersion) {
            case "PurchaseComparatorV1":
                purchases.sort(COMPARATOR_V1);
            case "PurchaseComparatorV2":
                purchases.sort(COMPARATOR_V2);
        }
    }

    public int searchAnElement(Purchase purchase) { /////???
        return Collections.binarySearch(purchases, purchase, COMPARATOR_V2);
    }
}
