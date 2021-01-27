package com.epam.university.tag;

import com.epam.university.model.identifiable.SubjectName;

import java.util.Optional;

public class SubjectNameTag extends AbstractTag<SubjectName> {

    @Override
    protected Optional<SubjectName> getName(int subjectId) {
        Optional<SubjectName> optionalSubjectName = Optional.empty();
        SubjectName[] subjectNames = SubjectName.values();
        for (SubjectName subjectName : subjectNames) {
            int currentSubjectId = subjectName.getId();
            if (currentSubjectId == subjectId) {
                optionalSubjectName = Optional.of(subjectName);
            }
        }
        return optionalSubjectName;
    }

}