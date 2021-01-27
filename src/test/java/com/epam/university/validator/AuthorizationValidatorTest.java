package com.epam.university.validator;

import org.junit.Assert;
import org.junit.Test;

public class AuthorizationValidatorTest {

    private final static String LOGIN_VALID = "user";
    private final static String LOGIN_INVALID = "2ser";
    private final static String PASSWORD_VALID = "password";
    private final static String PASSWORD_INVALID = "pppPPPpppPPPp";

    private final AuthorizationValidator authorizationValidator = new AuthorizationValidator();

    @Test
    public void testIsValidShouldTrueWhenLoginAndPasswordValid() {
        //when
        boolean actual = authorizationValidator.isValid(LOGIN_VALID, PASSWORD_VALID);
        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidShouldFalseWhenLoginInvalid() {
        //when
        boolean actual = authorizationValidator.isValid(LOGIN_INVALID, PASSWORD_VALID);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldFalseWhenPasswordInvalid() {
        //when
        boolean actual = authorizationValidator.isValid(LOGIN_VALID, PASSWORD_INVALID);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldFalseWhenLoginAndPasswordInvalid() {
        //when
        boolean actual = authorizationValidator.isValid(LOGIN_INVALID, PASSWORD_INVALID);
        //then
        Assert.assertFalse(actual);
    }

}