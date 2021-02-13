package com.epam.university.command.impl;

import com.epam.university.command.AbstractErrorCommand;
import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.EnteredUserService;
import com.epam.university.validator.Validator;

import java.util.List;

public class EnteredApplicantsCommand extends AbstractErrorCommand implements Command {

    private final static int INDEX_FIRST_VALUE = 0;
    private final static String LISTS_NOT_FORMED_BUNDLE_ERROR_MESSAGE = "lists.not.formed";
    private final static String PARAMETER_IS_NOT_VALID_ERROR_MESSAGE = "PARAMETER IS NOT VALID: ";
    private final static String FACULTY_ID_PARAMETER = "facultyId";
    private final static String ENTERED_APPLICANTS_ATTRIBUTE = "enteredApplicants";
    private final static String PAGE_ENTERED_APPLICANTS = "WEB-INF/view/enteredApplicants.jsp";

    private final EnteredUserService enteredUserService;
    private final Validator<String> validator;

    public EnteredApplicantsCommand(EnteredUserService enteredUserService,
                                    Validator<String> validator) {
        this.enteredUserService = enteredUserService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        if (!enteredUserService.isApplicantListReady()) {
            return forwardErrorPage(requestContext,
                    LISTS_NOT_FORMED_BUNDLE_ERROR_MESSAGE, true);
        }
        String facultyId = requestContext.getRequestParameter(FACULTY_ID_PARAMETER)[INDEX_FIRST_VALUE];
        if (!validator.isValid(facultyId)) {
            return forwardErrorPage(requestContext,
                    PARAMETER_IS_NOT_VALID_ERROR_MESSAGE + facultyId, false);
        }
        int facultyIdInt = Integer.parseInt(facultyId);
        List<UserDto> enteredApplicants =
                enteredUserService.findEnteredUsersByFaculty(facultyIdInt);
        requestContext.setRequestAttribute(ENTERED_APPLICANTS_ATTRIBUTE, enteredApplicants);
        return CommandResult.forward(PAGE_ENTERED_APPLICANTS);
    }

}