package com.epam.university.service.impl.comparator;

import com.epam.university.model.identifiable.UserDto;
import com.epam.university.model.identifiable.UserRole;
import org.junit.Assert;
import org.junit.Test;

public class UserDtoSurnameNameComparatorTest {

    private final static UserDto USER_DTO =
            new UserDto(1, UserRole.ENROLLEE, "name", "surname");

    private final UserDtoSurnameNameComparator comparator =
            new UserDtoSurnameNameComparator();

    @Test
    public void testCompareShouldReturnPositiveWhenFirstSurnameMoreSecond() {
        //given
        UserDto userDto = new UserDto(1, UserRole.ENROLLEE, "name", "a");
        //when
        int actual = comparator.compare(USER_DTO, userDto);
        //then
        Assert.assertTrue(actual > 0);
    }

    @Test
    public void testCompareShouldReturnPositiveWhenFirstSurnameLessSecond() {
        //given
        UserDto userDto = new UserDto(1, UserRole.ENROLLEE, "name", "z");
        //when
        int actual = comparator.compare(USER_DTO, userDto);
        //then
        Assert.assertTrue(actual < 0);
    }

    @Test
    public void testCompareShouldReturnPositiveWhenSurnamesEqualAndFirstNameMoreSecond() {
        //given
        UserDto userDto = new UserDto(1, UserRole.ENROLLEE, "aname", "surname");
        //when
        int actual = comparator.compare(USER_DTO, userDto);
        //then
        Assert.assertTrue(actual > 0);
    }

    @Test
    public void testCompareShouldReturnNegativeWhenSurnamesEqualAndFirstNameLessSecond() {
        //given
        UserDto userDto = new UserDto(1, UserRole.ENROLLEE, "zname", "surname");
        //when
        int actual = comparator.compare(USER_DTO, userDto);
        //then
        Assert.assertTrue(actual < 0);
    }

    @Test
    public void testCompareShouldReturnZeroWhenSurnamesEqualAndNamesEqual() {
        //given
        UserDto userDto = new UserDto(1, UserRole.ENROLLEE, "name", "surname");
        //when
        int actual = comparator.compare(USER_DTO, userDto);
        //then
        Assert.assertTrue(actual == 0);
    }

}