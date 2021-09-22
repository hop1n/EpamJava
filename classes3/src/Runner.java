import by.gsu.epamlab.Purchase;
import by.gsu.epamlab.WeekDay;
import by.gsu.epamlab.KopsToByn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;



public class Runner {
    private static void printPurchases(Purchase[] purchases){
        System.out.println(Purchase.NAME + "\n" + KopsToByn.toByn(Purchase.PRICE));
        for (Purchase purchase: purchases){
            System.out.println(purchase);
        }
    }

    public static void main (String[] args) throws FileNotFoundException {
        final String SEPORATOR = File.separator;
        final String PATH = "." + SEPORATOR + "src" + SEPORATOR + "in.txt";
        try(Scanner sc = new Scanner(new FileReader(PATH))){
            sc.useLocale(Locale.ENGLISH);
            final int PURCHASES_NUMBER= sc.nextInt();
            Purchase[] purchases = new Purchase[PURCHASES_NUMBER];
            for (int i = 0; i < PURCHASES_NUMBER; i++){
                purchases[i] = new Purchase(sc.nextInt(), sc.nextDouble(), sc.nextInt());
            }
            printPurchases(purchases);
            int sum=0;
            int mondayCost=0;
            int maxCost= 0;
            WeekDay maxDay = null;
            for (Purchase purchase: purchases){
                int cost = purchase.getCost();
                sum += cost;
                if (cost > maxCost){
                    maxCost = cost;
                    maxDay = purchase.getDay();
                }
                if (purchase.getDay() == WeekDay.MONDAY){
                    mondayCost += cost;
                }
            }
            double meanCost = 0;
            if (sum != 0){
                meanCost = (double) (sum/PURCHASES_NUMBER)/100;
            }
            System.out.printf(Locale.ENGLISH, "%.3f\n", meanCost);
            System.out.println(KopsToByn.toByn(mondayCost));
            System.out.println(maxDay);
            Arrays.sort(purchases);
            printPurchases(purchases);
            int index = Arrays.binarySearch(purchases, new Purchase(5, 0, null));
            if (index >=0){
                System.out.println(purchases[index]);
            }
            else{
                System.out.println("Required purchase is not found");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Input file is not found");
        }
    }
}