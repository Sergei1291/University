<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}" />
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
        <c:if test="${!isRegistrationFinished}">
          <fmt:message key="label.main.context.registration.continues" />
          <br>
          <br>
          <fmt:message key="label.main.context.apply.need.authorization" />
          <br>
          <form method="GET" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="authorization" />
            <div class="context-button">
              <button type="submit">
                <fmt:message key="label.main.context.button.authorization" />
              </button>
            </div>
          </form>
        </c:if>
        <c:if test="${isRegistrationFinished}">
          <jsp:include page="parts/registrationFinished.jsp" />
        </c:if>
      </div>

    </div>
  </body>

</html>