package com.epam.university.service.impl;

import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.model.FacultyDto;
import com.epam.university.model.identifiable.Faculty;
import com.epam.university.model.identifiable.FacultyName;
import com.epam.university.model.identifiable.Subject;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.FacultyDtoService;
import com.epam.university.service.api.FacultySubjectService;

import java.util.ArrayList;
import java.util.List;

public class FacultyDtoServiceImpl extends FacultyServiceImpl implements FacultyDtoService {

    private final FacultySubjectService facultySubjectService;

    public FacultyDtoServiceImpl(DaoHelperCreator daoHelperCreator,
                                 FacultySubjectService facultySubjectService) {
        super(daoHelperCreator);
        this.facultySubjectService = facultySubjectService;
    }

    @Override
    public List<FacultyDto> findFacultiesDto() throws ServiceException {
        List<Faculty> faculties = findFaculties();
        List<FacultyDto> facultiesDto = new ArrayList<>();
        for (Faculty faculty : faculties) {
            FacultyDto facultyDto = complete(faculty);
            facultiesDto.add(facultyDto);
        }
        return facultiesDto;
    }

    private FacultyDto complete(Faculty faculty) throws ServiceException {
        int facultyId = faculty.getId();
        FacultyName facultyName = faculty.getName();
        int recruitment = faculty.getRecruitment();
        List<Subject> subjects = facultySubjectService.findFacultySubjects(facultyId);
        return new FacultyDto(facultyId, facultyName, recruitment, subjects);
    }

}