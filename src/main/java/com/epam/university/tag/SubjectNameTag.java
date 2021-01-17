package com.epam.university.tag;

import com.epam.university.model.identifiable.SubjectName;

public class SubjectNameTag extends AbstractTag<SubjectName> {

    @Override
    protected SubjectName getName(int id) {
        SubjectName foundedSubjectName = null;
        SubjectName[] subjectNames = SubjectName.values();
        for (SubjectName subjectName : subjectNames) {
            int currentIdSubject = subjectName.getId();
            if (currentIdSubject == id) {
                foundedSubjectName = subjectName;
            }
        }
        return foundedSubjectName;
    }

}