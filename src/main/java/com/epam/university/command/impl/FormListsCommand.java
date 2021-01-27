package com.epam.university.command.impl;

import com.epam.university.command.AbstractErrorCommand;
import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.CommitteeService;

public class FormListsCommand extends AbstractErrorCommand implements Command {

    private final static String LISTS_ALREADY_FORMED_BUNDLE_ERROR_MESSAGE = "lists.already.formed";
    private final static String REGISTRATION_OPENED_BUNDLE_ERROR_MESSAGE = "registration.opened";

    private final static String COMMAND_SUCCESS_FORM_LISTS =
            "/University/controller?command=successFormLists";

    private final CommitteeService committeeService;

    public FormListsCommand(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        if (committeeService.isApplicantListReady()) {
            return forwardErrorPage(requestContext,
                    LISTS_ALREADY_FORMED_BUNDLE_ERROR_MESSAGE, true);
        }
        if (!committeeService.isRegistrationFinished()) {
            return forwardErrorPage(requestContext,
                    REGISTRATION_OPENED_BUNDLE_ERROR_MESSAGE, true);
        }
        committeeService.formListApplicants();
        return CommandResult.redirect(COMMAND_SUCCESS_FORM_LISTS);
    }

}