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
        String sumElements = EMPTY_STRING;
        try (Scanner sc = new Scanner(new FileReader(PATH))) {
            byte countErrLines = 0;
            double sum = 0.0;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] charsArr = line.split(DELIMITER);
                if (isNumber(charsArr[0])) {
                    int number = Integer.parseInt(charsArr[0]);
                    if (number > charsArr.length - 1) {
                        countErrLines++;
                    } else {
                        try {
                            double firstDigit = Double.parseDouble(charsArr[number]);
                            sum += firstDigit;
                            if (firstDigit < 0) {
                                sumElements += MINUS + firstDigit * -1;
                            } else {
                                sumElements += PLUS + firstDigit;
                            }
                        } catch (NumberFormatException e) {
                            countErrLines++;
                        }
                    }
                } else {
                    countErrLines++;
                }
            }
            if (!sumElements.isEmpty()) {
                String deletedSign = sumElements.substring(MINUS.length());
                if (sumElements.startsWith(MINUS)) {
                    sumElements = ONLY_MINUS + deletedSign;
                } else {
                    sumElements = deletedSign;
                }
            }
            System.out.println("result(" + sumElements + ") = " + sum);
            System.out.println("error-lines = " + countErrLines);
        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
    }
}
