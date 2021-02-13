package com.epam.university.tag;

import com.epam.university.model.identifiable.FacultyName;

import java.util.Optional;

public class FacultyNameTag extends AbstractTag<FacultyName> {

    @Override
    protected Optional<FacultyName> getName(int facultyId) {
        Optional<FacultyName> optionalFacultyName = Optional.empty();
        FacultyName[] facultyNames = FacultyName.values();
        for (FacultyName facultyName : facultyNames) {
            int currentFacultyId = facultyName.getId();
            if (currentFacultyId == facultyId) {
                optionalFacultyName = Optional.of(facultyName);
            }
        }
        return optionalFacultyName;
    }

}