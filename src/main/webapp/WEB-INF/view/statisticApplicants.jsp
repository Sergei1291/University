<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/static/js/statisticTable.js"></script>
    <title>
      <fmt:message key="label.statistic.applicants.title" />
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
        <fmt:message key="label.statistic.applicants.context.faculty" />
        <div class="statistic-table">
          <table>
            <tr class="th">
              <td>
                <fmt:message key="label.statistic.applicants.context.table.mark" />
              </td>
              <td>
                <fmt:message key="label.statistic.applicants.context.table.applications" />
              </td>
            </tr>
            <c:forEach var="counter" items="${counters}">
              <tr class="row">
                <td>
                  ${counter.minBorder} - ${counter.maxBorder}
                </td>
                <td>
                  ${counter.value}
                </td>
              </tr>
            </c:forEach>
          </table>
        </div>
        <script>
          initialize('statistic-table');
          display();
        </script>

      </div>

    </div>
  </body>

</html>