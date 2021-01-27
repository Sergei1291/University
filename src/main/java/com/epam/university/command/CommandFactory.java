package com.epam.university.command;

import com.epam.university.command.impl.*;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.service.impl.*;
import com.epam.university.validator.*;

public class CommandFactory {

    private final static String ACCOUNT = "account";
    private final static String APPLICATION_FORM = "applicationForm";
    private final static String APPLICATIONS_INFO = "applicationsInfo";
    private final static String APPLY = "apply";
    private final static String AUTHORIZATION = "authorization";
    private final static String CANCEL_APPLICATION = "cancelApplication";
    private final static String CLOSE_REGISTRATION = "closeRegistration";
    private final static String ENTERED_APPLICANTS = "enteredApplicants";
    private final static String ENTRANCE_COMPANY = "entranceCompany";
    private final static String FORM_LISTS = "formLists";
    private final static String LOCAL = "local";
    private final static String LOGIN = "login";
    private final static String LOGOUT = "logout";
    private final static String MAIN = "main";
    private final static String PERSONAL_APPLICATION = "personalApplication";
    private final static String SELECTION_FACULTY = "selectionFaculty";
    private final static String STATISTIC_APPLICANTS = "statisticApplicants";
    private final static String SUCCESS_FORM_LISTS = "successFormLists";
    private final static String SUCCESS_REGISTRATION = "successRegistration";

    public static Command create(String command) {
        switch (command) {
            case ACCOUNT:
                return new AccountCommand(new RegistrationServiceImpl(new DaoHelperCreator()),
                        new UserDtoValidator());
            case APPLICATION_FORM:
                return new ApplicationFormCommand(
                        new FacultySubjectServiceImpl(new DaoHelperCreator(), new FacultyIdValidator()),
                        new NumberValidator());
            case APPLICATIONS_INFO:
                return new ApplicationsInfoCommand(
                        new FacultyApplicantServiceImpl(new DaoHelperCreator(), new FacultyIdValidator()),
                        new NumberValidator());
            case APPLY:
                return new ApplyCommand(
                        new ApplicationServiceImpl(new DaoHelperCreator(), new DataApplicationValidator(
                                new UserDtoValidator(), new FacultyIdValidator(),
                                new SubjectIdValidator(), new MarkValidator())),
                        new NumberValidator());
            case AUTHORIZATION:
                return new AuthorizationCommand();
            case CANCEL_APPLICATION:
                return new CancelApplicationCommand(new CancelApplicationServiceImpl(
                        new DaoHelperCreator(), new UserDtoValidator()));
            case CLOSE_REGISTRATION:
                return new CloseRegistrationCommand(new CommitteeServiceImpl(new DaoHelperCreator()));
            case ENTERED_APPLICANTS:
                return new EnteredApplicantsCommand(new EnteredUserServiceImpl(
                        new DaoHelperCreator(), new FacultyIdValidator()),
                        new NumberValidator());
            case ENTRANCE_COMPANY:
                DaoHelperCreator daoHelperCreator = new DaoHelperCreator();
                return new EntranceCompanyCommand(new EntranceCompanyServiceImpl(
                        new FacultySubjectServiceImpl(
                                daoHelperCreator, new FacultyIdValidator()),
                        daoHelperCreator));
            case FORM_LISTS:
                return new FormListsCommand(new CommitteeServiceImpl(new DaoHelperCreator()));
            case LOCAL:
                return new LocalCommand();
            case LOGIN:
                return new LoginCommand(new LoginServiceImpl(new DaoHelperCreator(), new AuthorizationValidator()));
            case LOGOUT:
                return new LogoutCommand();
            case MAIN:
                return new MainCommand(new RegistrationServiceImpl(new DaoHelperCreator()));
            case PERSONAL_APPLICATION:
                return new PersonalApplicationCommand(new PersonalApplicationServiceImpl(
                        new DaoHelperCreator(), new UserDtoValidator()));
            case SELECTION_FACULTY:
                return new SelectionFacultyCommand(new FacultyServiceImpl(new DaoHelperCreator()));
            case STATISTIC_APPLICANTS:
                return new StatisticApplicantsCommand(new StatisticApplicationServiceImpl(
                        new DaoHelperCreator(), new FacultyIdValidator()), new NumberValidator());
            case SUCCESS_FORM_LISTS:
                return new SuccessFormListsCommand(new RegistrationServiceImpl(new DaoHelperCreator()));
            case SUCCESS_REGISTRATION:
                return new SuccessRegistrationCommand(new SuccessRegistrationServiceImpl(new DaoHelperCreator()));
            default:
                throw new IllegalArgumentException("illegal command " + command);
        }
    }

}