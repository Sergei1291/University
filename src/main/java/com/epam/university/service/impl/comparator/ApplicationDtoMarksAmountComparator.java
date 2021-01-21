package com.epam.university.service.impl.comparator;

import com.epam.university.model.ApplicationDto;

import java.util.Comparator;

public class ApplicationDtoMarksAmountComparator implements Comparator<ApplicationDto> {

    @Override
    public int compare(ApplicationDto applicationFirst, ApplicationDto applicationSecond) {
        int firstMarksAmount = applicationFirst.getMarksAmount();
        int secondMarksAmount = applicationSecond.getMarksAmount();
        return secondMarksAmount - firstMarksAmount;
    }

}