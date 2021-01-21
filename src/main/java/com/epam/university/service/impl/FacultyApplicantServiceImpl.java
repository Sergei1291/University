package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.api.UserDtoDao;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.ApplicationDao;
import com.epam.university.dao.persistent.api.CertificateDao;
import com.epam.university.model.FullApplication;
import com.epam.university.model.identifiable.Application;
import com.epam.university.model.identifiable.Certificate;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.FacultyApplicantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FacultyApplicantServiceImpl implements FacultyApplicantService {

    private DaoHelperCreator daoHelperCreator;

    public FacultyApplicantServiceImpl(DaoHelperCreator daoHelperCreator) {
        this.daoHelperCreator = daoHelperCreator;
    }

    @Override
    public List<FullApplication> findFacultyApplicationsInfo(int facultyId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            List<Application> applications = applicationDao.findAllByFaculty(facultyId);

            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
            CertificateDao certificateDao = daoHelper.createCertificateDao();
            List<FullApplication> applicationsInfo = new ArrayList<>();
            for (Application application : applications) {
                int userId = application.getUser();
                Optional<UserDto> optionalUserDto = userDtoDao.findById(userId);
                UserDto userDto = optionalUserDto.get();

                int applicationId = application.getId();
                List<Certificate> certificates
                        = certificateDao.findALlByApplication(applicationId);
                FullApplication fullApplication = new FullApplication(application, userDto, certificates);
                applicationsInfo.add(fullApplication);
            }
            return applicationsInfo;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}