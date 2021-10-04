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
        Purchase[] purchases = new Purchase[6];
        try (Scanner sc = new Scanner(new FileReader(PATH))) {
            sc.useLocale(Locale.ENGLISH);
            for (int i = 0; i < 6; i++) {
                purchases[i] = PurchasesFactory.getPurchaseFromFactory(sc);
                System.out.println(purchases[i]);
            }
        }
    }
}
