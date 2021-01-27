package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.ApplicationDao;
import com.epam.university.dao.persistent.api.CertificateDao;
import com.epam.university.model.FullApplication;
import com.epam.university.model.identifiable.Application;
import com.epam.university.model.identifiable.Certificate;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.PersonalApplicationService;
import com.epam.university.validator.Validator;

import java.util.List;
import java.util.Optional;

public class PersonalApplicationServiceImpl extends RegistrationServiceImpl
        implements PersonalApplicationService {

    private final Validator<UserDto> validator;

    public PersonalApplicationServiceImpl(DaoHelperCreator daoHelperCreator,
                                          Validator<UserDto> validator) {
        super(daoHelperCreator);
        this.validator = validator;
    }

    @Override
    public Optional<FullApplication> findUserApplication(UserDto userDto) throws ServiceException {
        if (!validator.isValid(userDto)) {
            return Optional.empty();
        }
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            int userId = userDto.getId();
            Optional<Application> optionalApplication = applicationDao.findByUserAndStatusNotCancelled(userId);
            if (!optionalApplication.isPresent()) {
                return Optional.empty();
            }
            Application application = optionalApplication.get();
            int applicationId = application.getId();
            CertificateDao certificateDao = daoHelper.createCertificateDao();
            List<Certificate> certificates = certificateDao.findALlByApplication(applicationId);
            return Optional.of(new FullApplication(application, userDto, certificates));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}