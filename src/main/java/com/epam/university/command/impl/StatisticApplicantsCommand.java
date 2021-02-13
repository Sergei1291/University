package com.epam.university.command.impl;

import com.epam.university.command.AbstractErrorCommand;
import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.Counter;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.StatisticApplicationService;
import com.epam.university.validator.Validator;

import java.util.List;

public class StatisticApplicantsCommand extends AbstractErrorCommand implements Command {

    private final static int INDEX_FIRST_VALUE = 0;
    private final static String PARAMETER_IS_NOT_VALID_ERROR_MESSAGE = "PARAMETER IS NOT VALID: ";
    private final static String FACULTY_ID_PARAMETER = "facultyId";
    private final static String COUNTERS_ATTRIBUTE = "counters";
    private final static String PAGE_STATISTIC_APPLICANTS = "WEB-INF/view/statisticApplicants.jsp";

    private final StatisticApplicationService statisticApplicationService;
    private final Validator<String> validator;

    public StatisticApplicantsCommand(StatisticApplicationService statisticApplicationService,
                                      Validator<String> validator) {
        this.statisticApplicationService = statisticApplicationService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String facultyId = requestContext.getRequestParameter(FACULTY_ID_PARAMETER)[INDEX_FIRST_VALUE];
        if (!validator.isValid(facultyId)) {
            return forwardErrorPage(requestContext,
                    PARAMETER_IS_NOT_VALID_ERROR_MESSAGE + facultyId, false);
        }
        int facultyIdInt = Integer.parseInt(facultyId);
        List<Counter> counters =
                statisticApplicationService.findFacultyStatisticApplications(facultyIdInt);
        requestContext.setRequestAttribute(COUNTERS_ATTRIBUTE, counters);
        return CommandResult.forward(PAGE_STATISTIC_APPLICANTS);
    }

}