package com.epam.university.service.api;

import com.epam.university.model.FacultyDto;
import com.epam.university.service.ServiceException;

import java.util.List;

public interface FacultyDtoService extends FacultyService {

    /**
     * This method is used to finding list of all faculties dto.
     *
     * @return
     * @throws ServiceException
     */
    List<FacultyDto> findFacultiesDto() throws ServiceException;

}