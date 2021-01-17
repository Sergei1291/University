package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.UserDtoDao;
import com.epam.university.model.identifiable.user.ApplicationStatus;
import com.epam.university.model.identifiable.user.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.EnteredApplicantService;
import com.epam.university.service.impl.comparator.UserDtoSurnameNameComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnteredApplicantServiceImpl extends RegistrationServiceImpl
        implements EnteredApplicantService {

    private final static ApplicationStatus ENTERED = ApplicationStatus.ENTERED;

    public EnteredApplicantServiceImpl(DaoHelperCreator daoHelperCreator) {
        super(daoHelperCreator);
    }

    @Override
    public List<UserDto> findEnteredApplicantsByFaculty(int facultyId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
            List<UserDto> enteredApplicants =
                    userDtoDao.findAllByApplicationStatusAndFacultyId(ENTERED, facultyId);
            return sortAlphabeticSurnameName(enteredApplicants);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private List<UserDto> sortAlphabeticSurnameName(List<UserDto> applicants) {
        List<UserDto> sortedApplicants = new ArrayList<>(applicants);
        Collections.sort(sortedApplicants, new UserDtoSurnameNameComparator());
        return sortedApplicants;
    }

}