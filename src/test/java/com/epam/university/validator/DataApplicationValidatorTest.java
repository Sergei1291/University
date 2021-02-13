package com.epam.university.validator;

import com.epam.university.model.identifiable.UserDto;
import com.epam.university.model.identifiable.UserRole;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

public class DataApplicationValidatorTest {

    private final static UserDto USER_DTO = new UserDto(1, UserRole.ENROLLEE, "", "");

    @Test
    public void testIsValidShouldFalseWhenSubjectIdNotValid() {
        //given
        UserDtoValidator userDtoValidator = mock(UserDtoValidator.class);
        when(userDtoValidator.isValid(anyObject())).thenReturn(true);
        FacultyIdValidator facultyIdValidator = mock(FacultyIdValidator.class);
        when(facultyIdValidator.isValid(anyInt())).thenReturn(true);
        MarkValidator markValidator = mock(MarkValidator.class);
        when(markValidator.isValid(anyInt())).thenReturn(true);
        SubjectIdValidator subjectIdValidator = mock(SubjectIdValidator.class);
        when(subjectIdValidator.isValid(anyInt())).thenReturn(true);
        when(subjectIdValidator.isValid(4)).thenReturn(false);
        DataApplicationValidator dataApplicationValidator = new DataApplicationValidator(
                userDtoValidator, facultyIdValidator, subjectIdValidator, markValidator);
        Map<Integer, Integer> subjectsMarks = new HashMap<Integer, Integer>() {
            {
                put(1, 100);
                put(2, 100);
                put(5, 100);
                put(4, 100);
            }
        };
        //when
        boolean actual = dataApplicationValidator.isValid(USER_DTO, 1, 1, subjectsMarks);
        //then
        Assert.assertFalse(actual);
        verify(subjectIdValidator, times(3)).isValid(anyInt());
        verify(subjectIdValidator, times(3)).isValid(anyInt());
    }

    @Test
    public void testIsValidShouldFalseWhenMarkNotValid() {
        //given
        UserDtoValidator userDtoValidator = mock(UserDtoValidator.class);
        when(userDtoValidator.isValid(anyObject())).thenReturn(true);
        FacultyIdValidator facultyIdValidator = mock(FacultyIdValidator.class);
        when(facultyIdValidator.isValid(anyInt())).thenReturn(true);
        MarkValidator markValidator = mock(MarkValidator.class);
        when(markValidator.isValid(anyInt())).thenReturn(true);
        when(markValidator.isValid(99)).thenReturn(false);
        SubjectIdValidator subjectIdValidator = mock(SubjectIdValidator.class);
        when(subjectIdValidator.isValid(anyInt())).thenReturn(true);
        DataApplicationValidator dataApplicationValidator = new DataApplicationValidator(
                userDtoValidator, facultyIdValidator, subjectIdValidator, markValidator);
        Map<Integer, Integer> subjectsMarks = new HashMap<Integer, Integer>() {
            {
                put(1, 100);
                put(2, 99);
                put(5, 100);
                put(4, 100);
            }
        };
        //when
        boolean actual = dataApplicationValidator.isValid(USER_DTO, 1, 1, subjectsMarks);
        //then
        Assert.assertFalse(actual);
        verify(subjectIdValidator, times(2)).isValid(anyInt());
        verify(markValidator, times(3)).isValid(anyInt());
    }

}