package uts.isd.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";
    private static final String namePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";
    private static final String passwordPattern = "[a-z0-9]{4,}";

    public static boolean checkEmpty(String email, String password) {
        return email.isEmpty() || password.isEmpty();
    }


    public static boolean validEmail(String email) {
        return validate(emailPattern, email);
    }


    public static boolean validName(String name) {
        return validate(namePattern, name);
    }


    public static boolean validPassword(String password) {
        return validate(passwordPattern, password);
    }

    // Helpers

    private static boolean validate(String pattern, String input) {
        Pattern regEx = Pattern.compile(pattern);
        Matcher match = regEx.matcher(input);

        return match.matches();
    }
}