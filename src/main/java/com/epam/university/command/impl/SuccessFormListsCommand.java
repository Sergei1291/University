package com.epam.university.command.impl;

import com.epam.university.command.AbstractErrorCommand;
import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.RegistrationService;

public class SuccessFormListsCommand extends AbstractErrorCommand implements Command {

    private final static String LISTS_NOT_FORMED_BUNDLE_ERROR_MESSAGE = "lists.not.formed";

    private final static String PAGE_SUCCESS_FORM_LISTS = "WEB-INF/view/successFormLists.jsp";

    private final RegistrationService registrationService;

    public SuccessFormListsCommand(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        if (!registrationService.isApplicantListReady()) {
            return forwardErrorPage(requestContext, LISTS_NOT_FORMED_BUNDLE_ERROR_MESSAGE, true);
        }
        return CommandResult.forward(PAGE_SUCCESS_FORM_LISTS);
    }

}