package com.epam.university.validator;

import org.junit.Assert;
import org.junit.Test;

public class NumberValidatorTest {

    private NumberValidator validator = new NumberValidator();

    @Test
    public void testIsValidShouldFalseWhenDataStringNull() {
        //when
        boolean actual = validator.isValid(null);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldFalseWhenDataStringContainsNotNumberSymbol() {
        //when
        boolean actual = validator.isValid("-1");
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldTrueWhenDataStringContainsOnlyNumberSymbols() {
        //when
        boolean actual = validator.isValid("045210");
        //then
        Assert.assertTrue(actual);
    }

}