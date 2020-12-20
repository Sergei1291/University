package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.service.ApplicationService;
import com.epam.university.service.ServiceException;

public class RegisterCommand implements Command {

    private final static String REGISTERED_APPLICATIONS_ATTRIBUTE = "registeredApplications";

    private final static String PAGE_REGISTER = "WEB-INF/view/register.jsp";

    private ApplicationService applicationService;

    public RegisterCommand(ApplicationService applicationService) {

        this.applicationService = applicationService;

    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        int registeredApplications = applicationService.registerApplications();

        requestContext.setRequestAttribute(REGISTERED_APPLICATIONS_ATTRIBUTE, registeredApplications);

        return CommandResult.forward(PAGE_REGISTER);
    }

}