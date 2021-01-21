package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.ApplicationDao;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.EntranceCompanyService;

public class EntranceCompanyServiceImpl extends FacultyServiceImpl implements EntranceCompanyService {

    public EntranceCompanyServiceImpl(DaoHelperCreator daoHelperCreator) {
        super(daoHelperCreator);
    }

    @Override
    public int findNumberApplicationsByFaculty(int facultyId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            return applicationDao.findNumberActualApplicationsByFaculty(facultyId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}