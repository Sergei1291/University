package com.epam.university.validator;

public class FacultyIdValidator implements Validator<Integer> {

    private final static int NUMBER_FACULTIES = 6;

    @Override
    public boolean isValid(Integer facultyId) {
        if (facultyId == null) {
            return false;
        }
        return (facultyId > 0) && (facultyId <= NUMBER_FACULTIES);
    }

}