package com.epam.university.tag;

import com.epam.university.model.identifiable.FacultyName;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class FacultyNameTagTest {

    private final FacultyNameTag facultyNameTag = new FacultyNameTag();

    @Test
    public void testGetNameShouldEmptyOptionalWhenFacultyNameIdNotExist() {
        //given
        int facultyId = 10;
        //when
        Optional<FacultyName> actual = facultyNameTag.getName(facultyId);
        //then
        Optional<FacultyName> expected = Optional.empty();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetNameShouldReturnOptionalFacultyNameById() {
        //given
        FacultyName expectedFacultyName = FacultyName.GEOGRAPHICAL;
        int facultyId = expectedFacultyName.getId();
        //when
        Optional<FacultyName> actual = facultyNameTag.getName(facultyId);
        //then
        Optional<FacultyName> expected = Optional.of(FacultyName.GEOGRAPHICAL);
        Assert.assertEquals(expected, actual);
    }

}