package com.epam.university.service.impl;

import com.epam.university.dao.CertificateDao;
import com.epam.university.dao.DaoException;
import com.epam.university.dao.FacultyDtoDao;
import com.epam.university.dao.UserDtoDao;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.model.Certificate;
import com.epam.university.model.FacultyDto;
import com.epam.university.model.user.ApplicationStatus;
import com.epam.university.model.user.UserDto;
import com.epam.university.model.user.Enrollee;
import com.epam.university.service.AbstractService;
import com.epam.university.service.ApplicationService;
import com.epam.university.service.ServiceException;
import com.epam.university.service.comparator.EnrolleeBalAmountComparator;
import com.epam.university.service.comparator.EnrolleeSurnameNameComparator;

import java.util.*;

public class ApplicationServiceImpl extends AbstractService implements ApplicationService {

    private final static ApplicationStatus WAITING = ApplicationStatus.WAITING;
    private final static ApplicationStatus REGISTERED = ApplicationStatus.REGISTERED;

    private final static int MIN_NUMBER_REGISTERED_USERS = 1;

    private DaoHelperCreator daoHelperCreator;

    public ApplicationServiceImpl(DaoHelperCreator daoHelperCreator) {

        this.daoHelperCreator = daoHelperCreator;

    }

    @Override
    public boolean isRegistrationFinished() throws ServiceException {

        try (DaoHelper daoHelper = daoHelperCreator.create()) {

            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();

            int numberUsers = userDtoDao.findNumberUsersApplicationStatus(REGISTERED);

            if (numberUsers >= MIN_NUMBER_REGISTERED_USERS) {
                return true;
            }

            return false;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    @Override
    public UserDto apply(int userId, int facultyId, int averageMark,
                         Map<Integer, Integer> subjectsMarks) throws ServiceException {

        DaoHelper daoHelper = null;

        try {
            daoHelper = daoHelperCreator.create();

            daoHelper.startTransaction();

            saveCertificates(daoHelper, userId, subjectsMarks);
            Optional<UserDto> optionalUserDto =
                    updateUserApplication(daoHelper, userId, facultyId, averageMark, WAITING);

            daoHelper.commitTransaction();

            return optionalUserDto.get();
        } catch (DaoException e) {
            rollbackTransaction(daoHelper, e);
            throw new ServiceException(e.getMessage(), e);
        } finally {
            if (daoHelper != null) {
                finishTransaction(daoHelper);
                daoHelper.close();
            }
        }

    }

    @Override
    public UserDto cancelApplication(int userId) throws ServiceException {

        DaoHelper daoHelper = null;

        try {
            daoHelper = daoHelperCreator.create();

            daoHelper.startTransaction();

            removeCertificates(daoHelper, userId);
            Optional<UserDto> userDto =
                    updateUserApplication(daoHelper, userId, null, null, null);

            daoHelper.commitTransaction();

            return userDto.get();
        } catch (DaoException e) {
            rollbackTransaction(daoHelper, e);
            throw new ServiceException(e.getMessage(), e);
        } finally {
            if (daoHelper != null) {
                finishTransaction(daoHelper);
                daoHelper.close();
            }
        }

    }

    @Override
    public int registerApplications() throws ServiceException {

        try (DaoHelper daoHelper = daoHelperCreator.create()) {

            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();

            return userDtoDao.changeAllApplicationStatus(WAITING, REGISTERED);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    private void saveCertificates(DaoHelper daoHelper, int userId, Map<Integer, Integer> subjectsMarks)
            throws DaoException {

        CertificateDao certificateDao = daoHelper.createCertificateDao();

        Set<Integer> idSubjects = subjectsMarks.keySet();
        Iterator<Integer> iterator = idSubjects.iterator();

        while (iterator.hasNext()) {

            Integer idSubject = iterator.next();
            Integer mark = subjectsMarks.get(idSubject);
            Certificate certificate = new Certificate(userId, idSubject, mark);

            certificateDao.save(certificate);
        }

    }

    private void removeCertificates(DaoHelper daoHelper, int userId) throws DaoException {

        CertificateDao certificateDao = daoHelper.createCertificateDao();
        certificateDao.removeByUser(userId);

    }

    private Optional<UserDto> updateUserApplication(DaoHelper daoHelper, int userId, Integer facultyId,
                                                    Integer averageMark, ApplicationStatus status)
            throws DaoException {

        UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
        userDtoDao.updateApplication(userId, facultyId, averageMark, status);

        return userDtoDao.findById(userId);
    }

    @Override
    public List<Enrollee> findEnrolledApplicantsByFaculty(int idFaculty) throws ServiceException {

        DaoHelper daoHelper = null;

        try {
            daoHelper = daoHelperCreator.create();

            daoHelper.startTransaction();

            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();

            List<UserDto> usersDtoRegistered =
                    userDtoDao.findAllByApplicationStatusAndFaculty(REGISTERED, idFaculty);
            List<Enrollee> registeredEnrollees = transform(daoHelper, usersDtoRegistered);

            FacultyDtoDao facultyDtoDao = daoHelper.createFacultyDtoDao();
            Optional<FacultyDto> optionalFacultyDto = facultyDtoDao.findById(idFaculty);

            daoHelper.commitTransaction();

            FacultyDto facultyDto = optionalFacultyDto.get();
            int recruitment = facultyDto.getRecruitment();

            List<Enrollee> enteredEnrollees = findEnteredEnrollees(registeredEnrollees, recruitment);

            List<Enrollee> sortedEnrollees = new ArrayList<>(enteredEnrollees);
            Collections.sort(sortedEnrollees, new EnrolleeSurnameNameComparator());

            return sortedEnrollees;
        } catch (DaoException e) {
            rollbackTransaction(daoHelper, e);
            throw new ServiceException(e.getMessage(), e);
        } finally {
            if (daoHelper != null) {
                finishTransaction(daoHelper);
                daoHelper.close();
            }
        }

    }

    private List<Enrollee> transform(DaoHelper daoHelper, List<UserDto> usersDto)
            throws DaoException {

        List<Enrollee> enrollees = new LinkedList<>();

        for (UserDto userDto : usersDto) {

            String userName = userDto.getName();
            String userSurname = userDto.getSurname();
            int balAmount = findBalAmount(daoHelper, userDto);

            enrollees.add(new Enrollee(userName, userSurname, balAmount));

        }

        return enrollees;
    }

    private int findBalAmount(DaoHelper daoHelper, UserDto userDto) throws DaoException {

        List<Certificate> certificates = findCertificates(daoHelper, userDto);
        int marksSum = findMarksSum(certificates);
        int averageMark = userDto.getAverageMark();

        return marksSum + averageMark;
    }

    private List<Certificate> findCertificates(DaoHelper daoHelper, UserDto userDto) throws DaoException {

        CertificateDao certificateDao = daoHelper.createCertificateDao();

        int userId = userDto.getId();

        return certificateDao.findALlByUser(userId);
    }

    private int findMarksSum(List<Certificate> certificates) {

        int sum = 0;

        for (Certificate certificate : certificates) {
            sum = sum + certificate.getMark();
        }

        return sum;
    }

    private List<Enrollee> findEnteredEnrollees(List<Enrollee> enrollees, int recruitment) {

        if (enrollees.size() <= recruitment) {

            return new ArrayList<>(enrollees);
        }

        List<Enrollee> sortedEnrollees = new ArrayList<>(enrollees);

        Collections.sort(sortedEnrollees, new EnrolleeBalAmountComparator());

        return new ArrayList<>(sortedEnrollees.subList(0, recruitment));
    }

}