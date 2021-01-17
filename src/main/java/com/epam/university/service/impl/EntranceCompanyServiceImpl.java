package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.UserDtoDao;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.EntranceCompanyService;

public class EntranceCompanyServiceImpl extends FacultyServiceImpl implements EntranceCompanyService {

    public EntranceCompanyServiceImpl(DaoHelperCreator daoHelperCreator) {
        super(daoHelperCreator);
    }

    @Override
    public int findNumberApplicantsByFaculty(int facultyId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
            return userDtoDao.findNumberUsersByFacultyId(facultyId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}