package com.epam.university.validator;

public class NumberValidator implements Validator<String> {

    private final static String NUMBER_REGEX = "[0-9]+";

    @Override
    public boolean isValid(String data) {
        if (data == null) {
            return false;
        }
        return data.matches(NUMBER_REGEX);
    }

}