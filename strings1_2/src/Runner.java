import com.sun.org.apache.xpath.internal.operations.Minus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Runner {
    public static void main(String args[]) {
        final String SEPARATOR = File.separator;
        final String PATH = "src" + SEPARATOR + "in.csv";
        StringBuilder sumElements = new StringBuilder();
        try (Scanner sc = new Scanner(new FileReader(PATH))) {
            final String ERR_MSG = "Input file is not found";
            final String RESULT_BEGIN = "result(";
            final String RESULT_END = ") = ";
            final String ERR_LINES_OUT = "error-lines = ";
            final String MINUS = " - ";
            byte countErrLines = 0;
            double sum = 0.0;
            while (sc.hasNext()) {
                final String DELIMETER = ";";
                String[] charsArr = sc.nextLine().split(DELIMETER);
                try {
                    int number = Integer.parseInt(charsArr[0]);
                    final String PLUS = " + ";
                    double firstDigit = Double.parseDouble(charsArr[number]);
                    sum += firstDigit;
                    if (firstDigit < 0) {
                        sumElements.append(MINUS).append(firstDigit * -1);
                    } else {
                        sumElements.append(PLUS).append(firstDigit);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    countErrLines++;
                }
            }
            if (sumElements.length() != 0) {
                final char ONLY_MINUS = '-';
                final int SIGN_LENGTH = MINUS.length();
                String sign = sumElements.substring(0, SIGN_LENGTH);
                sumElements.delete(0, SIGN_LENGTH);
                if (sign.equals(MINUS)) {
                    sumElements.insert(0, ONLY_MINUS);
                }
            }
            System.out.println(RESULT_BEGIN + sumElements + RESULT_END + sum);
            System.out.println(ERR_LINES_OUT + countErrLines);
        } catch (FileNotFoundException e) {
            System.err.println(ERR_MSG);
        }
    }
}
