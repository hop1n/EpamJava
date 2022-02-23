import by.epam.lab.Constants;
import by.epam.lab.EntryChecker;
import by.epam.lab.beans.Byn;
import by.epam.lab.beans.PricePurchase;
import by.epam.lab.beans.Purchase;
import by.epam.lab.beans.WeekDay;
import by.epam.lab.services.PurchaseFactory;

import java.io.FileReader;
import java.util.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        Map<Purchase, WeekDay> firstPurchase = new HashMap<>();
        Map<Purchase, WeekDay> lastPurchase = new HashMap<>();
        Map<WeekDay, List<Purchase>> dayPurchasesMap = new HashMap<>();
        List<PricePurchase> priceDiscountPurchases = new ArrayList<>();
        try (Scanner sc = new Scanner(new FileReader(Constants.PATH))) {
            while (sc.hasNextLine()) {
                String csvLine = sc.nextLine();
                Purchase purchase = PurchaseFactory.getPurchaseFromFactory(csvLine);
                WeekDay day = WeekDay.valueOf(sc.nextLine());
                lastPurchase.put(purchase, day);
                if (!(firstPurchase.containsKey(purchase))) {
                    firstPurchase.put(purchase, day);
                }
                if (!dayPurchasesMap.containsKey(day)) {
                    dayPurchasesMap.put(day, new ArrayList<>());
                }
                dayPurchasesMap.get(day).add(purchase);
                if (purchase instanceof PricePurchase) {
                    priceDiscountPurchases.add((PricePurchase) purchase);
                }
            }
            printMap(lastPurchase, Constants.LAST_PURCHASE_MAP);
            printMap(firstPurchase, Constants.FIRST_PURCHASE_MAP);
            findAndShow(firstPurchase, new Purchase("bread", new Byn(155), 3), "bread;155");
            findAndShow(lastPurchase, new Purchase("bread", new Byn(155), 3), "bread;155");
            findAndShow(firstPurchase, new Purchase("bread", new Byn(170), 4), "bread;170");
            removeEntries(lastPurchase, new EntryChecker<Purchase, WeekDay>() {
                @Override
                public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                    return Constants.MEAT.equals(entry.getKey().getName());
                }
            });
            removeEntries(firstPurchase, new EntryChecker<Purchase, WeekDay>() {

                @Override
                public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                    return WeekDay.FRIDAY.toString().equals(entry.getKey().getName());
                }
            });
            System.out.println(Constants.OUTPUT_AFTER_DELETING);
            printMap(firstPurchase, Constants.FIRST_PURCHASE_MAP);
            printMap(lastPurchase, Constants.LAST_PURCHASE_MAP);
            System.out.println(getTotalListCost(priceDiscountPurchases));
            printMap(dayPurchasesMap, Constants.ENUMERATED_MAP);
            for (Map.Entry<WeekDay, List<Purchase>> entry : dayPurchasesMap.entrySet()) {
                System.out.println(Constants.OUTPUT_COST_EACH_DAY + entry.getKey() + " " +
                        getTotalListCost(entry.getValue()));
            }
            System.out.println(Constants.MONDAY_PURCHASES + dayPurchasesMap.get(WeekDay.MONDAY));
            removeEntries(dayPurchasesMap, new EntryChecker<WeekDay, List<Purchase>>() {
                @Override
                public boolean check(Map.Entry<WeekDay, List<Purchase>> entry) {
                    boolean b = false;
                    for (Purchase purchase : entry.getValue()) {
                        if (Constants.MILK.equals(purchase.getName())) {
                            b = true;
                            break;
                        }
                    }
                    return b;
                }
            });
            printMap(dayPurchasesMap, Constants.DAY_PURCHASES_MAP);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private static <K, V> void removeEntries(Map<K, V> map, EntryChecker<K, V> checker) {
        for (Iterator<Map.Entry<K, V>> it = map.entrySet().iterator(); it.hasNext(); ) {
            if (checker.check(it.next())) {
                it.remove();
            }
        }
    }

    private static Byn getTotalListCost(List<? extends Purchase> list) {
        Byn sum = new Byn(0);
        for (Purchase purchase : list) {
            sum = sum.add(purchase.getCost());
        }
        return sum;
    }

    private static <K, V> void findAndShow(Map<K, V> map, K searchKey, String header) {
        V requiredDay;
        requiredDay = map.get(searchKey);
        if (requiredDay == null) {
            System.out.println(Constants.REQUIRED_PURCHASE + header + Constants.NOT_FOUND);
        } else {
            System.out.println(Constants.REQUIRED_PURCHASE + header + Constants.DAY + requiredDay);
        }
    }

    private static <K, V> void printMap(Map<K, V> map, String mapType) {
        System.out.println(mapType);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry);
        }
    }

}
