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

    public List<Purchase> getClonedPurchases() {
        List<Purchase> newList = new ArrayList<>();
        for (Purchase purchase : purchases) {
            newList.add(purchase.getPurchaseClone());
        }
        return newList;
    }

    public PurchasesList() {
        this.purchases = new ArrayList<Purchase>();
        this.comparator = new PurchaseComparator();
        isSorted = true;
    }

    public PurchasesList(String fileName, Comparator<Purchase> comparator) {
        this();
        this.comparator = comparator;
        try (Scanner sc = new Scanner(new FileReader(fileName))) {
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                try {
                    purchases.add(PurchaseFactory.getPurchaseFromFactory(str));
                    isSorted = false;
                } catch (CsvLineException e) {
                    System.err.println(e.getMessage());
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println(Constants.FILE_NOT_FOUND);
            purchases = new ArrayList<>();
            isSorted = true;
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

    public int deleteByIndexes(int startIndex, int endIndex) {
        int lengthBefore = purchases.size();
        if (startIndex > purchases.size() -1 ){
            startIndex = purchases.size() -1;
            System.err.println(Constants.SOME_INDEX_IS_WRONG);
        }
        if (endIndex < 0){
            endIndex = 0;
            System.err.println(Constants.SOME_INDEX_IS_WRONG);
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (endIndex > purchases.size() - 1) {
            endIndex = purchases.size();
        }
        purchases.subList(startIndex, endIndex).clear();
        return lengthBefore - purchases.size();
    }

    public Byn getTotalCost() {
        Byn cost = new Byn();
        for (Purchase purchase : purchases) {
            cost = cost.add(purchase.getCost());
        }
        return cost;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Purchase purchase : purchases) {
            s.append(purchase + "\n");
        }
        return s.toString();
    }

    public void purchasesSort() {
        Collections.sort(purchases, comparator);
        isSorted = true;
    }

    public int searchAnElement(Purchase purchase) {
        if (!isSorted) {
            Collections.sort(purchases, comparator);
            isSorted = true;
        }
        return Collections.binarySearch(purchases, purchase, comparator);
    }
}
