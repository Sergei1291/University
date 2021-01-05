package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.user.UserDto;
import com.epam.university.service.ApplicationService;
import com.epam.university.service.ServiceException;

public class CancelApplicationCommand implements Command {

    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String MESSAGE_ATTRIBUTE = "message";
    private final static String MESSAGE_ATTRIBUTE_VALUE = "registration of applications is over." +
            " You can not make this action";

    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";
    private final static String COMMAND_ACCOUNT = "/University/controller?command=account";

    private ApplicationService applicationService;

    public CancelApplicationCommand(ApplicationService applicationService) {

        this.applicationService = applicationService;

    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        if (applicationService.isRegistrationFinished()) {

            requestContext.setRequestAttribute(MESSAGE_ATTRIBUTE, MESSAGE_ATTRIBUTE_VALUE);
            return CommandResult.forward(PAGE_ERROR);
        }

        UserDto userDto = (UserDto) requestContext.getSessionAttribute(USER_DTO_ATTRIBUTE);
        int userId = userDto.getId();

        UserDto userDtoCorrected = applicationService.cancelApplication(userId);

        requestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, userDtoCorrected);

        return CommandResult.redirect(COMMAND_ACCOUNT);
    }

}