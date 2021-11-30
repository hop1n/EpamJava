import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {
    public static void main(String[] args) {
        final String ERR_MESSAGE = "there is no data";
        try {
            final String ERR_LINES_OUT = "error-lines = ";
            final String KEY_VALUE_OUT = "value";
            final String SUM_OUT = "sum = ";
            final String INPUT_PROPERTIES = "in";
            final String KEY_REG_EXP = "index(.*)";
            final String NUM_REG_EXP = "[1-9]\\d*";
            ResourceBundle rb = ResourceBundle.getBundle(INPUT_PROPERTIES);
            Enumeration<String> keys = rb.getKeys();
            Pattern pattern_1 = Pattern.compile(KEY_REG_EXP);
            Pattern pattern_2 = Pattern.compile(NUM_REG_EXP);
            int errors = 0;
            double sum = 0.0;
            String valueIJ;
            final int TAIL_INDEX = 1;
            final String VALUE = "value";
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                Matcher keyMatcher = pattern_1.matcher(key);
                if (keyMatcher.matches()) {
                    String iStr = keyMatcher.group(TAIL_INDEX);
                    String jStr = rb.getString(key).trim();
                    Matcher iMatcher = pattern_2.matcher(iStr);
                    Matcher jMatcher = pattern_2.matcher(jStr);
                    if (iMatcher.matches() && jMatcher.matches()) {
                        valueIJ = VALUE + iStr + jStr;
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
