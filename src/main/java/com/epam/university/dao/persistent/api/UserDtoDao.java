package com.epam.university.dao.persistent.api;

import com.epam.university.dao.DaoException;
import com.epam.university.model.identifiable.user.ApplicationStatus;
import com.epam.university.model.identifiable.user.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserDtoDao extends PersistentDao<UserDto> {

    int findNumberUsersByApplicationStatus(ApplicationStatus status) throws DaoException;

    int findNumberUsersByFacultyId(Integer facultyId) throws DaoException;

    Optional<UserDto> findByLoginAndPassword(String login, String password) throws DaoException;

    void changeAllApplicationStatus(ApplicationStatus dataStatus,
                                    ApplicationStatus replaceStatus) throws DaoException;

    List<UserDto> findAllByApplicationStatusAndFacultyId(ApplicationStatus status,
                                                         Integer facultyId) throws DaoException;

    void changeApplicationStatusByUserId(int userId, ApplicationStatus applicationStatus) throws DaoException;

    List<UserDto> findAllByFacultyIdAndApplicationStatusNotNull(Integer facultyId) throws DaoException;

    List<Integer> findUsersAmountsAverageMarkAndMarksCertificateByFacultyId(Integer facultyId) throws DaoException;

}