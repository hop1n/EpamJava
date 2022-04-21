package by.epam.lab.DAO;

import by.epam.lab.Result;
import by.epam.lab.ResultKind;
import by.epam.lab.exceptions.SourceException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Date;
import java.util.*;

import static by.epam.lab.Constants.*;

public class ResultImplCsv implements ResultDao {
    private static Scanner scanner = null;
    private final ResultKind resultType;

    public ResultImplCsv(String inputFileName, ResultKind resultType) {
        this.resultType = resultType;
        try {
            scanner = new Scanner(new FileReader(inputFileName));
        } catch (FileNotFoundException e) {
            throw new SourceException(FILE_NOT_FOUND);
        }
    }

    public Result nextResult() {
        final String[] SPLIT_LINE = scanner.nextLine().split(DELIMITER);
        String login = SPLIT_LINE[ZERO_PARAMETER];
        String test = SPLIT_LINE[FIRST_PARAMETER];
        Date date = Date.valueOf(SPLIT_LINE[SECOND_PARAMETER]);
        String mark = SPLIT_LINE[THIRD_PARAMETER];
        return resultType.getResult(login, test, date, mark);
    }

    public ResultKind getResultType() {
        return resultType;
    }

    public boolean hasResult() {
        return scanner.hasNextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
