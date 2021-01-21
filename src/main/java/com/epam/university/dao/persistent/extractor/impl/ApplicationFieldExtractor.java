package com.epam.university.dao.persistent.extractor.impl;

import com.epam.university.dao.persistent.extractor.AbstractFieldExtractor;
import com.epam.university.model.identifiable.Application;
import com.epam.university.model.identifiable.ApplicationStatus;

import java.util.Map;

public class ApplicationFieldExtractor extends AbstractFieldExtractor<Application> {

    private final static String ID_COLUMN = "id";
    private final static String USER_COLUMN = "user";
    private final static String FACULTY_COLUMN = "faculty";
    private final static String AVERAGE_MARK_COLUMN = "average_mark";
    private final static String STATUS_COLUMN = "status";

    @Override
    protected void initializeMap(Map<String, Object> mapNameFiledValue, Application application) {
        int id = application.getId();
        mapNameFiledValue.put(ID_COLUMN, id);

        int user = application.getUser();
        mapNameFiledValue.put(USER_COLUMN, user);

        int faculty = application.getFaculty();
        mapNameFiledValue.put(FACULTY_COLUMN, faculty);

        int averageMark = application.getAverageMark();
        mapNameFiledValue.put(AVERAGE_MARK_COLUMN, averageMark);

        ApplicationStatus status = application.getStatus();
        String statusValue = status.name();
        mapNameFiledValue.put(STATUS_COLUMN, statusValue);
    }

}