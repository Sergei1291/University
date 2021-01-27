package com.epam.university.validator;

public class AuthorizationValidator {

    private final static String PATTERN_LOGIN = "[A-Za-z][A-Za-z0-9_]{3,11}";
    private final static String PATTERN_PASSWORD = "[A-Za-z0-9]{4,12}";

    public boolean isValid(String login, String password) {
        return isValidLogin(login) && isValidPassword(password);
    }

    private boolean isValidLogin(String login) {
        if (login == null) {
            return false;
        }
        return login.matches(PATTERN_LOGIN);
    }

    private boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        return password.matches(PATTERN_PASSWORD);
    }

}