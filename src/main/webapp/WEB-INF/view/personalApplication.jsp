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
        <c:if test="${!optionalFullApplication.isPresent()}">
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
        <c:if test="${optionalFullApplication.isPresent()}">
          <fmt:message key="label.personal.application.context.application" />
          <table width="40%" align="center" border="2">
            <tr>
              <td width="60%">
                <fmt:message key="label.personal.application.context.table.surname" />
              </td>
              <td>
                ${optionalFullApplication.get().userDto.surname}
              </td>
            </tr>
            <tr>
              <td>
                <fmt:message key="label.personal.application.context.table.name" />
              </td>
              <td>
                ${optionalFullApplication.get().userDto.name}
              </td>
            </tr>
            <tr>
              <td>
                <fmt:message key="label.personal.application.context.table.faculty" />
              </td>
              <td>
                <c:set var="facultyName" scope="page">
                  <ex:faculty>${optionalFullApplication.get().application.faculty}</ex:faculty>
                </c:set>
                <fmt:message key="label.enum.faculty.${facultyName}" />
              </td>
            </tr>
            <tr>
              <td>
                <fmt:message key="label.personal.application.context.table.average.mark" />
              </td>
              <td>
                ${optionalFullApplication.get().application.averageMark}
              </td>
            </tr>
            <tr>
              <td>
                <fmt:message key="label.personal.application.context.table.application.status" />
              </td>
              <td>
                <fmt:message key="label.enum.application.status.${optionalFullApplication.get().application.status}" />
              </td>
            </tr>
          </table>
          <br>
          <fmt:message key="label.personal.application.context.certificates" />
          <table width="40%" align="center" border="2">
            <c:forEach var="certificate" items="${optionalFullApplication.get().certificates}">
              <tr>
                <td width="60%">
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
            <br>
            <form method="POST" action="${pageContext.request.contextPath}/controller?command=cancelApplication">
              <div class="context-button">
                <c:set var="messageConfirm" scope="page">
                  <fmt:message key="label.personal.application.context.message.confirm" />
                </c:set>
                <button type="submit" onclick="confirm('${messageConfirm}')">
                  <fmt:message key="label.personal.application.context.button.cancel" />
                </button>
              </div>
            </form>
          </c:if>
        </c:if>
        <c:if test="${isRegistrationFinished}">
          <jsp:include page="parts/registrationFinished.jsp" />
        </c:if>
      </div>

    </div>
  </body>

</html>