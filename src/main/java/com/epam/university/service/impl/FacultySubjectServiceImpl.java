package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.api.SubjectDao;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.model.identifiable.Subject;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.FacultySubjectService;
import com.epam.university.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class FacultySubjectServiceImpl implements FacultySubjectService {

    protected DaoHelperCreator daoHelperCreator;
    private final Validator<Integer> validator;

    public FacultySubjectServiceImpl(DaoHelperCreator daoHelperCreator, Validator<Integer> validator) {
        this.daoHelperCreator = daoHelperCreator;
        this.validator = validator;
    }

    @Override
    public List<Subject> findFacultySubjects(int facultyId) throws ServiceException {
        if (!validator.isValid(facultyId)) {
            return new ArrayList<>();
        }
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            SubjectDao subjectDao = daoHelper.createSubjectDao();
            return subjectDao.findAllByFaculty(facultyId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}