package com.epam.university.validator;

public class SubjectIdValidator implements Validator<Integer> {

    private final static int NUMBER_SUBJECTS = 7;

    @Override
    public boolean isValid(Integer subjectId) {
        if (subjectId == null) {
            return false;
        }
        return (subjectId > 0) && (subjectId <= NUMBER_SUBJECTS);
    }

}