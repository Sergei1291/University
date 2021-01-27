package com.epam.university.validator;

public class MarkValidator implements Validator<Integer> {

    private final static int MIN_MARK = 0;
    private final static int MAX_MARK = 100;

    @Override
    public boolean isValid(Integer mark) {
        if (mark == null) {
            return false;
        }
        return (mark >= MIN_MARK) && (mark <= MAX_MARK);
    }

}