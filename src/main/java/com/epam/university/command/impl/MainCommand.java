package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.RegistrationService;

public class MainCommand implements Command {

    private final static String IS_REGISTRATION_FINISHED_ATTRIBUTE = "isRegistrationFinished";
    private final static String PAGE_MAIN = "WEB-INF/view/main.jsp";

    private final RegistrationService registrationService;

    public MainCommand(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        boolean isRegistrationFinished = registrationService.isRegistrationFinished();
        requestContext.setRequestAttribute(IS_REGISTRATION_FINISHED_ATTRIBUTE, isRegistrationFinished);
        return CommandResult.forward(PAGE_MAIN);
    }

}