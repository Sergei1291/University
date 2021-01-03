<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <title>
      <fmt:message key="label.main.title" />
    </title>
  </head>

  <body>
    <jsp:include page="parts/header.jsp" />
    <div class="container">
      <div class="menu">
        <jsp:include page="parts/menu.jsp" />
      </div>

      <div class="context">
        <h1>
          <fmt:message key="label.main.context.welcome.site" />
        </h1>
        <br>
        <c:if test="${requestScope.isRegistrationFinished}">
          <fmt:message key="label.main.context.registration.finished" />
          <form method="GET" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="selectFaculty">
            <input type="hidden" name="targetPage" value="view">
            <div class="context-button">
              <button type="submit">
                <fmt:message key="label.main.context.button.enrolled" />
              </button>
            </div>
          </form>
        </c:if>

      </div>

    </div>
  </body>

</html>