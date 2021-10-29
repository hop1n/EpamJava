import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Runner {
    private static boolean isNumber(String in) {
        final String DIGITS = "1234567890";
        boolean bool = true;
        for (int i = 0; i < in.length(); i++) {
            if (!DIGITS.contains(in.substring(i, i + 1))) {
                bool = false;
                break;
            }
        }
        return bool;
    }

    public static void main(String args[]) {
        final String SEPARATOR = File.separator;
        final String PATH = "src" + SEPARATOR + "in.csv";
        final String PLUS = " + ";
        final String MINUS = " - ";
        final String ONLY_MINUS = "-";
        final String DELIMITER = ";";
        final String EMPTY_STRING = "";
        String[] charsArr;
        String sumElements = EMPTY_STRING;
        try (Scanner sc = new Scanner(new FileReader(PATH))) {
            byte k = 0;
            int i;
            double sum = 0.0;
            while (sc.hasNext()) {
                String line;
                line = sc.nextLine();
                charsArr = line.split(DELIMITER);
                if (isNumber(charsArr[0])) {
                    i = Integer.parseInt(charsArr[0]);
                    if (i > charsArr.length - 1) {
                        k++;
                    } else {
                        try {
                            double firstDigit = Double.parseDouble(charsArr[i]);
                            sum += firstDigit;
                            if (firstDigit < 0) {
                                sumElements += MINUS + firstDigit * -1;
                            } else {
                                sumElements += PLUS + firstDigit;
                            }
                        } catch (NumberFormatException e) {
                            k++;
                        }
                    }
                } else {
                    k++;
                }
            }
            if (!sumElements.isEmpty()) {
                sumElements = sumElements.substring(MINUS.length());
                if (sumElements.startsWith(MINUS)) {
                    sumElements = ONLY_MINUS + sumElements;
                }
            }
            System.out.println("result(" + sumElements + ") = " + sum);
            System.out.println("error-lines = " + k);
        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
    }
}
