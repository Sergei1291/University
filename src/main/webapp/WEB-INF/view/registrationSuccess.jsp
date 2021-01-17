<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <title>
      <fmt:message key="label.registration.success.title" />
    </title>
  </head>

  <body>
    <jsp:include page="parts/header.jsp" />
    <div class="container">
      <div class="menu">
        <jsp:include page="parts/menu.jsp" />
      </div>

      <div class="context">
        <h2>
          <fmt:message key="label.registration.success.context.registration.successful" />
        </h2>
        <h3>
          <fmt:message key="label.registration.success.context.registered.application" />
        </h3>
        <h3>
          ${processedApplications}
        </h3>
        <form method="GET" action="${pageContext.request.contextPath}/controller">
          <input type="hidden" name="command" value="account" />
          <div class="context-button">
            <button type="submit">
              <fmt:message key="label.registration.success.context.button.account" />
            </button>
          </div>
        </form>

      </div>

    </div>
  </body>

</html>