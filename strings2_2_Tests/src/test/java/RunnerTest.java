import org.junit.Assert;
import org.testng.annotations.Test;

import javax.xml.transform.Result;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunnerTest {
    private static class Result {
        private double sum;
        private int errors;

        public void setSum(double sum) {
            this.sum = sum;
        }

        public void setErrors(int errors) {
            this.errors = errors;
        }

        public Result(int errors, double sum) {
            this.errors = errors;
            this.sum = sum;
        }

        public int getErrors() {
            return errors;
        }

        public double getSum() {
            return sum;
        }

    }

    private static Result getResult(String fileName) throws FileNotFoundException {
        final String ERR_LINES_OUT = "error-lines = ";
        final String SUM_OUT = "sum = ";
        final String KEY_REG_EXP = "index(.*)";
        final String NUM_REG_EXP = "[1-9]\\d*";
        ResourceBundle rb = ResourceBundle.getBundle(fileName);
        Enumeration<String> keys = rb.getKeys();
        Pattern pattern_key = Pattern.compile(KEY_REG_EXP);
        Pattern pattern_num = Pattern.compile(NUM_REG_EXP);
        int errors = 0;
        double sum = 0.0;
        final int TAIL_INDEX = 1;
        final String VALUE = "value";
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            Matcher keyMatcher = pattern_key.matcher(key);
            if (keyMatcher.matches()) {
                String iStr = keyMatcher.group(TAIL_INDEX);
                String jStr = rb.getString(key).trim();
                Matcher iMatcher = pattern_num.matcher(iStr);
                Matcher jMatcher = pattern_num.matcher(jStr);
                if (iMatcher.matches() && jMatcher.matches()) {
                    String valueIJ = VALUE + iStr + jStr;
                    try {
                        sum += Double.parseDouble(rb.getString(valueIJ));
                    } catch (MissingResourceException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        errors++;
                    }
                } else {
                    errors++;
                }
            }
        }
        System.out.println(SUM_OUT + sum);
        System.out.println(ERR_LINES_OUT + errors);

        return new Result(errors, sum);
    }


    @Test
    public void testFirstFileMainScenario() throws FileNotFoundException {
        final String FILE_NAME = "in1";
        final int EXPECTED_ERRORS = 3;
        final double EXPECTED_SUM = 8.24;
        Result result = getResult(FILE_NAME);
        Assert.assertEquals(EXPECTED_ERRORS, result.errors);
        Assert.assertEquals(EXPECTED_SUM, result.sum, 0.001);
    }

    @Test
    public void testSecondFileMainScenario() throws FileNotFoundException {
        final String FILE_NAME = "in2";
        final int EXPECTED_ERRORS = 9;
        final double EXPECTED_SUM = 30.242;
        Result result = getResult(FILE_NAME);
        Assert.assertEquals(EXPECTED_ERRORS, result.errors);
        Assert.assertEquals(EXPECTED_SUM, result.sum, 0.0001);
    }

    @Test(expectedExceptions = MissingResourceException.class)
    public void testWrongCsvName() throws MissingResourceException, FileNotFoundException {
        final String testName = "in9";
        getResult(testName);
    }

}