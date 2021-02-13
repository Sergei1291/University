package com.epam.university.validator;

import org.junit.Assert;
import org.junit.Test;

public class SubjectIdValidatorTest {

    private SubjectIdValidator validator = new SubjectIdValidator();

    @Test
    public void testIsValidShouldFalseWhenSubjectIdNull() {
        //when
        boolean actual = validator.isValid(null);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldFalseWhenSubjectIdLessZero() {
        //when
        boolean actual = validator.isValid(-1);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldFalseWhenSubjectIdMoreSeven() {
        //when
        boolean actual = validator.isValid(8);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldTrueWhenSubjectIdBetweenZeroEight() {
        //when
        boolean actual = validator.isValid(5);
        //then
        Assert.assertTrue(actual);
    }
    
}