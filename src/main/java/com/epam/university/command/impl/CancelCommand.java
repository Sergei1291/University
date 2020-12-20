package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.UserDto;
import com.epam.university.service.ApplicationService;
import com.epam.university.service.ServiceException;

public class CancelCommand implements Command {

    private final static String USER_DTO_ATTRIBUTE = "userDto";

    private final static String COMMAND_MAIN = "/University/controller?command=main";

    private ApplicationService applicationService;

    public CancelCommand(ApplicationService applicationService) {

        this.applicationService = applicationService;

    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        UserDto userDto = (UserDto) requestContext.getSessionAttribute(USER_DTO_ATTRIBUTE);
        int userId = userDto.getId();

        UserDto userDtoCorrect = applicationService.cancel(userId);

        requestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, userDtoCorrect);

        return CommandResult.redirect(COMMAND_MAIN);
    }

}