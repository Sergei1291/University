package com.epam.university.validator;

import org.junit.Assert;
import org.junit.Test;

public class FacultyIdValidatorTest {

    private FacultyIdValidator validator = new FacultyIdValidator();

    @Test
    public void testIsValidShouldFalseWhenFacultyIdNull() {
        //when
        boolean actual = validator.isValid(null);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldFalseWhenFacultyIdLessZero() {
        //when
        boolean actual = validator.isValid(-1);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldFalseWhenFacultyIdMoreSix() {
        //when
        boolean actual = validator.isValid(7);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldTrueWhenFacultyIdBetweenZeroSeven() {
        //when
        boolean actual = validator.isValid(5);
        //then
        Assert.assertTrue(actual);
    }

}