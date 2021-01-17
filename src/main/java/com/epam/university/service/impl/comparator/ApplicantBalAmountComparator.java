package com.epam.university.service.impl.comparator;

import com.epam.university.model.Applicant;

import java.util.Comparator;

public class ApplicantBalAmountComparator implements Comparator<Applicant> {

    @Override
    public int compare(Applicant applicantFirst, Applicant applicantSecond) {
        int firstBalAmount = applicantFirst.getBalAmount();
        int secondBalAmount = applicantSecond.getBalAmount();
        return secondBalAmount - firstBalAmount;
    }

}