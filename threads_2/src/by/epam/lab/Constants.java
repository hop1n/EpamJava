package by.epam.lab;

import java.io.File;

public class Constants {
    public static final String SEPARATOR = File.separator;
    public static final String PATH = "src" + SEPARATOR + "trials.csv";
    public static final String FAIL = "fail";
    public static final String PUT = "put> ";
    public static final String GOT = "GOT >";
    public static final String DELIMITER = ";";
    public static final int ACCOUNT_INDEX = 0;
    public static final int MARK1_INDEX = 1;
    public static final int MARK2_INDEX = 2;
    public static final String FILE_NOT_FOUND ="Input file is not found";
    public static final Trial FAILED_TRIAL = new Trial(FAIL, 0, 0);
}
