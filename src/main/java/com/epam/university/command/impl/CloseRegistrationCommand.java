package com.epam.university.command.impl;

import com.epam.university.command.AbstractErrorCommand;
import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.CommitteeService;

public class CloseRegistrationCommand extends AbstractErrorCommand implements Command {

    private final static String REGISTRATION_CLOSED_BUNDLE_ERROR_MESSAGE = "registration.closed";
    private final static String COMMAND_SUCCESS_REGISTRATION =
            "/University/controller?command=successRegistration";

    private final CommitteeService committeeService;

    public CloseRegistrationCommand(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        if (committeeService.isRegistrationFinished()) {
            return forwardErrorPage(requestContext,
                    REGISTRATION_CLOSED_BUNDLE_ERROR_MESSAGE, true);
        }
        committeeService.closeRegistration();
        return CommandResult.redirect(COMMAND_SUCCESS_REGISTRATION);
    }

}