package com.epam.university.tag;

import com.epam.university.model.identifiable.FacultyName;

public class FacultyNameTag extends AbstractTag<FacultyName> {

    @Override
    protected FacultyName getName(int idFaculty) {
        FacultyName foundedFacultyName = null;
        FacultyName[] facultyNames = FacultyName.values();
        for (FacultyName facultyName : facultyNames) {
            int currentIdFaculty = facultyName.getId();
            if (currentIdFaculty == idFaculty) {
                foundedFacultyName = facultyName;
            }
        }
        return foundedFacultyName;
    }

}