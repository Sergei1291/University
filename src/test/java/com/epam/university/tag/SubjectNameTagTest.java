package com.epam.university.tag;

import com.epam.university.model.identifiable.SubjectName;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class SubjectNameTagTest {

    private final SubjectNameTag subjectNameTag = new SubjectNameTag();

    @Test
    public void testGetNameShouldEmptyOptionalWhenSubjectNameIdNotExist() {
        //given
        int subjectId = 10;
        //when
        Optional<SubjectName> actual = subjectNameTag.getName(subjectId);
        //then
        Optional<SubjectName> expected = Optional.empty();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetNameShouldReturnOptionalSubjectNameById() {
        //given
        SubjectName expectedSubjectName = SubjectName.MATHEMATICS;
        int subjectId = expectedSubjectName.getId();
        //when
        Optional<SubjectName> actual = subjectNameTag.getName(subjectId);
        //then
        Optional<SubjectName> expected = Optional.of(SubjectName.MATHEMATICS);
        Assert.assertEquals(expected, actual);
    }

}