package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.api.FacultyDao;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.ApplicationDao;
import com.epam.university.dao.persistent.api.CertificateDao;
import com.epam.university.model.ApplicationDto;
import com.epam.university.model.identifiable.Application;
import com.epam.university.model.identifiable.ApplicationStatus;
import com.epam.university.model.identifiable.Faculty;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.CommitteeService;
import com.epam.university.service.impl.comparator.ApplicationDtoMarksAmountComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommitteeServiceImpl extends RegistrationServiceImpl implements CommitteeService {

    private final static ApplicationStatus APPLIED = ApplicationStatus.APPLIED;
    private final static ApplicationStatus REGISTERED = ApplicationStatus.REGISTERED;
    private final static ApplicationStatus ENTERED = ApplicationStatus.ENTERED;
    private final static ApplicationStatus UNENTERED = ApplicationStatus.UNENTERED;

    public CommitteeServiceImpl(DaoHelperCreator daoHelperCreator) {
        super(daoHelperCreator);
    }

    @Override
    public void closeRegistration() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            applicationDao.replaceAllStatus(APPLIED, REGISTERED);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void formListApplicants() throws ServiceException {
        DaoHelper daoHelper = null;
        try {
            daoHelper = daoHelperCreator.create();
            daoHelper.startTransaction();

            FacultyDao facultyDao = daoHelper.createFacultyDao();
            List<Faculty> faculties = facultyDao.findAll();

            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            CertificateDao certificateDao = daoHelper.createCertificateDao();

            for (Faculty faculty : faculties) {
                int facultyId = faculty.getId();
                List<Application> registeredApplications =
                        applicationDao.findAllByStatusAndFaculty(REGISTERED, facultyId);
                List<ApplicationDto> registeredApplicationsDto = transform(certificateDao,
                        registeredApplications);
                List<ApplicationDto> enteredApplicationsDto = findEnteredApplications(faculty,
                        registeredApplicationsDto);
                for (ApplicationDto applicationDto : enteredApplicationsDto) {
                    int applicationId = applicationDto.getId();
                    applicationDao.changeStatusById(applicationId, ENTERED);
                }
                List<Application> unenteredApplications =
                        applicationDao.findAllByStatusAndFaculty(REGISTERED, facultyId);
                for (Application application : unenteredApplications) {
                    int applicationId = application.getId();
                    applicationDao.changeStatusById(applicationId, UNENTERED);
                }
            }
            daoHelper.commitTransaction();
        } catch (DaoException e) {
            daoHelper.rollBackTransaction();
            throw new ServiceException(e.getMessage(), e);
        } finally {
            if (daoHelper != null) {
                daoHelper.finishTransaction();
                daoHelper.close();
            }
        }
    }

    private List<ApplicationDto> transform(CertificateDao certificateDao, List<Application> applications)
            throws DaoException {
        List<ApplicationDto> applicationsDto = new ArrayList<>();
        for (Application application : applications) {
            int applicationId = application.getId();
            int marksAmount = findMarksAmount(certificateDao, application);
            applicationsDto.add(new ApplicationDto(applicationId, marksAmount));
        }
        return applicationsDto;
    }

    private int findMarksAmount(CertificateDao certificateDao, Application application) throws DaoException {
        int applicationId = application.getId();
        int marksSum = certificateDao.findSumMarksByApplication(applicationId);
        int averageMark = application.getAverageMark();
        return marksSum + averageMark;
    }

    private List<ApplicationDto> findEnteredApplications(Faculty faculty, List<ApplicationDto> applicationsDto) {
        int recruitment = faculty.getRecruitment();
        if (applicationsDto.size() <= recruitment) {
            return new ArrayList<>(applicationsDto);
        }
        List<ApplicationDto> sortedApplications = new ArrayList<>(applicationsDto);
        sortedApplications.sort(new ApplicationDtoMarksAmountComparator());
        return new ArrayList<>(sortedApplications.subList(0, recruitment));
    }

}