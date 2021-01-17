package com.epam.university.service.impl.comparator;

import com.epam.university.model.identifiable.user.UserDto;

import java.util.Comparator;

public class UserDtoSurnameNameComparator implements Comparator<UserDto> {

    @Override
    public int compare(UserDto userFirst, UserDto userSecond) {
        String surnameFirst = userFirst.getSurname();
        String surnameSecond = userSecond.getSurname();
        int compareSurname = surnameFirst.compareTo(surnameSecond);
        if (compareSurname != 0) {
            return compareSurname;
        }
        String nameFirst = userFirst.getName();
        String nameSecond = userSecond.getName();
        return nameFirst.compareTo(nameSecond);
    }

}