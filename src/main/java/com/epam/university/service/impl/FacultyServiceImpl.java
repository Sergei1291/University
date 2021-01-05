package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.FacultyDtoDao;
import com.epam.university.dao.SubjectDao;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.model.Faculty;
import com.epam.university.model.FacultyDto;
import com.epam.university.model.FacultyName;
import com.epam.university.model.Subject;
import com.epam.university.service.AbstractService;
import com.epam.university.service.FacultyService;
import com.epam.university.service.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FacultyServiceImpl extends AbstractService implements FacultyService {

    private DaoHelperCreator daoHelperCreator;

    public FacultyServiceImpl(DaoHelperCreator daoHelperCreator) {

        this.daoHelperCreator = daoHelperCreator;

    }

    @Override
    public List<Faculty> findFaculties() throws ServiceException {

        List<FacultyDto> facultiesDto = findFacultiesDto();

        List<Faculty> faculties = new ArrayList<>();

        for (FacultyDto facultyDto : facultiesDto) {

            int idFaculty = facultyDto.getId();
            FacultyName facultyName = facultyDto.getName();
            int recruitment = facultyDto.getRecruitment();

            List<Subject> subjects = findSubjects(idFaculty);

            Faculty faculty = new Faculty(idFaculty, facultyName, recruitment, subjects);

            faculties.add(faculty);
        }

        return faculties;
    }

    @Override
    public List<FacultyDto> findFacultiesDto() throws ServiceException {

        try (DaoHelper daoHelper = daoHelperCreator.create()) {

            FacultyDtoDao facultyDtoDao = daoHelper.createFacultyDtoDao();

            return facultyDtoDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    @Override
    public List<Subject> findSubjects(int idFaculty) throws ServiceException {

        try (DaoHelper daoHelper = daoHelperCreator.create()) {

            SubjectDao subjectDao = daoHelper.createSubjectDao();

            return subjectDao.findByIdFaculty(idFaculty);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    @Override
    public FacultyDto findFaculty(int idFaculty) throws ServiceException {

        try (DaoHelper daoHelper = daoHelperCreator.create()) {

            FacultyDtoDao facultyDtoDao = daoHelper.createFacultyDtoDao();

            Optional<FacultyDto> optionalFacultyDto = facultyDtoDao.findById(idFaculty);

            return optionalFacultyDto.get();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

}