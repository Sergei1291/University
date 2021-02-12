package com.epam.university.validator;

import org.junit.Assert;
import org.junit.Test;

public class MarkValidatorTest {

    private MarkValidator validator = new MarkValidator();

    @Test
    public void testIsValidShouldFalseWhenMarkNull() {
        //when
        boolean actual = validator.isValid(null);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldFalseWhenMarkLessZero() {
        //when
        boolean actual = validator.isValid(-1);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldFalseWhenMarkMoreOneHundred() {
        //when
        boolean actual = validator.isValid(101);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldTrueWhenMarkBetweenZeroAndOneHundredOne() {
        //when
        boolean actual = validator.isValid(46);
        //then
        Assert.assertTrue(actual);
    }

}