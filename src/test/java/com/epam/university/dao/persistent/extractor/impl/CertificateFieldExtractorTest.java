package com.epam.university.dao.persistent.extractor.impl;

import com.epam.university.model.identifiable.Certificate;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class CertificateFieldExtractorTest {

    private final static int ID = 3;
    private final static int APPLICATION = 44;
    private final static int SUBJECT = 3;
    private final static int MARK = 55;
    private final static Certificate CERTIFICATE = new Certificate(ID, APPLICATION, SUBJECT, MARK);

    private final static Map<String, Object> EXPECTED_RESULT = new LinkedHashMap<String, Object>() {
        {
            put("id", ID);
            put("application", APPLICATION);
            put("subject", SUBJECT);
            put("mark", MARK);
        }
    };

    private final CertificateFieldExtractor fieldExtractor = new CertificateFieldExtractor();

    @Test
    public void testExtractShouldReturnMapWhenCertificate() {
        //when
        Map<String, Object> actual = fieldExtractor.extract(CERTIFICATE);
        //then
        Assert.assertEquals(EXPECTED_RESULT, actual);
    }

}