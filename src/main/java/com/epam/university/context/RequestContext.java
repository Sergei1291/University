package com.epam.university.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RequestContext {

    private Map<String, Object> requestAttributes = new HashMap<>();
    private Map<String, String[]> requestParameters = new HashMap<>();
    private Map<String, Object> sessionAttributes = new HashMap<>();

    public RequestContext() {
    }

    //package private for RequestContextHelper
    Map<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public Object getRequestAttribute(String name) {
        return requestAttributes.get(name);
    }

    public void setRequestAttribute(String name, Object value) {
        requestAttributes.put(name, value);
    }

    //package private for RequestContextHelper
    Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public String[] getRequestParameter(String name) {
        return requestParameters.get(name);
    }

    public void setRequestParameter(String name, String[] params) {
        requestParameters.put(name, params);
    }

    //package private for RequestContextHelper
    Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public Object getSessionAttribute(String name) {
        return sessionAttributes.get(name);
    }

    public void setSessionAttribute(String name, Object value) {
        sessionAttributes.put(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestContext that = (RequestContext) o;
        return Objects.equals(requestAttributes, that.requestAttributes) &&
                Objects.equals(requestParameters, that.requestParameters) &&
                Objects.equals(sessionAttributes, that.sessionAttributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestAttributes, requestParameters, sessionAttributes);
    }

}