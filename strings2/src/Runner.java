import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {
    public static void main(String[] args) {
        final String ERR_MESSAGE = "there is no data";
        try {
            final String INPUT_PROPERTIES = "in";
            final String ERR_LINES_OUT = "error-lines = ";
            final String SUM_OUT = "sum = ";
            final String KEY_VALUE_OUT = "value";
            ResourceBundle rb = ResourceBundle.getBundle(INPUT_PROPERTIES);
            Enumeration<String> keys = rb.getKeys();
            int errorLines = 0;
            double sum = 0.0;
            while (keys.hasMoreElements()) {
                try {
                    final String FIRST_MATCH = "index.*";
                    final String SECOND_MATCH = "index([1-9]\\d*)";
                    final String THIRD_MATCH ="([1-9])(\\d*)";
                    String key = keys.nextElement();
                    Pattern pattern = Pattern.compile(FIRST_MATCH);
                    Matcher matcher = pattern.matcher(key);
                    if (matcher.lookingAt()) {
                        pattern = Pattern.compile(SECOND_MATCH);
                        matcher = pattern.matcher(key);
                        if (matcher.matches()) {
                            pattern = Pattern.compile(THIRD_MATCH);
                            matcher = pattern.matcher(matcher.group());
                            matcher.find();
                            String s = matcher.group();
                            pattern = Pattern.compile(THIRD_MATCH);
                            matcher = pattern.matcher(rb.getString(key).trim());
                            if (matcher.lookingAt()) {
                                sum += Double.parseDouble(rb.getString(KEY_VALUE_OUT + s + matcher.group()));
                            } else {
                                errorLines++;
                            }
                        } else {
                            errorLines++;
                        }
                    }
                } catch (MissingResourceException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    errorLines++;
                }
            }
            System.out.println(SUM_OUT + sum);
            System.out.println(ERR_LINES_OUT + errorLines);
        } catch (MissingResourceException e) {
            System.out.println(ERR_MESSAGE);
        }
    }
}
