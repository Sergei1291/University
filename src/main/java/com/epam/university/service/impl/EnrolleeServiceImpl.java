package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.ApplicationDao;
import com.epam.university.dao.persistent.api.CertificateDao;
import com.epam.university.model.identifiable.Application;
import com.epam.university.model.identifiable.ApplicationStatus;
import com.epam.university.model.identifiable.Certificate;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.EnrolleeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class EnrolleeServiceImpl extends RegistrationServiceImpl implements EnrolleeService {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static ApplicationStatus APPLIED = ApplicationStatus.APPLIED;
    private final static ApplicationStatus CANCELLED = ApplicationStatus.CANCELLED;

    public EnrolleeServiceImpl(DaoHelperCreator daoHelperCreator) {
        super(daoHelperCreator);
    }

    @Override
    public boolean apply(UserDto userDto, int facultyId, int averageMark,
                         Map<Integer, Integer> subjectsMarks) throws ServiceException {
        DaoHelper daoHelper = null;
        try {
            daoHelper = daoHelperCreator.create();

            int userId = userDto.getId();
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            Optional<Application> applicationOptionalCheck =
                    applicationDao.findByUserAndStatus(userId, APPLIED);
            if (applicationOptionalCheck.isPresent()) {
                return false;
            }

            daoHelper.startTransaction();
            Application application = new Application(0, userId, facultyId, averageMark, APPLIED);
            applicationDao.save(application);
            Optional<Application> applicationOptional =
                    applicationDao.findByUserAndStatus(userId, APPLIED);
            Application correctApplication = applicationOptional.get();

            CertificateDao certificateDao = daoHelper.createCertificateDao();
            saveCertificates(certificateDao, correctApplication, subjectsMarks);

            daoHelper.commitTransaction();
            return true;
        } catch (DaoException e) {
            try {
                daoHelper.rollBackTransaction();
            } catch (DaoException daoException) {
                LOGGER.error(daoException.getMessage(), daoException);
            }
            throw new ServiceException(e.getMessage(), e);
        } finally {
            if (daoHelper != null) {
                try {
                    daoHelper.finishTransaction();
                } catch (DaoException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                daoHelper.close();
            }
        }
    }

    @Override
    public boolean cancelApplication(UserDto userDto) throws ServiceException {
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

    private void saveCertificates(CertificateDao certificateDao, Application application,
                                  Map<Integer, Integer> subjectsMarks) throws DaoException {
        int applicationId = application.getId();
        Set<Integer> subjectIdSet = subjectsMarks.keySet();
        for (Integer subjectId : subjectIdSet) {
            Integer mark = subjectsMarks.get(subjectId);
            Certificate certificate = new Certificate(0, applicationId, subjectId, mark);
            certificateDao.save(certificate);
        }
    }

}