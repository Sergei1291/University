<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <title>
      <fmt:message key="label.entrance.company.title" />
    </title>
  </head>

  <body>
    <jsp:include page="parts/header.jsp" />
    <div class="container">
      <div class="menu">
        <jsp:include page="parts/menu.jsp" />
      </div>

      <div class="context">
        <div class="table-entrance-company">
          <table>
            <tr>
              <td>
                <fmt:message key="label.entrance.company.context.table.num" />
              </td>
              <td>
                <fmt:message key="label.entrance.company.context.table.faculty" />
              </td>
              <td>
                <fmt:message key="label.entrance.company.context.table.subjects" />
              </td>
              <td>
                <fmt:message key="label.entrance.company.context.table.recruitment" />
              </td>
              <td width="15%">
                <fmt:message key="label.entrance.company.context.table.applications.submitted" />
              </td>
            </tr>
            <c:forEach var="facultyDto" items="${facultiesDto}" varStatus="status">
              <tr>
                <td>
                  ${status.count}
                </td>
                <td>
                  <fmt:message key="label.enum.faculty.${facultyDto.name}" />
                </td>
                <td>
                  <c:forEach var="subject" items="${facultyDto.subjects}">
                    <fmt:message key="label.enum.subject.${subject.name}" />
                    <br>
                  </c:forEach>
                </td>
                <td>
                  ${facultyDto.recruitment}
                </td>
                <td>
                  ${numbersApplications.get(facultyDto.id)}
                  <br>
                  <form method="GET" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="statisticApplicants" />
                    <input type="hidden" name="facultyId" value="${facultyDto.id}" />
                    <button type="submit">
                      <fmt:message key="label.entrance.company.context.table.button.details" />
                    </button>
                  </form>
                </td>
              </tr>
            </c:forEach>
          </table>
        </div>
      </div>

    </div>
  </body>

</html>