package com.epam.university.command.impl;

import com.epam.university.command.AbstractErrorCommand;
import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.model.identifiable.UserRole;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.RegistrationService;
import com.epam.university.validator.Validator;

public class AccountCommand extends AbstractErrorCommand implements Command {

    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String PARAMETER_IS_NOT_VALID_ERROR_MESSAGE = "PARAMETER IS NOT VALID";
    private final static String IS_REGISTRATION_FINISHED_ATTRIBUTE = "isRegistrationFinished";
    private final static String IS_APPLICANT_LIST_READY_ATTRIBUTE = "isApplicantListReady";
    private final static String PAGE_ACCOUNT_ENROLLEE = "WEB-INF/view/accountEnrollee.jsp";
    private final static String PAGE_ACCOUNT_COMMITTEE = "WEB-INF/view/accountCommittee.jsp";

    private final RegistrationService registrationService;
    private final Validator<UserDto> validator;

    public AccountCommand(RegistrationService registrationService, Validator<UserDto> validator) {
        this.registrationService = registrationService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        UserDto userDto = (UserDto) requestContext.getSessionAttribute(USER_DTO_ATTRIBUTE);
        if (!validator.isValid(userDto)) {
            return forwardErrorPage(requestContext,
                    PARAMETER_IS_NOT_VALID_ERROR_MESSAGE, false);
        }

        boolean isRegistrationFinished = registrationService.isRegistrationFinished();
        requestContext.setRequestAttribute(IS_REGISTRATION_FINISHED_ATTRIBUTE, isRegistrationFinished);
        boolean isApplicantListReady = registrationService.isApplicantListReady();
        requestContext.setRequestAttribute(IS_APPLICANT_LIST_READY_ATTRIBUTE, isApplicantListReady);

        return forwardPageByUserRole(userDto);
    }

    private CommandResult forwardPageByUserRole(UserDto userDto) {
        UserRole role = userDto.getRole();
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