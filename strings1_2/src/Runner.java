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
        final char ONLY_MINUS = '-';
        final String DELIMETER = ";";
        StringBuilder sumElements = new StringBuilder();
        try (Scanner sc = new Scanner(new FileReader(PATH))) {
            byte countErrLines = 0;
            double sum = 0.0;
            while (sc.hasNext()) {
                String[] charsArr = sc.nextLine().split(DELIMETER);
                if (isNumber(charsArr[0])) {
                    int number = Integer.parseInt(charsArr[0]);
                    if (number > charsArr.length - 1) {
                        countErrLines++;
                    } else {
                        try {
                            double firstDigit = Double.parseDouble(charsArr[number]);
                            sum += firstDigit;
                            if (firstDigit < 0) {
                                sumElements.append(" - ").append(firstDigit * -1);
                            } else {
                                sumElements.append(" + ").append(firstDigit);
                            }
                        } catch (NumberFormatException e) {
                            countErrLines++;
                        }
                    }
                } else {
                    countErrLines++;
                }
            }
            if (sumElements.length() != 0) {
                String sign = sumElements.substring(0, MINUS.length());
                sumElements.delete(0, MINUS.length());
                if (sign.equals(MINUS)) {
                    sumElements.insert(0, ONLY_MINUS);
                }
            }
            System.out.println("result(" + sumElements + ") = " + sum);
            System.out.println("error-lines = " + countErrLines);
        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
    }
}
