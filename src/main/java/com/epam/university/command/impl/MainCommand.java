package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.RegistrationService;

public class MainCommand implements Command {

    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String IS_REGISTRATION_FINISHED_ATTRIBUTE = "isRegistrationFinished";
    private final static String IS_APPLICANT_LIST_READY_ATTRIBUTE = "isApplicantListReady";
    private final static String COMMAND_ACCOUNT = "/University/controller?command=account";
    private final static String PAGE_MAIN = "WEB-INF/view/main.jsp";

    private final RegistrationService registrationService;

    public MainCommand(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        UserDto userDto = (UserDto) requestContext.getSessionAttribute(USER_DTO_ATTRIBUTE);
        if (userDto != null) {
            return CommandResult.redirect(COMMAND_ACCOUNT);
        }
        boolean isRegistrationFinished = registrationService.isRegistrationFinished();
        requestContext.setRequestAttribute(IS_REGISTRATION_FINISHED_ATTRIBUTE, isRegistrationFinished);

        boolean isApplicantListReady = registrationService.isApplicantListReady();
        requestContext.setRequestAttribute(IS_APPLICANT_LIST_READY_ATTRIBUTE, isApplicantListReady);

        return CommandResult.forward(PAGE_MAIN);
    }

}