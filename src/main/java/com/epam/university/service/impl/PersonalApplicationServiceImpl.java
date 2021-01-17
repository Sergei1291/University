package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.CertificateDao;
import com.epam.university.model.identifiable.Certificate;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.PersonalApplicationService;

import java.util.List;

public class PersonalApplicationServiceImpl extends RegistrationServiceImpl implements PersonalApplicationService {

    public PersonalApplicationServiceImpl(DaoHelperCreator daoHelperCreator) {
        super(daoHelperCreator);
    }

    @Override
    public List<Certificate> findUserCertificates(int userId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            CertificateDao certificateDao = daoHelper.createCertificateDao();
            return certificateDao.findALlByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}