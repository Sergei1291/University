package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.Certificate;
import com.epam.university.model.identifiable.user.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.FacultyApplicantService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApplicationsInfoCommand implements Command {

    private final static int INDEX_FIRST_VALUE = 0;
    private final static String FACULTY_ID_PARAMETER = "facultyId";
    private final static String APPLICANTS_INFO_ATTRIBUTE = "applicantsInfo";
    private final static String APPLICANTS_ATTRIBUTE = "applicants";
    private final static String PAGE_APPLICATIONS_INFO = "WEB-INF/view/applicationsInfo.jsp";

    private FacultyApplicantService facultyApplicantService;

    public ApplicationsInfoCommand(FacultyApplicantService facultyApplicantService) {
        this.facultyApplicantService = facultyApplicantService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String facultyId = requestContext.getRequestParameter(FACULTY_ID_PARAMETER)[INDEX_FIRST_VALUE];
        int facultyIdInt = Integer.parseInt(facultyId);
        Map<UserDto, List<Certificate>> applicantsInfo =
                facultyApplicantService.findFacultyApplicantsInfo(facultyIdInt);
        requestContext.setRequestAttribute(APPLICANTS_INFO_ATTRIBUTE, applicantsInfo);

        Set<UserDto> applicants = applicantsInfo.keySet();
        requestContext.setRequestAttribute(APPLICANTS_ATTRIBUTE, applicants);

        return CommandResult.forward(PAGE_APPLICATIONS_INFO);
    }

}