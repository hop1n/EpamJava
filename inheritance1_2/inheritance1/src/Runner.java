import by.gsu.epamlab.*;

import java.io.File;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) throws FileNotFoundException {
        final String SEPARATOR = File.separator;
        final String PATH = "." + SEPARATOR + "src" + SEPARATOR + "in.txt";
        try (Scanner sc = new Scanner(new FileReader(PATH))) {
            final int PURCHASES_NUMBER = 6;
            Purchase[] purchases = new Purchase[PURCHASES_NUMBER];
            Purchase maxCostPurchase = new Purchase();
            boolean isAllPurchasesEqual = true;
            sc.useLocale(Locale.ENGLISH);
            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = PurchasesFactory.getPurchaseFromFactory(sc);
                System.out.println(purchases[i]);
                if (purchases[i].getCost().compareTo(maxCostPurchase.getCost()) > 0) {
                        maxCostPurchase = purchases[i];
                }
                if (isAllPurchasesEqual) {
                    isAllPurchasesEqual = purchases[i].equals(purchases[0]);
                }
            }
            System.out.println("Purchase with maximum cost: " + maxCostPurchase);
            if (isAllPurchasesEqual) {
                System.out.println("all purchases are equal.\n");
            } else {
                System.out.println("all purchases are not equal.\n");
            }
        }catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
    }
}
