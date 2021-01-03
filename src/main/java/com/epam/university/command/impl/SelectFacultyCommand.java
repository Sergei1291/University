package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.FacultyDto;
import com.epam.university.service.FacultyService;
import com.epam.university.service.ServiceException;

import java.util.List;

public class SelectFacultyCommand implements Command {

    private final static int INDEX_FIRST_VALUE = 0;

    private final static String FACULTIES_DTO_ATTRIBUTE = "facultiesDto";
    private final static String TARGET_PAGE_PARAMETER = "targetPage";

    private final static String PAGE_SELECT_APPLY = "WEB-INF/view/selectApply.jsp";
    private final static String PAGE_SELECT_VIEW = "WEB-INF/view/selectView.jsp";

    private FacultyService facultyService;

    public SelectFacultyCommand(FacultyService facultyService) {

        this.facultyService = facultyService;

    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        List<FacultyDto> facultiesDto = facultyService.findFacultiesDto();

        requestContext.setRequestAttribute(FACULTIES_DTO_ATTRIBUTE, facultiesDto);

        String targetPage = requestContext.getRequestParameter(TARGET_PAGE_PARAMETER)[INDEX_FIRST_VALUE];

        switch (targetPage) {
            case "apply":
                return CommandResult.forward(PAGE_SELECT_APPLY);
            case "view":
                return CommandResult.forward(PAGE_SELECT_VIEW);
            default:
                throw new IllegalArgumentException("Unknown page " + targetPage);
        }

    }

}