package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.Subject;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.FacultyService;

import java.util.List;

public class ApplicationFormCommand implements Command {

    private final static int INDEX_FIRST_VALUE = 0;
    private final static String FACULTY_ID_PARAMETER = "facultyId";
    private final static String SUBJECTS_ATTRIBUTE = "subjects";
    private final static String PAGE_APPLICATION_FORM = "WEB-INF/view/applicationForm.jsp";

    private final FacultyService facultyService;

    public ApplicationFormCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String facultyId = requestContext.getRequestParameter(FACULTY_ID_PARAMETER)[INDEX_FIRST_VALUE];
        int facultyIdInt = Integer.parseInt(facultyId);
        List<Subject> subjects = facultyService.findFacultySubjects(facultyIdInt);
        requestContext.setRequestAttribute(SUBJECTS_ATTRIBUTE, subjects);

        return CommandResult.forward(PAGE_APPLICATION_FORM);
    }

}