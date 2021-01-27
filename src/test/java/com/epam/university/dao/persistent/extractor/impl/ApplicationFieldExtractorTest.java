package com.epam.university.dao.persistent.extractor.impl;

import com.epam.university.model.identifiable.Application;
import com.epam.university.model.identifiable.ApplicationStatus;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class ApplicationFieldExtractorTest {

    private final static int ID = 1;
    private final static int USER = 2;
    private final static int FACULTY = 3;
    private final static int AVERAGE_MARK = 55;
    private final static ApplicationStatus STATUS = ApplicationStatus.APPLIED;
    private final static Application APPLICATION = new Application(ID, USER, FACULTY, AVERAGE_MARK, STATUS);

    private final static Map<String, Object> EXPECTED_RESULT = new LinkedHashMap<String, Object>() {
        {
            put("id", ID);
            put("user", USER);
            put("faculty", FACULTY);
            put("average_mark", AVERAGE_MARK);
            put("status", "APPLIED");
        }
    };

    private final ApplicationFieldExtractor fieldExtractor = new ApplicationFieldExtractor();

    @Test
    public void testExtractShouldReturnMapWhenApplication() {
        //when
        Map<String, Object> actual = fieldExtractor.extract(APPLICATION);
        //then
        Assert.assertEquals(EXPECTED_RESULT, actual);
    }

}