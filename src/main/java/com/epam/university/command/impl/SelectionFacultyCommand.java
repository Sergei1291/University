package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.FacultyDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.FacultyDtoService;

import java.util.List;

public class SelectionFacultyCommand implements Command {

    private final static String FACULTIES_DTO_ATTRIBUTE = "facultiesDto";
    private final static String PAGE_SELECTION_FACULTY = "WEB-INF/view/selectionFaculty.jsp";

    private final FacultyDtoService facultyDtoService;

    public SelectionFacultyCommand(FacultyDtoService facultyDtoService) {
        this.facultyDtoService = facultyDtoService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        List<FacultyDto> facultiesDto = facultyDtoService.findFacultiesDto();
        requestContext.setRequestAttribute(FACULTIES_DTO_ATTRIBUTE, facultiesDto);
        return CommandResult.forward(PAGE_SELECTION_FACULTY);
    }

}