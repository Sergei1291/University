package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.api.SubjectDao;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.model.identifiable.Faculty;
import com.epam.university.model.identifiable.FacultyDto;
import com.epam.university.model.identifiable.FacultyName;
import com.epam.university.model.identifiable.Subject;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.FacultyService;

import java.util.ArrayList;
import java.util.List;

public class FacultyServiceImpl extends FacultyDtoServiceImpl implements FacultyService {

    public FacultyServiceImpl(DaoHelperCreator daoHelperCreator) {
        super(daoHelperCreator);
    }

    @Override
    public List<Faculty> findFaculties() throws ServiceException {
        List<FacultyDto> facultiesDto = findFacultiesDto();
        List<Faculty> faculties = new ArrayList<>();
        for (FacultyDto facultyDto : facultiesDto) {
            Faculty faculty = complete(facultyDto);
            faculties.add(faculty);
        }
        return faculties;
    }

    @Override
    public List<Subject> findFacultySubjects(int facultyId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            SubjectDao subjectDao = daoHelper.createSubjectDao();
            return subjectDao.findByFacultyId(facultyId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private Faculty complete(FacultyDto facultyDto) throws ServiceException {
        int facultyId = facultyDto.getId();
        FacultyName facultyName = facultyDto.getName();
        int recruitment = facultyDto.getRecruitment();
        List<Subject> subjects = findFacultySubjects(facultyId);
        return new Faculty(facultyId, facultyName, recruitment, subjects);
    }

}