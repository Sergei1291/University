package com.epam.university.dao.persistent.extractor.impl;

import com.epam.university.dao.persistent.extractor.AbstractFieldExtractor;
import com.epam.university.model.identifiable.Certificate;

import java.util.Map;

public class CertificateFieldExtractor extends AbstractFieldExtractor<Certificate> {

    private final static String ID_COLUMN = "id";
    private final static String APPLICATION_COLUMN = "application";
    private final static String SUBJECT_COLUMN = "subject";
    private final static String MARK_COLUMN = "mark";

    @Override
    protected void initializeMap(Map<String, Object> mapNameFiledValue, Certificate certificate) {
        int id = certificate.getId();
        mapNameFiledValue.put(ID_COLUMN, id);

        int application = certificate.getApplication();
        mapNameFiledValue.put(APPLICATION_COLUMN, application);

        int subject = certificate.getSubject();
        mapNameFiledValue.put(SUBJECT_COLUMN, subject);

        int mark = certificate.getMark();
        mapNameFiledValue.put(MARK_COLUMN, mark);
    }

}