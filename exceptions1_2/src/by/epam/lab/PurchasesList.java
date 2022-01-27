package by.epam.lab;

import by.epam.lab.comparators.PurchaseComparator;
import by.epam.lab.exceptions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PurchasesList {
    private List<Purchase> purchases;
    private Comparator<Purchase> comparator;
    private boolean isSorted = false;

    public List<Purchase> getPurchases() throws InvalidNameException {
        List<Purchase> newList = new ArrayList<>();
        for (Purchase purchase : purchases) {
            newList.add(purchase.getPurchaseClone());
        }
        return newList;
    }

    public PurchasesList() {
        this.purchases = new ArrayList<Purchase>();
    }

    public PurchasesList(String fileName, Comparator<Purchase> comparator) {
        this();
        this.comparator = comparator;
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
        isSorted = false;
    }

    public void deleteByIndexes(int startIndex, int endIndex) {
        if (startIndex > purchases.size() - 1 || startIndex < 0) {
            System.err.println(Constants.WRONG_INDEX + startIndex);
        }
        if (endIndex > purchases.size() - 1 || endIndex < 0) {
            System.err.println(Constants.WRONG_INDEX + endIndex);
        } else {
            purchases.removeAll(purchases.subList(startIndex, endIndex+1));
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
            s.append(purchase + "\n");
        }
        return s.toString();
    }

    public void purchasesSort() {
        isSorted = true;
        Collections.sort(purchases, comparator);
    }

    public int searchAnElement(Purchase purchase) {
        if (!isSorted) {
            Collections.sort(purchases, comparator);
        }
        return Collections.binarySearch(purchases, purchase, comparator);
    }
}
