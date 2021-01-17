package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.user.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.EnrolleeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CancelApplicationCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String MESSAGE_ATTRIBUTE = "message";
    private final static String REGISTRATION_CLOSED_ERROR_MESSAGE = "Registration of applications is over." +
            " You can not make this action";
    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";

    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String COMMAND_ACCOUNT = "/University/controller?command=account";

    private final EnrolleeService enrolleeService;

    public CancelApplicationCommand(EnrolleeService enrolleeService) {
        this.enrolleeService = enrolleeService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        if (enrolleeService.isRegistrationFinished()) {
            LOGGER.warn(REGISTRATION_CLOSED_ERROR_MESSAGE);
            requestContext.setRequestAttribute(MESSAGE_ATTRIBUTE, REGISTRATION_CLOSED_ERROR_MESSAGE);
            return CommandResult.forward(PAGE_ERROR);
        }
        UserDto userDto = (UserDto) requestContext.getSessionAttribute(USER_DTO_ATTRIBUTE);
        UserDto userDtoCancelled = enrolleeService.cancelApplication(userDto);
        requestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, userDtoCancelled);
        return CommandResult.redirect(COMMAND_ACCOUNT);
    }

}