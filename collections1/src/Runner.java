import by.epam.lab.Constants;
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

    private static void deleteMapElementsByName(Map<Purchase, WeekDay> map, String name){
        Iterator<Purchase> it = map.keySet().iterator();
        while(it.hasNext()){
            if (name.equals(it.next().getName())){
                it.remove();
            }
        }
    }

    private static void deleteMapElementsByDay(Map<Purchase, WeekDay> map, WeekDay day){
        Iterator<Map.Entry<Purchase, WeekDay>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Purchase, WeekDay> entry = it.next();
            if (day.equals(entry.getValue())){
                it.remove();
            }
        }
    }

    private static Byn getPurchasesTotalCost(List<? extends Purchase> list){
        Byn sum = new Byn(0);
        for (Purchase purchase : list){
            sum = sum.add(purchase.getCost());
        }
        return sum;
    }

    private static void findPurchaseDay(Map<Purchase, WeekDay> map, Purchase purchase){
        WeekDay requiredDay;
        requiredDay = map.get(purchase);
        if (requiredDay == null){
            System.out.println(Constants.FAILED_SEARCH);
        }else{
            System.out.println(Constants.SUCCESSFUL_SEARCH +requiredDay);
        }
    }

    private static<T, N> void printMap(Map<T, N> map, String mapType){
        System.out.println(mapType);
        for (Map.Entry<T, N> entry: map.entrySet()) {
            System.out.println(entry);
        }
    }

    public static void main (String[] args) {
        Map<Purchase, WeekDay> firstPurchase = new HashMap<>();
        Map<Purchase, WeekDay> lastPurchase = new HashMap<>();
        Map<WeekDay, List<Purchase>> enumeratedMap = new HashMap<>();
        List<PricePurchase> discountList = new ArrayList<>();
        try (Scanner sc = new Scanner(new FileReader(Constants.PATH))) {
            while (sc.hasNextLine()){
                String csvLine = sc.nextLine();
                Purchase purchase = PurchaseFactory.getPurchaseFromFactory(csvLine);
                WeekDay day = WeekDay.valueOf(sc.nextLine());
                if (purchase instanceof PricePurchase){
                    discountList.add((PricePurchase) purchase);
                }
                lastPurchase.put(purchase, day);
                if (!(firstPurchase.containsKey(purchase))){
                    firstPurchase.put(purchase, day);
                }
                if (!enumeratedMap.containsKey(day)) {
                    enumeratedMap.put(day, new ArrayList<>());
                }
                enumeratedMap.get(day).add(purchase);
            }
            printMap(firstPurchase, Constants.FIRST_PURCHASE_MAP);
            System.out.println();
            printMap(lastPurchase, Constants.LAST_PURCHASE_MAP);
            findPurchaseDay(lastPurchase, new Purchase("bread", new Byn(155), 3));
            findPurchaseDay(firstPurchase, new Purchase("bread", new Byn(155), 3));
            findPurchaseDay(firstPurchase, new Purchase("bread", new Byn(170), 4));
            deleteMapElementsByName(lastPurchase, Constants.MEAT);
            deleteMapElementsByDay(firstPurchase, WeekDay.FRIDAY);
            System.out.println(Constants.OUTPUT_AFTER_DELETING);
            printMap(firstPurchase, Constants.FIRST_PURCHASE_MAP);
            System.out.println();
            printMap(lastPurchase, Constants.LAST_PURCHASE_MAP);
            System.out.println(getPurchasesTotalCost(discountList));
            printMap(enumeratedMap, Constants.ENUMERATED_MAP);
            for (Map.Entry<WeekDay, List<Purchase>> entry: enumeratedMap.entrySet()) {
                System.out.println(Constants.OUTPUT_COST_EACH_DAY + entry.getKey() + " " +
                        getPurchasesTotalCost(entry.getValue()));
            }
            System.out.println(Constants.MONDAY_PURCHASES + enumeratedMap.get(WeekDay.MONDAY));
        } catch (FileNotFoundException e) {
        System.err.println(e.getMessage());
    }
    }
}
