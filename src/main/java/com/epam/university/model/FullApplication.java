package com.epam.university.model;

import com.epam.university.model.identifiable.Application;
import com.epam.university.model.identifiable.Certificate;
import com.epam.university.model.identifiable.UserDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class FullApplication  implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Application application;
    private final UserDto userDto;
    private final List<Certificate> certificates;

    public FullApplication(Application application, UserDto userDto, List<Certificate> certificates) {
        this.application = application;
        this.userDto = userDto;
        this.certificates = certificates;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Application getApplication() {
        return application;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public List<Certificate> getCertificates() {
        return new ArrayList<>(certificates);
    }

}