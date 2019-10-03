package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternChecker {
    public static boolean checkPattern(char input, String pattern) {
        Pattern pattern1 = Pattern.compile(pattern);
        Matcher matcher = pattern1.matcher(String.valueOf(input));
        return matcher.matches();
    }
}
