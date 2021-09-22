import by.gsu.epamlab.Purchase;
import by.gsu.epamlab.WeekDay;
import by.gsu.epamlab.KopsToByn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;



public class Runner {
    private static void printArr(Purchase[] purchases){
        System.out.println(Purchase.NAME + "\n" + KopsToByn.toByn(Purchase.PRICE));
        for (Purchase purchase: purchases){
            System.out.println(purchase);
        }
    }

    public static void main (String[] args) throws FileNotFoundException {
        String seporator = File.separator;
        String in = "." + seporator + "src" + seporator + "in.txt";
        try(Scanner sc = new Scanner(new FileReader(in))){
            sc.useLocale(Locale.ENGLISH);
            final int PURCHASES_NUMBER= sc.nextInt();
            Purchase[] purchases = new Purchase[PURCHASES_NUMBER];
            for (int i = 0; i < PURCHASES_NUMBER; i++){
                purchases[i] = new Purchase(sc.nextInt(), sc.nextInt(), sc.nextInt());
            }
            printArr(purchases);
            int sum=0, mondayCost=0, maxCost= 0;
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
            double meanCost;
            if (sum == 0){
                meanCost = 0;
            }
            else{
                meanCost = (double) (sum/PURCHASES_NUMBER)/100;
            }
            System.out.printf(Locale.ENGLISH, "%.3f\n", meanCost / 100);
            System.out.println(KopsToByn.toByn(mondayCost));
            System.out.println(maxDay);
            Arrays.sort(purchases);
            printArr(purchases);
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