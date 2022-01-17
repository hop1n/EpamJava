package by.epam.lab;

import by.epam.lab.comparators.PurchaseComparator;
import by.epam.lab.exceptions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PurchasesList {
    private List<Purchase> purchases;
    private final PurchaseComparator COMPARATOR = new PurchaseComparator();
    private boolean IS_SORTED = false;

    public List<Purchase> getPurchases() {
        List<Purchase> newList = new ArrayList<Purchase>(purchases.size());
        for(Purchase purchase : purchases) {
            if (purchase.getClass() == Purchase.class) {
                newList.add(new Purchase(purchase));
            } else {
                newList.add(new PriceDiscountPurchase((PriceDiscountPurchase) purchase));
            }
        }
        return newList;
    }

    public PurchasesList() {
        this.purchases = new ArrayList<Purchase>();
    }

    public PurchasesList(String fileName) {
        this();
        try (Scanner sc = new Scanner(new FileReader(fileName))) {
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                try {
                    purchases.add(PurchaseFactory.getPurchaseFromFactory(str));
                } catch (CsvLineException e) {
                    System.err.println(e.getMessage());
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println(Constants.FILE_NOT_FOUND);
            purchases = new ArrayList<>();
        }
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
        IS_SORTED = false;
    }

    public void deleteByIndexes(int startIndex, int endIndex) {
        if (startIndex > purchases.size() - 1 || startIndex < 0) {
            System.err.println(Constants.WRONG_INDEX + startIndex);
        }
        if (endIndex > purchases.size() - 1 || endIndex < 0) {
            System.err.println(Constants.WRONG_INDEX + endIndex);
        } else {
            for (int i = startIndex; i <= endIndex; i++) {
                purchases.remove(startIndex);
            }
        }
    }

    public Byn getTotalCost() {
        Byn cost = new Byn();
        for (Purchase purchase : purchases) {
            cost = cost.add(purchase.getCost());
        }
        return cost;
    }

    public String printPurchases() {
        StringBuilder s = new StringBuilder();
        for (Purchase purchase : purchases) {
            s.append(purchase);
        }
        return s.toString();
    }

    public void purchasesSort() {
        IS_SORTED = true;
        Collections.sort(purchases, COMPARATOR);
    }

    public int searchAnElement(Purchase purchase) {
        if (!IS_SORTED) {
            Collections.sort(purchases, COMPARATOR);
        }
        return Collections.binarySearch(purchases, purchase, COMPARATOR);
    }
}
