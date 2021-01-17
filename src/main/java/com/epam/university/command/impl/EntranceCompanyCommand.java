package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.Faculty;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.EntranceCompanyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntranceCompanyCommand implements Command {

    private final static String FACULTIES_ATTRIBUTE = "faculties";
    private final static String NUMBERS_APPLICATIONS = "numbersApplications";
    private final static String PAGE_ENTRANCE_COMPANY = "WEB-INF/view/entranceCompany.jsp";

    private final EntranceCompanyService entranceCompanyService;

    public EntranceCompanyCommand(EntranceCompanyService entranceCompanyService) {
        this.entranceCompanyService = entranceCompanyService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        List<Faculty> faculties = entranceCompanyService.findFaculties();
        requestContext.setRequestAttribute(FACULTIES_ATTRIBUTE, faculties);

        Map<Integer, Integer> numbersApplications = createNumbersApplications(faculties);
        requestContext.setRequestAttribute(NUMBERS_APPLICATIONS, numbersApplications);

        return CommandResult.forward(PAGE_ENTRANCE_COMPANY);
    }

    private Map<Integer, Integer> createNumbersApplications(List<Faculty> faculties) throws ServiceException {
        Map<Integer, Integer> numbersApplications = new HashMap<>();
        for (Faculty faculty : faculties) {
            int facultyId = faculty.getId();
            int numberApplications = entranceCompanyService.findNumberApplicantsByFaculty(facultyId);
            numbersApplications.put(facultyId, numberApplications);
        }
        return numbersApplications;
    }

}