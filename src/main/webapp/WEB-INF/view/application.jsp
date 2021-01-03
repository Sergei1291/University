<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <title>
      <fmt:message key="label.application.form.title" />
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
        <form method="POST" action="${pageContext.request.contextPath}/controller?command=apply">
          <input type="hidden" name="firstSubjectId" value="${subjects.get(0).id}"/>
          <input type="hidden" name="secondSubjectId" value="${subjects.get(1).id}"/>
          <input type="hidden" name="thirdSubjectId" value="${subjects.get(2).id}"/>
          <input type="hidden" name="facultyId" value="${facultyId}"/>

          <label for="firstMark">
            <c:out value="${subjects.get(0).name}"/>
          </label>
          <input type="number" required min="0" max="100" name="firstMark"/>
          <br>
          <label for="secondMark">
            <c:out value="${subjects.get(1).name}"/>
          </label>
          <input type="number" required min="0" max="100" name="secondMark"/>
          <br>
          <label for="thirdMark">
            <c:out value="${subjects.get(2).name}"/>
          </label>
          <input type="number" required min="0" max="100" name="thirdMark"/>
          <br>
          <label for="averageMark">
            <fmt:message key="label.application.form.context.input.average.mark" />
          </label>
          <input type="number" required min="0" max="100" name="averageMark"/>
          <br>
          <div class="button-send">
            <button type="submit">
              <fmt:message key="label.application.form.context.button.apply" />
            </button>
          </div>
        </form>
      </div>

    </div>
  </body>

</html>