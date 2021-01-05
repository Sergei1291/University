<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <title>
      <fmt:message key="label.selection.faculty.title" />
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
        <fmt:message key="label.selection.faculty.context.select.faculty" />
        <br><br>
        <c:forEach var="facultyDto" items="${facultiesDto}">
          <form method="GET" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="enrolledApplicants">
            <input type="hidden" name="facultyId" value="${facultyDto.id}">
            <div class="context-button">
              <button type="submit">
                ${facultyDto.name}
              </button>
            </div>
          </form>
        </c:forEach>
      </div>

    </div>
  </body>

</html>