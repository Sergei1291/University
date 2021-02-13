package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.ApplicationDao;
import com.epam.university.model.identifiable.Application;
import com.epam.university.model.identifiable.ApplicationStatus;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.CancelApplicationService;
import com.epam.university.validator.Validator;

import java.util.Optional;

public class CancelApplicationServiceImpl extends RegistrationServiceImpl implements CancelApplicationService {

    private final static ApplicationStatus APPLIED = ApplicationStatus.APPLIED;
    private final static ApplicationStatus CANCELLED = ApplicationStatus.CANCELLED;

    private final Validator<UserDto> validator;

    public CancelApplicationServiceImpl(DaoHelperCreator daoHelperCreator, Validator<UserDto> validator) {
        super(daoHelperCreator);
        this.validator = validator;
    }

    @Override
    public boolean cancelApplication(UserDto userDto) throws ServiceException {
        if (!validator.isValid(userDto)) {
            return false;
        }
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            int userId = userDto.getId();
            Optional<Application> applicationOptional = applicationDao.findByUserAndStatus(userId, APPLIED);
            if (!applicationOptional.isPresent()) {
                return false;
            }
            Application application = applicationOptional.get();
            int applicationId = application.getId();
            applicationDao.changeStatusById(applicationId, CANCELLED);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}