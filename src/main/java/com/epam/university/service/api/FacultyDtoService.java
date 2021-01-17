package com.epam.university.service.api;

import com.epam.university.model.identifiable.FacultyDto;
import com.epam.university.service.ServiceException;

import java.util.List;

public interface FacultyDtoService {

    List<FacultyDto> findFacultiesDto() throws ServiceException;

}