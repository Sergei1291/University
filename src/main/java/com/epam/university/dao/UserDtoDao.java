package com.epam.university.dao;

import com.epam.university.model.ApplicationStatus;
import com.epam.university.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserDtoDao extends Dao<UserDto> {

    Optional<UserDto> findByLoginAndPassword(String login, String password) throws DaoException;

    void updateApplication(int userId, Integer facultyId, Integer averageMark, ApplicationStatus status)
            throws DaoException;

    int updateApplicationStatus(ApplicationStatus status) throws DaoException;

    List<UserDto> findAllByFacultyId(int facultyId) throws DaoException;

}