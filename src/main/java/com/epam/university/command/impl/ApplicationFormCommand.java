package com.epam.university.command.impl;

import com.epam.university.command.AbstractErrorCommand;
import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.Subject;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.FacultySubjectService;
import com.epam.university.validator.Validator;

import java.util.List;

public class ApplicationFormCommand extends AbstractErrorCommand implements Command {

    private final static int INDEX_FIRST_VALUE = 0;
    private final static String PARAMETER_IS_NOT_VALID_ERROR_MESSAGE = "PARAMETER IS NOT VALID: ";
    private final static String FACULTY_ID_PARAMETER = "facultyId";
    private final static String SUBJECTS_ATTRIBUTE = "subjects";
    private final static String PAGE_APPLICATION_FORM = "WEB-INF/view/applicationForm.jsp";

    private final FacultySubjectService facultySubjectService;
    private final Validator<String> validator;

    public ApplicationFormCommand(FacultySubjectService facultySubjectService,
                                  Validator<String> validator) {
        this.facultySubjectService = facultySubjectService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String facultyId = requestContext.getRequestParameter(FACULTY_ID_PARAMETER)[INDEX_FIRST_VALUE];
        if (!validator.isValid(facultyId)) {
            return forwardErrorPage(requestContext,
                    PARAMETER_IS_NOT_VALID_ERROR_MESSAGE + facultyId, false);
        }
        int facultyIdInt = Integer.parseInt(facultyId);
        List<Subject> subjects = facultySubjectService.findFacultySubjects(facultyIdInt);
        if (subjects.isEmpty()) {
            return forwardErrorPage(requestContext,
                    PARAMETER_IS_NOT_VALID_ERROR_MESSAGE + facultyIdInt, false);
        }
        requestContext.setRequestAttribute(SUBJECTS_ATTRIBUTE, subjects);
        return CommandResult.forward(PAGE_APPLICATION_FORM);
    }

}