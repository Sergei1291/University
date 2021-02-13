package com.epam.university.validator;

import com.epam.university.model.identifiable.UserDto;

import java.util.Map;
import java.util.Set;

public class DataApplicationValidator {

    private final UserDtoValidator userDtoValidator;
    private final FacultyIdValidator facultyIdValidator;
    private final SubjectIdValidator subjectIdValidator;
    private final MarkValidator markValidator;

    public DataApplicationValidator(UserDtoValidator userDtoValidator, FacultyIdValidator facultyIdValidator,
                                    SubjectIdValidator subjectIdValidator, MarkValidator markValidator) {
        this.userDtoValidator = userDtoValidator;
        this.facultyIdValidator = facultyIdValidator;
        this.subjectIdValidator = subjectIdValidator;
        this.markValidator = markValidator;
    }

    public boolean isValid(UserDto userDto, int facultyId, int averageMark,
                           Map<Integer, Integer> subjectsMarks) {
        if (!userDtoValidator.isValid(userDto)) {
            return false;
        }
        if (!facultyIdValidator.isValid(facultyId)) {
            return false;
        }
        if (!markValidator.isValid(averageMark)) {
            return false;
        }
        return isValidSubjectsMarks(subjectsMarks);
    }

    private boolean isValidSubjectsMarks(Map<Integer, Integer> subjectsMarks) {
        Set<Integer> subjectsId = subjectsMarks.keySet();
        for (Integer subjectId : subjectsId) {
            if (!subjectIdValidator.isValid(subjectId)) {
                return false;
            }
            Integer subjectMark = subjectsMarks.get(subjectId);
            if (!markValidator.isValid(subjectMark)) {
                return false;
            }
        }
        return true;
    }

}