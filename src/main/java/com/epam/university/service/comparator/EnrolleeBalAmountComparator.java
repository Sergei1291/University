package com.epam.university.service.comparator;

import com.epam.university.model.user.Enrollee;

import java.util.Comparator;

public class EnrolleeBalAmountComparator implements Comparator<Enrollee> {

    @Override
    public int compare(Enrollee enrolleeFirst, Enrollee enrolleeSecond) {

        int firstBalAmount = enrolleeFirst.getBalAmount();
        int secondBalAmount = enrolleeSecond.getBalAmount();

        return secondBalAmount - firstBalAmount;
    }

}