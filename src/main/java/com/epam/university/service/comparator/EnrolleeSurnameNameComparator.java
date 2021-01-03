package com.epam.university.service.comparator;

import com.epam.university.model.user.Enrollee;

import java.util.Comparator;

public class EnrolleeSurnameNameComparator implements Comparator<Enrollee> {

    @Override
    public int compare(Enrollee enrolleeFirst, Enrollee enrolleeSecond) {

        String surnameFirst = enrolleeFirst.getSurname();
        String surnameSecond = enrolleeSecond.getSurname();

        int compareSurname = surnameFirst.compareTo(surnameSecond);

        if (compareSurname != 0) {

            return compareSurname;
        }

        String nameFirst = enrolleeFirst.getName();
        String nameSecond = enrolleeSecond.getName();

        return nameFirst.compareTo(nameSecond);
    }

}