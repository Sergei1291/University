package com.epam.university.service;

import com.epam.university.dao.*;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.model.*;

import java.util.ArrayList;
import java.util.List;

public class FacultyService {

    private DaoHelperCreator daoHelperCreator;

    public FacultyService(DaoHelperCreator daoHelperCreator) {

        this.daoHelperCreator = daoHelperCreator;

    }

    public List<Faculty> findFaculties() throws ServiceException {

        List<FacultyDto> facultiesDto = findFacultiesDto();

        List<Faculty> faculties = new ArrayList<>();

        for (FacultyDto facultyDto : facultiesDto) {

            int idFaculty = facultyDto.getId();
            FacultyName facultyName = facultyDto.getName();
            List<Subject> subjects = findSubjects(idFaculty);
            int recruitment = facultyDto.getRecruitment();

            Faculty faculty = new Faculty(idFaculty, facultyName, recruitment, subjects);

            faculties.add(faculty);
        }

        return faculties;
    }

    public List<FacultyDto> findFacultiesDto() throws ServiceException {

        try (DaoHelper daoHelper = daoHelperCreator.create()) {

            FacultyDtoDao facultyDtoDao = daoHelper.createFacultyDtoDao();
            List<FacultyDto> facultiesDto = facultyDtoDao.getAll();

            return facultiesDto;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    public List<Subject> findSubjects(int idFaculty) throws ServiceException {

        try (DaoHelper daoHelper = daoHelperCreator.create()) {

            SubjectDao subjectDao = daoHelper.createSubjectDao();

            return subjectDao.findByFaculty(idFaculty);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

}