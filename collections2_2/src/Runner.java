import by.epam.lab.Constants;
import by.epam.lab.LenNum;
import by.epam.lab.LenNumComparator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Runner {
    public static void main(String[] args) {
        Set<LenNum> setNumLen = new HashSet<LenNum>();
        String regex = Constants.REGEX;
        try (Scanner sc = new Scanner(new FileReader(Constants.PATH))) {
            sc.useLocale(Locale.ENGLISH);
            while (sc.hasNextLine()) {
                String csvLine = sc.nextLine();
                String[] parts = csvLine.split(regex);
                double x1 = Double.parseDouble(parts[Constants.X1_INDEX]);
                double y1 = Double.parseDouble(parts[Constants.Y1_INDEX]);
                double x2 = Double.parseDouble(parts[Constants.X2_INDEX]);
                double y2 = Double.parseDouble(parts[Constants.Y2_INDEX]);
                int l = (int) Math.round(Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
                LenNum lenNum = new LenNum(l);
                setNumLen.add(lenNum);
            }
            List<LenNum> list = new ArrayList<>(setNumLen);
            Collections.sort(list, new LenNumComparator());
            for (LenNum lenNum : list) {
                System.out.println(lenNum);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}