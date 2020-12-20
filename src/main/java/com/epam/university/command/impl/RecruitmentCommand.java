package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.Faculty;
import com.epam.university.service.FacultyService;
import com.epam.university.service.ServiceException;

import java.util.List;

public class RecruitmentCommand implements Command {

    private final static String FACULTIES_ATTRIBUTE = "faculties";

    private final static String PAGE_RECRUITMENT = "WEB-INF/view/recruitment.jsp";

    private FacultyService facultyService;

    public RecruitmentCommand(FacultyService facultyService) {

        this.facultyService = facultyService;

    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        List<Faculty> faculties = facultyService.findFaculties();

        requestContext.setRequestAttribute(FACULTIES_ATTRIBUTE, faculties);

        return CommandResult.forward(PAGE_RECRUITMENT);
    }

}