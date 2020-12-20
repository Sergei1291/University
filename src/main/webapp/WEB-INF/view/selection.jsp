<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <title>
      <fmt:message key="label.title.selection.faculty" />
    </title>
  </head>

  <body>
    <jsp:include page="parts/header.jsp" />
    <div class="container">
      <div class="menu">
        <jsp:include page="parts/menu.jsp" />
      </div>

      <div class="context">
        <fmt:message key="label.context.select.faculty" />
        <br>
        <br>
        <c:forEach var="facultyDto" items="${facultiesDto}">
          <div class="button">
            <a href="${pageContext.request.contextPath}/controller?command=application&id=${facultyDto.id}">
              ${facultyDto.name}
            </a>
          </div>
          <br>
        </c:forEach>
      </div>

    </div>
  </body>

</html>