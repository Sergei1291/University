package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.user.UserDto;
import com.epam.university.service.ApplicationService;
import com.epam.university.service.ServiceException;
import com.epam.university.service.UserDtoService;

public class MainCommand implements Command {

    private final static String IS_REGISTRATION_FINISHED_ATTRIBUTE = "isRegistrationFinished";

    private final static String PAGE_MAIN = "WEB-INF/view/main.jsp";
    private final static String COMMAND_ACCOUNT = "/University/controller?command=account";

    private ApplicationService applicationService;

    public MainCommand(ApplicationService applicationService) {

        this.applicationService = applicationService;

    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        UserDto userDto = (UserDto) requestContext.getSessionAttribute("userDto");

        if (userDto != null) {

            return CommandResult.redirect(COMMAND_ACCOUNT);
        }

        addAttribute(requestContext);

        return CommandResult.forward(PAGE_MAIN);
    }

    private void addAttribute(RequestContext requestContext) throws ServiceException {

        boolean isRegistrationFinished = applicationService.isRegistrationFinished();

        requestContext.setRequestAttribute(IS_REGISTRATION_FINISHED_ATTRIBUTE, isRegistrationFinished);

    }

}