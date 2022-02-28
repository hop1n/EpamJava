import by.epam.lab.LenNum;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Runner {
    public static void main (String[] args){
        List<LenNum> list = new ArrayList<>();
        String regex = "[\\s();]+";
        try(Scanner sc = new Scanner(new FileReader("src/in.txt") )) {
            sc.useLocale(Locale.ENGLISH);
            while (sc.hasNextLine()){
                String csvLine = sc.nextLine();
                String[] parts =  csvLine.split(regex);
                double x1 = Double.parseDouble(parts[1]);
                double x2 = Double.parseDouble(parts[2]);
                double y1 = Double.parseDouble(parts[3]);
                double y2 = Double.parseDouble(parts[4]);
                int l = (int)  Math.round(Math.sqrt((x1 - x2)*(x1 - x2)+(y1 - y2)*(y1 - y2)));
                Collections.sort(list);
                int n = Collections.binarySearch(list, new LenNum(l, 1));
                if (n >= 0){
                    LenNum lenNum = list.get(n);
                    lenNum.setNum(lenNum.getNum() + 1);
                } else {
                    list.add(new LenNum(l, 1));
                }
            }
            Collections.sort(list, new Comparator<LenNum>() {
                @Override
                public int compare(LenNum a, LenNum b){
                    return b.getNum() - a.getNum();
                }
            });
            for (LenNum lenNum : list){
                System.out.println(lenNum);
            }
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
