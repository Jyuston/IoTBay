package uts.isd.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private final String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";
    private final String namePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";
    private final String passwordPattern = "[a-z0-9]{4,}";
    private HttpServletRequest request;
    private boolean failed = false;

    public Validator(HttpServletRequest request) {
        this.request = request;
    }

    public boolean failed() { return failed; }

    public Validator checkEmpty(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            request.setAttribute("emptyEmailPassVErr", "Error: Email or Password field is empty");
            failed = true;
        }

        return this;
    }

    public Validator validateEmail(String email) {
        if (!validate(emailPattern, email)) {
            request.setAttribute("emailVErr", "Error: Email format incorrect");
            failed = true;
        }

        return this;
    }


    public Validator validateName(String name) {
        if (!validate(namePattern, name)) {
            request.setAttribute("nameVErr", "Error: Name format incorrect");
            failed = true;
        }

        return this;
    }


    public Validator validatePassword(String password) {
        if (validate(passwordPattern, password))  {
            request.setAttribute("passVErr", "Error: Password format incorrect");
            failed = true;
        }

        return this;
    }

    // Helpers

    private boolean validate(String pattern, String input) {
        Pattern regEx = Pattern.compile(pattern);
        Matcher match = regEx.matcher(input);

        return match.matches();
    }
}