import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {
    public static void main(String[] args) {
        try {
            final String INPUT_PROPERTIES = "in";
            ResourceBundle rb = ResourceBundle.getBundle(INPUT_PROPERTIES);
            Enumeration<String> keys = rb.getKeys();
            String element = null;
            List<String> indexes = new ArrayList<String>();
            String key;
            int errorLines = 0;
            double sum = 0.0;
            while (keys.hasMoreElements()) {
                try {
                    key = keys.nextElement();
                    String regex = "index.*";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(key);
                    if (matcher.lookingAt()){
                        regex = "index([1-9]\\d*)";
                        pattern = Pattern.compile(regex);
                        matcher = pattern.matcher(key);
                        if(matcher.matches()){
                            regex = "([1-9])(\\d*)";
                            pattern = Pattern.compile(regex);
                            matcher = pattern.matcher(matcher.group());
                            matcher.find();
                            String s = matcher.group();
                            pattern = Pattern.compile(regex);
                            matcher = pattern.matcher(rb.getString(key).trim());
                            if(matcher.lookingAt()){
                                sum += Double.parseDouble(rb.getString("value" + s + matcher.group()));
                            }
                            else {
                            errorLines++;
                            }
                        }
                        else {
                            errorLines++;
                        }
                    }
                } catch(MissingResourceException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    errorLines++;
                }
            }
            System.out.println("sum = " + sum);
            System.out.println("error-lines = " + errorLines);
        } catch (MissingResourceException e) {
            System.out.println(e.getMessage());
        }
    }
}
