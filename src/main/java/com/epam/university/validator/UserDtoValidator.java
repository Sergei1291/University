package com.epam.university.validator;

import com.epam.university.model.identifiable.UserDto;

public class UserDtoValidator implements Validator<UserDto> {

    @Override
    public boolean isValid(UserDto userDto) {
        if (userDto == null) {
            return false;
        }
        if (userDto.getRole() == null) {
            return false;
        }
        return userDto.getId() > 0;
    }

}