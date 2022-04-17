package by.epam.lab;

import by.epam.lab.exceptions.SourceException;

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
        final String[] SPLITED_LINE = scanner.nextLine().split(DELIMITER);
        String login = SPLITED_LINE[ZERO_PARAMETER];
        String test = SPLITED_LINE[FIRST_PARAMETER];
        Date date = Date.valueOf(SPLITED_LINE[SECOND_PARAMETER]);
        double mark;
        mark = (Double.parseDouble(SPLITED_LINE[THIRD_PARAMETER]));
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
