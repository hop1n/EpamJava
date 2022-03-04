import by.epam.lab.Constants;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Runner {
    public static void main(String[] args) {
        Map<Integer, Integer> mapNumLen = new HashMap<>();
        try (Scanner sc = new Scanner(new FileReader(Constants.PATH))) {
            sc.useLocale(Locale.ENGLISH);
            while (sc.hasNextLine()) {
                String csvLine = sc.nextLine();
                String[] parts = csvLine.split(Constants.REGEX);
                double x1 = Double.parseDouble(parts[Constants.X1_INDEX]);
                double y1 = Double.parseDouble(parts[Constants.Y1_INDEX]);
                double x2 = Double.parseDouble(parts[Constants.X2_INDEX]);
                double y2 = Double.parseDouble(parts[Constants.Y2_INDEX]);
                int l = (int) Math.round(Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
                boolean b = mapNumLen.containsKey(l);
                if (b){
                    mapNumLen.put(l, mapNumLen.get(l)+1);
                } else{
                    mapNumLen.put(l, 1);
                }
            }
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(mapNumLen.entrySet());
            list.sort();
            for (Map.Entry<Integer, Integer> entry : mapNumLen.entrySet()){
                System.out.println(entry);
            }
            for (Map.Entry<Integer, Integer> entry : mapNumLen.entrySet()){
                System.out.println(entry);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
