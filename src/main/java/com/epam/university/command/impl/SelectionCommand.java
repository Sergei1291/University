package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.FacultyDto;
import com.epam.university.service.FacultyService;
import com.epam.university.service.ServiceException;

import java.util.List;

public class SelectionCommand implements Command {

    private final static String FACULTIES_DTO_ATTRIBUTE = "facultiesDto";

    private final static String PAGE_SELECTION = "WEB-INF/view/selection.jsp";

    private FacultyService facultyService;

    public SelectionCommand(FacultyService facultyService) {

        this.facultyService = facultyService;

    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        List<FacultyDto> facultiesDto = facultyService.findFacultiesDto();

        requestContext.setRequestAttribute(FACULTIES_DTO_ATTRIBUTE, facultiesDto);

        return CommandResult.forward(PAGE_SELECTION);
    }

}