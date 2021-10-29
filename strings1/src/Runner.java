import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Runner {
    private static boolean isNumber (String in) {
        String digits = "1234567890";
        boolean bool = true;
        for (int i = 0; i<in.length();i++){
            if (!digits.contains(in.substring(i, i+1))){
                bool = false;
                break;
            }
        }
        return bool;
    }

    public static void main(String args[]){
        final String SEPORATOR = File.separator;
        final String PATH = "src" + SEPORATOR + "in.csv";
        final String PLUS = " + ";
        final String MINUS = " - ";
        final String ONLY_MINUS = "-";
        String[] charsArr;
        String sumElements = "";
        try (Scanner sc = new Scanner(new FileReader(PATH))) {
            byte k = 0;
            int i = 0;
            double sum = 0.0;
            String str = "";
            while (sc.hasNext()){
                str = sc.nextLine();
                charsArr = str.split(";");
                System.out.println(Arrays.toString(charsArr));
                if (isNumber(charsArr[0])) {
                    i = Integer.parseInt(charsArr[0]);
                    if (i>charsArr.length-1){
                        k++;
                    } else {
                        try {
                            sum += Double.parseDouble(charsArr[i]);
                            if (Double.parseDouble(charsArr[i]) < 0){
                                sumElements += MINUS + Double.parseDouble(charsArr[i]) * -1;
                            } else{
                                sumElements += PLUS + charsArr[i];
                            }
                        } catch (NumberFormatException e){
                            k++;
                        }
                    }
                } else {
                    System.out.println("wrong string");
                    k++;
                }

            }
            if (sumElements.startsWith(MINUS)){
                sumElements = sumElements.substring(MINUS.length());
                sumElements = '-' + sumElements;
            } else
                sumElements = sumElements.substring(MINUS.length());
            System.out.println("result(" + sumElements + ")=" + sum);
        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
    }

}
