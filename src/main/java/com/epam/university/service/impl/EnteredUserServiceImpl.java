package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.api.UserDtoDao;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.ApplicationDao;
import com.epam.university.model.identifiable.Application;
import com.epam.university.model.identifiable.ApplicationStatus;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.EnteredUserService;
import com.epam.university.service.impl.comparator.UserDtoSurnameNameComparator;
import com.epam.university.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnteredUserServiceImpl extends RegistrationServiceImpl
        implements EnteredUserService {

    private final static ApplicationStatus ENTERED = ApplicationStatus.ENTERED;

    private final Validator<Integer> validator;

    public EnteredUserServiceImpl(DaoHelperCreator daoHelperCreator, Validator<Integer> validator) {
        super(daoHelperCreator);
        this.validator = validator;
    }

    @Override
    public List<UserDto> findEnteredUsersByFaculty(int facultyId) throws ServiceException {
        if (!validator.isValid(facultyId)) {
            return new ArrayList<>();
        }
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            List<Application> applications =
                    applicationDao.findAllByStatusAndFaculty(ENTERED, facultyId);
            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
            List<UserDto> enteredUsers = new ArrayList<>();
            for (Application application : applications) {
                int userId = application.getUser();
                Optional<UserDto> optionalUserDto = userDtoDao.findById(userId);
                UserDto userDto =
                        optionalUserDto.orElseThrow(() -> new ServiceException("unknown user " + userId));
                enteredUsers.add(userDto);
            }
            return sortAlphabeticSurnameName(enteredUsers);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private List<UserDto> sortAlphabeticSurnameName(List<UserDto> users) {
        List<UserDto> sortedApplicants = new ArrayList<>(users);
        sortedApplicants.sort(new UserDtoSurnameNameComparator());
        return sortedApplicants;
    }

}