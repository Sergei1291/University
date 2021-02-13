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
import com.epam.university.service.api.ApplicationService;
import com.epam.university.validator.DataApplicationValidator;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ApplicationServiceImpl extends RegistrationServiceImpl implements ApplicationService {

    private final static ApplicationStatus APPLIED = ApplicationStatus.APPLIED;

    private final DataApplicationValidator dataApplicationValidator;

    public ApplicationServiceImpl(DaoHelperCreator daoHelperCreator, DataApplicationValidator dataApplicationValidator) {
        super(daoHelperCreator);
        this.dataApplicationValidator = dataApplicationValidator;
    }

    @Override
    public boolean apply(UserDto userDto, int facultyId, int averageMark,
                         Map<Integer, Integer> subjectsMarks) throws ServiceException {
        if (!dataApplicationValidator.isValid(userDto, facultyId, averageMark, subjectsMarks)) {
            return false;
        }
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
            Application appliedApplication =
                    applicationOptional.orElseThrow(() -> new ServiceException("unknown application by user " + userId));

            CertificateDao certificateDao = daoHelper.createCertificateDao();
            saveCertificates(certificateDao, appliedApplication, subjectsMarks);

            daoHelper.commitTransaction();
            return true;
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