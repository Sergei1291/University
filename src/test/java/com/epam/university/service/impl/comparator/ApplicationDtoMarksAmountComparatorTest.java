package com.epam.university.service.impl.comparator;

import com.epam.university.model.ApplicationDto;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationDtoMarksAmountComparatorTest {

    private final static ApplicationDto APPLICATION_DTO_MORE = new ApplicationDto(1, 100);
    private final static ApplicationDto APPLICATION_DTO_LESS = new ApplicationDto(1, 99);
    private final static ApplicationDto APPLICATION_DTO_LESS_EQUAL = new ApplicationDto(1, 99);

    private final ApplicationDtoMarksAmountComparator comparator =
            new ApplicationDtoMarksAmountComparator();

    @Test
    public void testCompareShouldReturnNegativeWhenFirstMarksAmountMoreSecond() {
        //when
        int actual = comparator.compare(APPLICATION_DTO_MORE, APPLICATION_DTO_LESS);
        //then
        Assert.assertTrue(actual < 0);
    }

    @Test
    public void testCompareShouldReturnPositiveWhenSecondMarksAmountMoreFirst() {
        //when
        int actual = comparator.compare(APPLICATION_DTO_LESS, APPLICATION_DTO_MORE);
        //then
        Assert.assertTrue(actual > 0);
    }

    @Test
    public void testCompareShouldReturnPositiveWhenSecondMarksAmountEqualFirst() {
        //when
        int actual = comparator.compare(APPLICATION_DTO_LESS, APPLICATION_DTO_LESS_EQUAL);
        //then
        Assert.assertTrue(actual == 0);
    }

}