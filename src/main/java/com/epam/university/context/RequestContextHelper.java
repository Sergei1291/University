package com.epam.university.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

public class RequestContextHelper {

    public RequestContext create(HttpServletRequest request) {
        RequestContext requestContext = new RequestContext();
        initializeRequestAttributes(request, requestContext);
        initializeRequestParameters(request, requestContext);
        initializeSessionAttributes(request, requestContext);
        return requestContext;
    }

    public void updateRequest(HttpServletRequest request, RequestContext requestContext) {
        updateRequestAttributes(request, requestContext);
        updateSessionAttributes(request, requestContext);
    }

    private void initializeRequestAttributes(HttpServletRequest request, RequestContext requestContext) {
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attribute = request.getAttribute(attributeName);
            requestContext.setRequestAttribute(attributeName, attribute);
        }
    }

    private void initializeRequestParameters(HttpServletRequest request, RequestContext requestContext) {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String[] parameterValues = request.getParameterValues(parameterName);
            requestContext.setRequestParameter(parameterName, parameterValues);
        }
    }

    private void initializeSessionAttributes(HttpServletRequest request, RequestContext requestContext) {
        HttpSession session = request.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attribute = session.getAttribute(attributeName);
            requestContext.setSessionAttribute(attributeName, attribute);
        }
    }

    private void updateRequestAttributes(HttpServletRequest request, RequestContext requestContext) {
        Map<String, Object> requestAttributes = requestContext.getRequestAttributes();
        for (Map.Entry<String, Object> attribute : requestAttributes.entrySet()) {
            String attributeName = attribute.getKey();
            Object attributeValue = attribute.getValue();
            request.setAttribute(attributeName, attributeValue);
        }
    }

    private void updateSessionAttributes(HttpServletRequest request, RequestContext requestContext) {
        Map<String, Object> sessionAttributes = requestContext.getSessionAttributes();
        HttpSession session = request.getSession();
        if (sessionAttributes.isEmpty()) {
            session.invalidate();
            return;
        }
        for (Map.Entry<String, Object> attribute : sessionAttributes.entrySet()) {
            String attributeName = attribute.getKey();
            Object attributeValue = attribute.getValue();
            session.setAttribute(attributeName, attributeValue);
        }
    }

}