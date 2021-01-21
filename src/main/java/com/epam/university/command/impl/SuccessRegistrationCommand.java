package com.epam.university.command.impl;

import com.epam.university.command.AbstractErrorCommand;
import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.RegistrationService;

public class SuccessRegistrationCommand extends AbstractErrorCommand implements Command {

    private final static String LISTS_ALREADY_FORMED_BUNDLE_ERROR_MESSAGE = "lists.already.formed";
    private final static String REGISTRATION_OPENED_BUNDLE_ERROR_MESSAGE = "registration.opened";

    private final static String REGISTERED_APPLICATIONS_ATTRIBUTE = "registeredApplications";
    private final static String PAGE_SUCCESS_REGISTRATION = "WEB-INF/view/successRegistration.jsp";

    private final RegistrationService registrationService;

    public SuccessRegistrationCommand(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        if (registrationService.isApplicantListReady()) {
            return forwardErrorPage(requestContext, LISTS_ALREADY_FORMED_BUNDLE_ERROR_MESSAGE);
        }
        if (!registrationService.isRegistrationFinished()) {
            return forwardErrorPage(requestContext, REGISTRATION_OPENED_BUNDLE_ERROR_MESSAGE);
        }
        int registeredApplications = registrationService.findNumRegisteredApplications();
        requestContext.setRequestAttribute(REGISTERED_APPLICATIONS_ATTRIBUTE, registeredApplications);
        return CommandResult.forward(PAGE_SUCCESS_REGISTRATION);
    }

}