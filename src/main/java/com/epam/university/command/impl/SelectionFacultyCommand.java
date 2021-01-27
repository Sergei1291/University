package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.Faculty;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.FacultyService;

import java.util.List;

public class SelectionFacultyCommand implements Command {

    private final static String FACULTIES_ATTRIBUTE = "faculties";
    private final static String PAGE_SELECTION_FACULTY = "WEB-INF/view/selectionFaculty.jsp";

    private final FacultyService facultyDtoService;

    public SelectionFacultyCommand(FacultyService facultyDtoService) {
        this.facultyDtoService = facultyDtoService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        List<Faculty> faculties = facultyDtoService.findFaculties();
        requestContext.setRequestAttribute(FACULTIES_ATTRIBUTE, faculties);
        return CommandResult.forward(PAGE_SELECTION_FACULTY);
    }

}