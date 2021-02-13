package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.api.FacultyDao;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.model.identifiable.Faculty;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.FacultyService;

import java.util.List;

public class FacultyServiceImpl implements FacultyService {

    protected DaoHelperCreator daoHelperCreator;

    public FacultyServiceImpl(DaoHelperCreator daoHelperCreator) {
        this.daoHelperCreator = daoHelperCreator;
    }

    @Override
    public List<Faculty> findFaculties() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            FacultyDao facultyDao = daoHelper.createFacultyDao();
            return facultyDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}