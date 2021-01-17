<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ex" uri="tag/custom.tld"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <title>
      <fmt:message key="label.personal.application.title" />
    </title>
  </head>

  <body>
    <jsp:include page="parts/header.jsp" />
    <div class="container">
      <div class="menu">
        <jsp:include page="parts/menu.jsp" />
      </div>

      <div class="context">
        <br>
        <c:if test="${sessionScope.userDto.faculty == 0}">
          <fmt:message key="label.personal.application.context.no.application" />
          <br>
          <c:if test="${!isRegistrationFinished}">
            <form method="GET" action="${pageContext.request.contextPath}/controller">
              <input type="hidden" name="command" value="selectionFaculty">
              <input type="hidden" name="targetPage" value="apply">
              <div class="context-button">
                <button type="submit">
                  <fmt:message key="label.personal.application.context.button.apply" />
                </button>
              </div>
            </form>
          </c:if>
        </c:if>
        <c:if test="${sessionScope.userDto.faculty != 0}">
          <fmt:message key="label.personal.application.context.application" />
          <table width="40%" align="center" border="2">
            <tr>
              <td>
                <fmt:message key="label.personal.application.context.table.surname" />
              </td>
              <td>
                ${sessionScope.userDto.surname}
              </td>
            </tr>
            <tr>
              <td>
                <fmt:message key="label.personal.application.context.table.name" />
              </td>
              <td>
                ${sessionScope.userDto.name}
              </td>
            </tr>
            <tr>
              <td>
                <fmt:message key="label.personal.application.context.table.faculty" />
              </td>
              <td>
                <c:set var="facultyName" scope="page">
                  <ex:faculty>${sessionScope.userDto.faculty}</ex:faculty>
                </c:set>
                <fmt:message key="label.enum.faculty.${facultyName}" />
              </td>
            </tr>
            <tr>
              <td>
                <fmt:message key="label.personal.application.context.table.average.mark" />
              </td>
              <td>
                ${sessionScope.userDto.averageMark}
              </td>
            </tr>
            <tr>
              <td>
                <fmt:message key="label.personal.application.context.table.application.status" />
              </td>
              <td>
                <fmt:message key="label.enum.application.status.${sessionScope.userDto.applicationStatus}" />
              </td>
            </tr>
          </table>
          <br>
          <fmt:message key="label.personal.application.context.certificates" />
          <table width="40%" align="center" border="2">
            <c:forEach var="certificate" items="${certificates}">
              <tr>
                <td>
                  <c:set var="subjectName" scope="page">
                    <ex:subject>${certificate.subject}</ex:subject>
                  </c:set>
                  <fmt:message key="label.enum.subject.${subjectName}" />
                </td>
                <td>
                  ${certificate.mark}
                </td>
              </tr>
            </c:forEach>
          </table>
          <c:if test="${!isRegistrationFinished}">
            <form method="POST" action="${pageContext.request.contextPath}/controller?command=cancelApplication">
              <div class="context-button">
                <button type="submit">
                  <fmt:message key="label.personal.application.context.button.cancel" />
                </button>
              </div>
            </form>
          </c:if>
        </c:if>
        <c:if test="${isRegistrationFinished}">
          <jsp:include page="parts/showApplicantsButton.jsp" />
        </c:if>

      </div>

    </div>
  </body>

</html>