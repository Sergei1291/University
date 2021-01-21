<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ex" uri="tag/custom.tld"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/static/js/pagination.js"></script>
    <title>
      <fmt:message key="label.applications.info.title" />
    </title>
  </head>

  <body>
    <jsp:include page="parts/header.jsp" />
    <div class="container">
      <div class="menu">
        <jsp:include page="parts/menu.jsp" />
      </div>

      <div class="context">
        <fmt:message key="label.enum.faculty.${param.facultyName}" />
        <fmt:message key="label.applications.info.context.faculty" />
        <br>
        <c:if test="${empty applicationsInfo}">
          <br>
          <fmt:message key="label.applications.info.context.no.applications" />
        </c:if>
        <c:if test="${!empty applicationsInfo}">
          <div class="table-applicants-info">
            <table>
              <tr class="th">
                <td>
                  <fmt:message key="label.applications.info.context.table.num" />
                </td>
                <td>
                  <fmt:message key="label.applications.info.context.table.surname" />
                </td>
                <td>
                  <fmt:message key="label.applications.info.context.table.name" />
                </td>
                <td>
                  <fmt:message key="label.applications.info.context.table.average.mark" />
                </td>
                <td>
                  <fmt:message key="label.applications.info.context.table.certificates" />
                </td>
                <td>
                  <fmt:message key="label.applications.info.context.table.application.status" />
                </td>
              </tr>
              <c:forEach var="fullApplication" items="${applicationsInfo}" varStatus="status">
                <tr class="row">
                  <td>
                    ${status.count}
                  </td>
                  <td>
                    ${fullApplication.userDto.surname}
                  </td>
                  <td>
                    ${fullApplication.userDto.name}
                  </td>
                  <td>
                    ${fullApplication.application.averageMark}
                  </td>
                  <td>
                    <c:forEach var="certificate" items="${fullApplication.certificates}">
                      <c:set var="subjectName" scope="page">
                        <ex:subject>${certificate.subject}</ex:subject>
                      </c:set>
                      <fmt:message key="label.enum.subject.${subjectName}" />
                      ${certificate.mark}
                      <br>
                    </c:forEach>
                  </td>
                  <td>
                    <fmt:message key="label.enum.application.status.${fullApplication.application.status}" />
                  </td>
                </tr>
              </c:forEach>
            </table>
          </div>
          <div class="pagination"></div>
          <br><br>
        </c:if>
        <form method="GET" action="${pageContext.request.contextPath}/controller">
          <input type="hidden" name="command" value="selectionFaculty" />
          <input type="hidden" name="targetPage" value="applications" />
          <div class="context-button">
            <button type="submit">
              <fmt:message key="label.applications.info.context.button.choice.faculty" />
            </button>
          </div>
        </form>
      </div>

    </div>
    <script>
      initialize('table-applicants-info', 4);
      display();
      displayPagination();
      eventHandle();
    </script>
  </body>

</html>