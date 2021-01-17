package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.user.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.EnteredApplicantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EnteredApplicantsCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String MESSAGE_ATTRIBUTE = "message";
    private final static String REGISTRATION_OPENED_ERROR_MESSAGE = "Registration of applications continues." +
            " You can not make this action";
    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";

    private final static int INDEX_FIRST_VALUE = 0;
    private final static String FACULTY_ID_PARAMETER = "facultyId";
    private final static String ENTERED_APPLICANTS_ATTRIBUTE = "enteredApplicants";
    private final static String PAGE_ENTERED_APPLICANTS = "WEB-INF/view/enteredApplicants.jsp";

    private final EnteredApplicantService enteredApplicantService;

    public EnteredApplicantsCommand(EnteredApplicantService enteredApplicantService) {
        this.enteredApplicantService = enteredApplicantService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        if (!enteredApplicantService.isRegistrationFinished()) {
            LOGGER.warn(REGISTRATION_OPENED_ERROR_MESSAGE);
            requestContext.setRequestAttribute(MESSAGE_ATTRIBUTE, REGISTRATION_OPENED_ERROR_MESSAGE);
            return CommandResult.forward(PAGE_ERROR);
        }
        String facultyId = requestContext.getRequestParameter(FACULTY_ID_PARAMETER)[INDEX_FIRST_VALUE];
        int facultyIdInt = Integer.parseInt(facultyId);
        List<UserDto> enteredApplicants =
                enteredApplicantService.findEnteredApplicantsByFaculty(facultyIdInt);
        requestContext.setRequestAttribute(ENTERED_APPLICANTS_ATTRIBUTE, enteredApplicants);
        return CommandResult.forward(PAGE_ENTERED_APPLICANTS);
    }

}