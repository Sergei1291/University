package com.epam.university.dao.persistent.helper;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class DaoPersistentHelperImplTest {

    private final static String TABLE_NAME = "testName";
    private final static Map<String, Object> DATA_MAP = new LinkedHashMap<String, Object>() {
        {
            put("2", 2);
            put("1", "facultyName");
            put("3", "cancelled");
        }
    };

    private final DaoPersistentHelper daoPersistentHelper = new DaoPersistentHelperImpl();

    @Test
    public void testCreateQuerySaveShouldCreateCorrectString() {
        //when
        String actual = daoPersistentHelper.createQuerySave(TABLE_NAME, DATA_MAP);
        //then
        String expected = "insert into testName (2,1,3) values (?,?,?);";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCreateQueryUpdateShouldCreateCorrectString() {
        //when
        String actual = daoPersistentHelper.createQueryUpdate(TABLE_NAME, DATA_MAP);
        //then
        String expected = "update testName set 2 = ?,1 = ?,3 = ? where id = ?;";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCreateParamsShouldCreateCorrectObjectsArrayWhenIdEqualZero() {
        //given
        int id = 0;
        //when
        Object[] actual = daoPersistentHelper.createParams(DATA_MAP, id);
        //then
        Object[] expected = new Object[]{2, "facultyName", "cancelled"};
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testCreateParamsShouldCreateCorrectObjectsArrayWhenIdNotEqualZero() {
        //given
        int id = 100;
        //when
        Object[] actual = daoPersistentHelper.createParams(DATA_MAP, id);
        //then
        Object[] expected = new Object[]{2, "facultyName", "cancelled", 100};
        Assert.assertArrayEquals(expected, actual);
    }

}