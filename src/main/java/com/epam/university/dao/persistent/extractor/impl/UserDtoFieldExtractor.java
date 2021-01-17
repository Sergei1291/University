package com.epam.university.dao.persistent.extractor.impl;

import com.epam.university.dao.persistent.extractor.AbstractFieldExtractor;
import com.epam.university.model.identifiable.user.ApplicationStatus;
import com.epam.university.model.identifiable.user.UserDto;

import java.util.Map;

public class UserDtoFieldExtractor extends AbstractFieldExtractor<UserDto> {

    private final static String ID_COLUMN = "id";
    private final static String NAME_COLUMN = "name";
    private final static String SURNAME_COLUMN = "surname";
    private final static String FACULTY_COLUMN = "faculty";
    private final static String AVERAGE_MARK_COLUMN = "average_mark";
    private final static String APPLICATION_STATUS_COLUMN = "application_status";

    @Override
    protected void initializeMap(Map<String, Object> mapNameFiledValue, UserDto userDto) {
        int id = userDto.getId();
        mapNameFiledValue.put(ID_COLUMN, id);

        String name = userDto.getName();
        mapNameFiledValue.put(NAME_COLUMN, name);

        String surname = userDto.getSurname();
        mapNameFiledValue.put(SURNAME_COLUMN, surname);

        Integer faculty = userDto.getFaculty();
        mapNameFiledValue.put(FACULTY_COLUMN, faculty);

        Integer averageMark = userDto.getAverageMark();
        mapNameFiledValue.put(AVERAGE_MARK_COLUMN, averageMark);

        ApplicationStatus status = userDto.getApplicationStatus();
        if (status != null) {
            String statusValue = status.name();
            mapNameFiledValue.put(APPLICATION_STATUS_COLUMN, statusValue);
        } else {
            mapNameFiledValue.put(APPLICATION_STATUS_COLUMN, null);
        }
    }

}