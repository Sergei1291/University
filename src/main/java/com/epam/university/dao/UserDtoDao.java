package com.epam.university.dao;

import com.epam.university.model.user.ApplicationStatus;
import com.epam.university.model.user.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserDtoDao extends Dao<UserDto> {

    int findNumberUsersApplicationStatus(ApplicationStatus status) throws DaoException;

    Optional<UserDto> findByLoginAndPassword(String login, String password) throws DaoException;

    void updateApplication(int userId, Integer facultyId, Integer averageMark, ApplicationStatus status)
            throws DaoException;

    int changeAllApplicationStatus(ApplicationStatus dataStatus,
                                   ApplicationStatus replaceStatus) throws DaoException;

    List<UserDto> findAllByApplicationStatusAndFaculty(ApplicationStatus status,
                                                       Integer faculty) throws DaoException;

}