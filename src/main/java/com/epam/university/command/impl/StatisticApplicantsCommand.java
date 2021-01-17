package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.Counter;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.StatisticApplicantService;

import java.util.List;

public class StatisticApplicantsCommand implements Command {

    private final static int INDEX_FIRST_VALUE = 0;
    private final static String FACULTY_ID_PARAMETER = "facultyId";
    private final static String COUNTERS_ATTRIBUTE = "counters";
    private final static String PAGE_STATISTIC_APPLICANTS = "WEB-INF/view/statisticApplicants.jsp";

    private final StatisticApplicantService statisticApplicantService;

    public StatisticApplicantsCommand(StatisticApplicantService statisticApplicantService) {
        this.statisticApplicantService = statisticApplicantService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String facultyId = requestContext.getRequestParameter(FACULTY_ID_PARAMETER)[INDEX_FIRST_VALUE];
        int facultyIdInt = Integer.parseInt(facultyId);
        List<Counter> counters =
                statisticApplicantService.findFacultyStatisticApplicantsMark(facultyIdInt);
        requestContext.setRequestAttribute(COUNTERS_ATTRIBUTE, counters);
        return CommandResult.forward(PAGE_STATISTIC_APPLICANTS);
    }

}