package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validCustomerId(final String input) {
        final Pattern pattern = Pattern.compile("^[A-Za-z][a-zA-Z0-9_]{4,11}$", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean validCustomerName(final String input) { //3글자 이상 무한대를 어떻게 표현해야 할지 몰라서 15자로 설정했습니다.
        final Pattern pattern = Pattern.compile("^[A-Za-z]{3,15}$", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
