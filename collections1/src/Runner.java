import by.epam.lab.Constants;

import java.io.FileReader;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Runner {
    public static void main (String[] args) {
        try (Scanner sc = new Scanner(new FileReader(Constants.PATH))) {
            String[] s = new String[0];
            while (sc.hasNextLine()){
                final String DELIMETER = ";";
                String[] charsArr = sc.nextLine().split(DELIMETER);
                System.out.println(Arrays.toString(charsArr));
            }
        } catch (FileNotFoundException e) {
        System.err.println(e.getMessage());
    }
    }
}
