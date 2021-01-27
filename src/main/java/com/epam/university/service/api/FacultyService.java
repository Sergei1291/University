package com.epam.university.service.api;

import com.epam.university.model.identifiable.Faculty;
import com.epam.university.service.ServiceException;

import java.util.List;

public interface FacultyService {

    /**
     * This method is used to finding list of all faculties. Faculty
     * need to have faculty id and faculty name params.
     *
     * @return
     * @throws ServiceException
     */
    List<Faculty> findFaculties() throws ServiceException;

}