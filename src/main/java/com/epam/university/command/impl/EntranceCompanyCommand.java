package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.FacultyDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.EntranceCompanyService;

import java.util.List;
import java.util.Map;

public class EntranceCompanyCommand implements Command {

    private final static String FACULTIES_DTO_ATTRIBUTE = "facultiesDto";
    private final static String NUMBERS_APPLICATIONS = "numbersApplications";
    private final static String PAGE_ENTRANCE_COMPANY = "WEB-INF/view/entranceCompany.jsp";

    private final EntranceCompanyService entranceCompanyService;

    public EntranceCompanyCommand(EntranceCompanyService entranceCompanyService) {
        this.entranceCompanyService = entranceCompanyService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        List<FacultyDto> facultiesDto = entranceCompanyService.findFacultiesDto();
        requestContext.setRequestAttribute(FACULTIES_DTO_ATTRIBUTE, facultiesDto);

        Map<Integer, Integer> numbersApplications =
                entranceCompanyService.findNumbersApplications();
        requestContext.setRequestAttribute(NUMBERS_APPLICATIONS, numbersApplications);

        return CommandResult.forward(PAGE_ENTRANCE_COMPANY);
    }

}