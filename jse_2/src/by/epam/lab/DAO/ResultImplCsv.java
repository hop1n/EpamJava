package by.epam.lab.DAO;

import by.epam.lab.beans.Result;
import by.epam.lab.services.ResultKind;
import by.epam.lab.exceptions.SourceException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Date;
import java.util.*;

import static by.epam.lab.services.Constants.*;

public class ResultImplCsv implements ResultDao {
    private final Scanner scanner;
    private final ResultKind resultType;

    public ResultImplCsv(String inputFileName, ResultKind resultType) throws SourceException {
        try {
            scanner = new Scanner(new FileReader(inputFileName));
            this.resultType = resultType;
        } catch (FileNotFoundException e) {
            throw new SourceException(FILE_NOT_FOUND);
        }
    }

    public Result nextResult() {
        final String[] SPLIT_LINE = scanner.nextLine().split(DELIMITER);
        return resultType.getResult(SPLIT_LINE[ZERO_PARAMETER], SPLIT_LINE[FIRST_PARAMETER],
                Date.valueOf(SPLIT_LINE[SECOND_PARAMETER]), SPLIT_LINE[THIRD_PARAMETER]);
    }

    @Override
    public boolean hasResult() {
        return scanner.hasNextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
