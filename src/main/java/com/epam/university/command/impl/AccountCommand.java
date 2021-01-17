package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.user.Role;
import com.epam.university.model.identifiable.user.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.RegistrationService;

public class AccountCommand implements Command {

    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String IS_REGISTRATION_FINISHED_ATTRIBUTE = "isRegistrationFinished";
    private final static String PAGE_ACCOUNT_ENROLLEE = "WEB-INF/view/accountEnrollee.jsp";
    private final static String PAGE_ACCOUNT_COMMITTEE = "WEB-INF/view/accountCommittee.jsp";

    private final RegistrationService registrationService;

    public AccountCommand(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        boolean isRegistrationFinished = registrationService.isRegistrationFinished();
        requestContext.setRequestAttribute(IS_REGISTRATION_FINISHED_ATTRIBUTE, isRegistrationFinished);

        return forwardPageByUserRole(requestContext);
    }

    private CommandResult forwardPageByUserRole(RequestContext requestContext) {
        UserDto userDto = (UserDto) requestContext.getSessionAttribute(USER_DTO_ATTRIBUTE);
        Role role = userDto.getRole();
        switch (role) {
            case ENROLLEE:
                return CommandResult.forward(PAGE_ACCOUNT_ENROLLEE);
            case COMMITTEE:
                return CommandResult.forward(PAGE_ACCOUNT_COMMITTEE);
            default:
                throw new IllegalArgumentException("Unknown user role " + role.name());
        }
    }

}