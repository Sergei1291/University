package com.epam.university.dao;

import com.epam.university.mapper.UserDtoRowMapper;
import com.epam.university.model.ApplicationStatus;
import com.epam.university.model.Role;
import com.epam.university.model.UserDto;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDtoDaoImpl extends AbstractDao<UserDto> implements UserDtoDao {

    private static final String TABLE_NAME = "user";

    private static final String FIND_BY_LOGIN_AND_PASSWORD =
            "select * from user where login = ? and password = sha1(?);";
    private static final String UPDATE_USER_APPLICATION =
            "update user set faculty = ? , average_mark = ? , application_status = ? where id = ? ;";
    private static final String UPDATE_APPLICATION_STATUS =
            "update user set application_status = ? where application_status='waiting';";
    private static final String FIND_ALL_BY_FACULTY_ID =
            "select * from user where faculty = ? and application_status = 'registered'";

    public UserDtoDaoImpl(Connection connection) {

        super(connection, new UserDtoRowMapper(), TABLE_NAME);

    }

    @Override
    public Optional<UserDto> findByLoginAndPassword(String login, String password) throws DaoException {

        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD,
                login, password);
    }

    @Override
    public void updateApplication(int userId, Integer facultyId, Integer averageMark,
                                  ApplicationStatus status) throws DaoException {
        if (status != null) {
            executeUpdate(UPDATE_USER_APPLICATION, facultyId, averageMark, status.name(), userId);
            return;
        }

        executeUpdate(UPDATE_USER_APPLICATION, facultyId, averageMark, null, userId);

    }

    @Override
    public int updateApplicationStatus(ApplicationStatus status) throws DaoException {

        return executeUpdate(UPDATE_APPLICATION_STATUS, status.name());
    }

    @Override
    public List<UserDto> findAllByFacultyId(int facultyId) throws DaoException {

        return executeQuery(FIND_ALL_BY_FACULTY_ID, facultyId);
    }

}