<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}" />
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
        <fmt:message key="label.enum.faculty.${param.facultyName}" />
        <fmt:message key="label.application.form.context.faculty" />
        <br>

        <div class="form">
          <form method="POST" action="${pageContext.request.contextPath}/controller?command=apply">
            <input type="hidden" name="firstSubjectId" value="${subjects.get(0).id}" />
            <input type="hidden" name="secondSubjectId" value="${subjects.get(1).id}" />
            <input type="hidden" name="thirdSubjectId" value="${subjects.get(2).id}" />
            <input type="hidden" name="facultyId" value="${param.facultyId}" />
            <div class="input-form">
              <label for="firstMark">
                <fmt:message key="label.enum.subject.${subjects.get(0).name}" />
              </label>
              <br>
              <input type="number" required min="0" max="100" name="firstMark" />
            </div>
            <div class="input-form">
              <label for="secondMark">
                <fmt:message key="label.enum.subject.${subjects.get(1).name}" />
              </label>
              <br>
              <input type="number" required min="0" max="100" name="secondMark" />
            </div>
            <div class="input-form">
              <label for="thirdMark">
                <fmt:message key="label.enum.subject.${subjects.get(2).name}" />
              </label>
              <br>
              <input type="number" required min="0" max="100" name="thirdMark" />
            </div>
            <div class="input-form">
              <label for="averageMark">
                <fmt:message key="label.application.form.context.input.average.mark" />
              </label>
              <br>
              <input type="number" required min="0" max="100" name="averageMark" />
            </div>
            <div class="button-send">
              <button type="submit">
                <fmt:message key="label.application.form.context.button.apply" />
              </button>
            </div>
          </form>
        </div>

      </div>

    </div>
  </body>

</html>