package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.Subject;
import com.epam.university.service.FacultyService;
import com.epam.university.service.ServiceException;

import java.util.List;

public class ApplicationCommand implements Command {

    private final static int INDEX_FIRST_VALUE = 0;

    private final static String ID_FACULTY_PARAMETER = "facultyId";
    private final static String ID_FACULTY_ATTRIBUTE = "facultyId";
    private final static String SUBJECTS_ATTRIBUTE = "subjects";

    private final static String PAGE_APPLICATION = "WEB-INF/view/application.jsp";

    private final FacultyService facultyService;

    public ApplicationCommand(FacultyService facultyService) {

        this.facultyService = facultyService;

    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        String idFaculty = requestContext.getRequestParameter(ID_FACULTY_PARAMETER)[INDEX_FIRST_VALUE];
        int idFacultyInt = Integer.parseInt(idFaculty);
        requestContext.setRequestAttribute(ID_FACULTY_ATTRIBUTE, idFacultyInt);

        List<Subject> subjects = facultyService.findSubjects(idFacultyInt);
        requestContext.setRequestAttribute(SUBJECTS_ATTRIBUTE, subjects);

        return CommandResult.forward(PAGE_APPLICATION);
    }

}