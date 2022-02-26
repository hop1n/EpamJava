import by.epam.lab.LenNum;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main (String[] args){
        List<LenNum> list = new ArrayList<>();
        try(Scanner sc = new Scanner(new FileReader("src/in.txt") )) {
            sc.useLocale(Locale.ENGLISH);
            while (sc.hasNextLine()){
                String csvLine = sc.nextLine();
                String[] parts = csvLine.split();
            }
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
