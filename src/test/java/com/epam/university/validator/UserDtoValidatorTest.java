package com.epam.university.validator;

import com.epam.university.model.identifiable.UserDto;
import com.epam.university.model.identifiable.UserRole;
import org.junit.Assert;
import org.junit.Test;

public class UserDtoValidatorTest {

    private final static UserDto USER_ID_INVALID = new UserDto(0, UserRole.ENROLLEE, "", "");
    private final static UserDto USER_ROLE_INVALID = new UserDto(1, null, "", "");
    private final static UserDto USER_VALID = new UserDto(1, UserRole.COMMITTEE, "", "");

    private final UserDtoValidator userDtoValidator = new UserDtoValidator();

    @Test
    public void testIsValidShouldFalseWhenUserNull() {
        //when
        boolean actual = userDtoValidator.isValid(null);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldFalseWhenIdInvalid() {
        //when
        boolean actual = userDtoValidator.isValid(USER_ID_INVALID);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldFalseWhenRoleInvalid() {
        //when
        boolean actual = userDtoValidator.isValid(USER_ROLE_INVALID);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldTrueWhenUserValid() {
        //when
        boolean actual = userDtoValidator.isValid(USER_VALID);
        //then
        Assert.assertTrue(actual);
    }

}