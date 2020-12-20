package com.epam.university.command;

import com.epam.university.command.impl.*;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.service.FacultyService;
import com.epam.university.service.ApplicationService;
import com.epam.university.service.UserDtoService;

public class CommandFactory {

    private final static String MAIN = "main";
    private final static String RECRUITMENT = "recruitment";
    private final static String LOCAL = "local";
    private final static String AUTHORIZATION = "authorization";
    private final static String LOGIN = "login";
    private final static String LOGOUT = "logout";
    private final static String SELECTION = "selection";
    private final static String APPLICATION = "application";
    private final static String APPLY = "apply";
    private final static String CANCEL = "cancel";
    private final static String REGISTER = "register";

    public static Command create(String command) {

        switch (command) {
            case MAIN:
                return new MainCommand();
            case RECRUITMENT:
                return new RecruitmentCommand(new FacultyService(new DaoHelperCreator()));
            case LOCAL:
                return new LocalCommand();
            case AUTHORIZATION:
                return new AuthorizationCommand();
            case LOGIN:
                return new LoginCommand(new UserDtoService(new DaoHelperCreator()));
            case LOGOUT:
                return new LogoutCommand();
            case SELECTION:
                return new SelectionCommand(new FacultyService(new DaoHelperCreator()));
            case APPLICATION:
                return new ApplicationCommand(new FacultyService(new DaoHelperCreator()));
            case APPLY:
                return new ApplyCommand(new ApplicationService(new DaoHelperCreator()));
            case CANCEL:
                return new CancelCommand(new ApplicationService(new DaoHelperCreator()));
            case REGISTER:
                return new RegisterCommand(new ApplicationService(new DaoHelperCreator()));
            default:
                throw new IllegalArgumentException("illegal command " + command);
        }

    }

}