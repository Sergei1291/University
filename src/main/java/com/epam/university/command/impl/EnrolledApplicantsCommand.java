package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.user.Enrollee;
import com.epam.university.service.ApplicationService;
import com.epam.university.service.ServiceException;

import java.util.List;

public class EnrolledApplicantsCommand implements Command {

    private final static int INDEX_FIRST_VALUE = 0;

    private final static String ID_FACULTY_PARAMETER = "facultyId";
    private final static String ENROLLED_APPLICANTS_ATTRIBUTE = "enrolledApplicants";

    private final static String PAGE_ENROLLED_APPLICANTS = "WEB-INF/view/enrolledApplicants.jsp";

    private ApplicationService applicationService;

    public EnrolledApplicantsCommand(ApplicationService applicationService) {

        this.applicationService = applicationService;

    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        String idFaculty = requestContext.getRequestParameter(ID_FACULTY_PARAMETER)[INDEX_FIRST_VALUE];
        int idFacultyInt = Integer.parseInt(idFaculty);

        List<Enrollee> enrolledApplicants =
                applicationService.findEnrolledApplicantsByFaculty(idFacultyInt);

        requestContext.setRequestAttribute(ENROLLED_APPLICANTS_ATTRIBUTE, enrolledApplicants);

        return CommandResult.forward(PAGE_ENROLLED_APPLICANTS);
    }

}