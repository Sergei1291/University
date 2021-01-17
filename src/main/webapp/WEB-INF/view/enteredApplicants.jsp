<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/static/js/pagination.js"></script>
    <title>
      <fmt:message key="label.entered.applicants.title" />
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
        <fmt:message key="label.enum.faculty.${param.facultyName}" />
        <fmt:message key="label.entered.applicants.context.faculty" />
        <br>
        <c:if test="${empty enteredApplicants}">
          <br>
          <fmt:message key="label.entered.applicants.context.no.applicants" />
        </c:if>
        <c:if test="${!empty enteredApplicants}">
          <div class="table-entered-applicants">
            <table>
              <tr class="th">
                <td>
                  <fmt:message key="label.entered.applicants.context.table.num" />
                </td>
                <td>
                  <fmt:message key="label.entered.applicants.context.table.surname" />
                </td>
                <td>
                  <fmt:message key="label.entered.applicants.context.table.name" />
                </td>
              </tr>
              <c:forEach var="applicant" items="${enteredApplicants}" varStatus="status">
                <tr class="row">
                  <td>
                    <c:out value="${status.count}" />
                  </td>
                  <td>
                    <c:out value="${applicant.surname}" />
                  </td>
                  <td>
                    <c:out value="${applicant.name}" />
                  </td>
                </tr>
              </c:forEach>
            </table>
          </div>

          <div class="pagination"></div>
          <br><br>
          <script>
            initialize('table-entered-applicants', 5);
            display();
            displayPagination();
            eventHandle();
          </script>
        </c:if>

        <form method="GET" action="${pageContext.request.contextPath}/controller">
          <input type="hidden" name="command" value="selectionFaculty" />
          <input type="hidden" name="targetPage" value="view" />
          <div class="context-button">
            <button type="submit">
              <fmt:message key="label.entered.applicants.context.button.choice.faculty" />
            </button>
          </div>
        </form>

      </div>

    </div>
  </body>

</html>