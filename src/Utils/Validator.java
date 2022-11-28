package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validCustomerId(final String input) {
        // Compile regular expression
        final Pattern pattern = Pattern.compile("^[A-Za-z][a-zA-Z0-9_]{4,11}$", Pattern.CASE_INSENSITIVE);
        // Match regex against input
        final Matcher matcher = pattern.matcher(input);
        // Use results...
        return matcher.matches();
    }

    public static boolean validCustomerName(final String input) {
        // Compile regular expression
        final Pattern pattern = Pattern.compile("^[A-Za-z]{3,15}$", Pattern.CASE_INSENSITIVE);
        // Match regex against input
        final Matcher matcher = pattern.matcher(input);
        // Use results...
        return matcher.matches();
    }

}
