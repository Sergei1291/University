package com.epam.university.command.impl;

import com.epam.university.command.AbstractErrorCommand;
import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.CancelApplicationService;

public class CancelApplicationCommand extends AbstractErrorCommand implements Command {

    private final static String REGISTRATION_CLOSED_BUNDLE_ERROR_MESSAGE = "registration.closed";
    private final static String APPLICATION_NOT_FOUND_BUNDLE_ERROR_MESSAGE = "application.not.found";
    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String COMMAND_PERSONAL_APPLICATION =
            "/University/controller?command=personalApplication";

    private final CancelApplicationService cancelApplicationService;

    public CancelApplicationCommand(CancelApplicationService cancelApplicationService) {
        this.cancelApplicationService = cancelApplicationService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        if (cancelApplicationService.isRegistrationFinished()) {
            return forwardErrorPage(requestContext,
                    REGISTRATION_CLOSED_BUNDLE_ERROR_MESSAGE, true);
        }
        UserDto userDto = (UserDto) requestContext.getSessionAttribute(USER_DTO_ATTRIBUTE);
        boolean isApplicationCancelled = cancelApplicationService.cancelApplication(userDto);
        if (!isApplicationCancelled) {
            return forwardErrorPage(requestContext,
                    APPLICATION_NOT_FOUND_BUNDLE_ERROR_MESSAGE, true);
        }
        return CommandResult.redirect(COMMAND_PERSONAL_APPLICATION);
    }

}