package com.epam.university.service;

import com.epam.university.dao.CertificateDao;
import com.epam.university.dao.DaoException;
import com.epam.university.dao.FacultyDtoDao;
import com.epam.university.dao.UserDtoDao;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ApplicationService {

    private DaoHelperCreator daoHelperCreator;

    public ApplicationService(DaoHelperCreator daoHelperCreator) {

        this.daoHelperCreator = daoHelperCreator;

    }

    public UserDto apply(int userId, int facultyId, String averageMark,
                         List<Integer> idSubjects, List<String> marks) throws ServiceException {

        try (DaoHelper daoHelper = daoHelperCreator.create()) {
// todo transaction
            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
            userDtoDao.updateApplication(userId, facultyId, Integer.parseInt(averageMark), ApplicationStatus.WAITING);

            CertificateDao certificateDao = daoHelper.createCertificateDao();

            for (int i = 0; i < idSubjects.size(); i++) {
                int idSubject = idSubjects.get(i);
                int mark = Integer.parseInt(marks.get(i));
                Certificate certificate = new Certificate(userId, idSubject, mark);

                certificateDao.save(certificate);
            }

            Optional<UserDto> userDto = userDtoDao.findById(userId);

            return userDto.get();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    public UserDto cancel(int userId) throws ServiceException {

        try (DaoHelper daoHelper = daoHelperCreator.create()) {
// todo transaction
            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
            userDtoDao.updateApplication(userId, null, null, null);

            CertificateDao certificateDao = daoHelper.createCertificateDao();
            certificateDao.removeByUser(userId);

            Optional<UserDto> userDto = userDtoDao.findById(userId);

            return userDto.get();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    public int registerApplications() throws ServiceException {

        try (DaoHelper daoHelper = daoHelperCreator.create()) {

            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
            ApplicationStatus registered = ApplicationStatus.REGISTERED;

            return userDtoDao.updateApplicationStatus(registered);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    public Map<Faculty, UserDto> identifyEnrolledApplicants() throws ServiceException {

        try (DaoHelper daoHelper = daoHelperCreator.create()) {
// todo transaction
            FacultyDtoDao facultyDtoDao = daoHelper.createFacultyDtoDao();
            List<FacultyDto> facultiesDto = facultyDtoDao.getAll();

            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();

            for (int i = 0; i < facultiesDto.size(); i++) {

                FacultyDto facultyDto = facultiesDto.get(i);
                int facultyId = facultyDto.getId();
                List<UserDto> facultyApplicants = userDtoDao.findAllByFacultyId(facultyId);
                selectApplicants(facultyApplicants, facultyDto);

            }

            return null;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    private void selectApplicants(List<UserDto> usersDto, FacultyDto facultyDto) throws ServiceException {

        int recruitment = facultyDto.getRecruitment();

        if (usersDto.size() <= recruitment) {
            return;
        }

        Map<UserDto, Integer> balAmounts = new HashMap<>();

        for (UserDto userDto : usersDto) {

            List<Certificate> certificates = findCertificates(userDto);
            int marksSum = findMarksSum(certificates);
            int averageMark = userDto.getAverageMark();
            int balAmount = marksSum + averageMark;
            balAmounts.put(userDto, balAmount);

        }


    }

    private List<Certificate> findCertificates(UserDto userDto) throws ServiceException {

        try (DaoHelper daoHelper = daoHelperCreator.create()) {

            int userId = userDto.getId();

            CertificateDao certificateDao = daoHelper.createCertificateDao();

            return certificateDao.findALlByUser(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    private int findMarksSum(List<Certificate> certificates) {

        int sum = 0;

        for (Certificate certificate : certificates) {
            sum = sum + certificate.getMark();
        }

        return sum;
    }

}