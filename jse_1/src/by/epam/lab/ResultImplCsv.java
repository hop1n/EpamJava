package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Date;
import java.util.*;

import static by.epam.lab.Constants.*;

public class ResultImplCsv implements ResultDao {
    private static Scanner scanner = null;

    public ResultImplCsv() {

    }

    public ResultImplCsv(String inputFileName) {
        try {
            scanner = new Scanner(new FileReader(inputFileName));
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }

    public Result nextResult() {
        final String[] SPLIT_LINE = scanner.nextLine().split(DELIMITER);
        String login = SPLIT_LINE[ZERO_PARAMETER];
        String test = SPLIT_LINE[FIRST_PARAMETER];
        Date date = Date.valueOf(SPLIT_LINE[SECOND_PARAMETER]);
        double mark;
        mark = Double.parseDouble(SPLIT_LINE[THIRD_PARAMETER]);
        return new Result(login, test, date, mark);
    }

    public boolean hasResult() {
        return scanner.hasNextLine();
    }

    public void closeReader() {
        scanner.close();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
