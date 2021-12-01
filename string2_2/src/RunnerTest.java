import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RunnerTest {
    private static int getResult(String csvName, StringBuilder strResult) throws FileNotFoundException {
            final String INPUT_PROPERTIES = "in";
            final String SUM_OUT = "sum = ";
            final String KEY_REG_EXP = "index(.*)";
            final String NUM_REG_EXP = "[1-9]\\d*";
            ResourceBundle rb = ResourceBundle.getBundle(INPUT_PROPERTIES);
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
            strResult = strResult.append(SUM_OUT + sum);
            return errors;
    }

    @Test
    public void testFirstFileMainScenario() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("in.properties", result);
        Assert.assertEquals(3, errorLines);
        String expectedIn1 = "sum = 8.24\n" +
                "error-lines = 3";
        Assert.assertEquals(expectedIn1, result.toString());
    }

    @Test
    public void testSecondFileMainScenario() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("in2.properties", result);
        Assert.assertEquals(9, errorLines);
        String expectedIn1 = "sum = 30.242";
        Assert.assertEquals(expectedIn1, result.toString());
    }

//    @Test
//    public void testName1MainScenario() throws FileNotFoundException {
//        StringBuilder result = new StringBuilder();
//        int errorLines = getResult("src/in2.properties", result);
//        Assert.assertEquals(9, errorLines);
//        String expectedIn1 = "sum = 30.242\n" +
//                "error-lines = 9";
//        Assert.assertEquals(expectedIn1, result.toString());
//    }
}
//
//    @Test
//    public void testXxx() throws FileNotFoundException {
//    }
//
//	…
//
//    @Test(expected = FileNotFoundException.class)
//    public void testWrongCsvName() throws FileNotFoundException {
//		...
//    }
//}
//
//}