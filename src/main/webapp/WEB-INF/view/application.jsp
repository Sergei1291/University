<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <title>
      <fmt:message key="label.title.application.form" />
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

        <form method="POST" action="/University/controller?command=apply&id=${subjects.get(0).id}&id=${subjects.get(1).id}&id=${subjects.get(2).id}&id=${facultyId}">
          <input type="number" required min="0" max="100" name="firstMark"/>
          <c:out value="${subjects.get(0).name}"/>
          <br>
          <input type="number" required min="0" max="100" name="secondMark"/>
          <c:out value="${subjects.get(1).name}"/>
          <br>
          <input type="number" required min="0" max="100" name="thirdMark"/>
          <c:out value="${subjects.get(2).name}"/>
          <br>
          <input type="number" required min="0" max="100" name="averageMark"/>
          <c:out value="AVERAGE MARK"/>
          <br>
          <input type="submit"/>
        </form>

      </div>

    </div>
  </body>

</html>