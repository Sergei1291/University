package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.RegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrationSuccessCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String MESSAGE_ATTRIBUTE = "message";
    private final static String REGISTRATION_OPENED_ERROR_MESSAGE = "Registration of applications continues." +
            " You can not make this action";
    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";

    private final static String PROCESSED_APPLICATIONS_ATTRIBUTE = "processedApplications";
    private final static String PAGE_REGISTRATION_SUCCESS = "WEB-INF/view/registrationSuccess.jsp";

    private final RegistrationService registrationService;

    public RegistrationSuccessCommand(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        if (!registrationService.isRegistrationFinished()) {
            LOGGER.warn(REGISTRATION_OPENED_ERROR_MESSAGE);
            requestContext.setRequestAttribute(MESSAGE_ATTRIBUTE, REGISTRATION_OPENED_ERROR_MESSAGE);
            return CommandResult.forward(PAGE_ERROR);
        }
        int processedApplications = registrationService.findNumProcessedApplications();
        requestContext.setRequestAttribute(PROCESSED_APPLICATIONS_ATTRIBUTE, processedApplications);
        return CommandResult.forward(PAGE_REGISTRATION_SUCCESS);
    }

}