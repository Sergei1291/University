package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.CommitteeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CloseRegistrationCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String MESSAGE_ATTRIBUTE = "message";
    private final static String REGISTRATION_CLOSED_ERROR_MESSAGE = "Registration of applications is over." +
            " You can not make this action";
    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";

    private final static String COMMAND_REGISTRATION_SUCCESS =
            "/University/controller?command=registrationSuccess";

    private final CommitteeService committeeService;

    public CloseRegistrationCommand(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        if (committeeService.isRegistrationFinished()) {
            LOGGER.warn(REGISTRATION_CLOSED_ERROR_MESSAGE);
            requestContext.setRequestAttribute(MESSAGE_ATTRIBUTE, REGISTRATION_CLOSED_ERROR_MESSAGE);
            return CommandResult.forward(PAGE_ERROR);
        }
        committeeService.closeRegistration();
        return CommandResult.redirect(COMMAND_REGISTRATION_SUCCESS);
    }

}