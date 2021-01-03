<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <title>
      <fmt:message key="label.personal.account.title" />
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
          <fmt:message key="label.personal.account.context.welcome" />
        </h2>
        <h3>
          ${sessionScope.userDto.name} ${sessionScope.userDto.surname}
        </h3>
        <br>
        <form method="POST" action="${pageContext.request.contextPath}/controller?command=register">
          <div class="context-button">
            <button type="submit">
              <fmt:message key="label.personal.account.context.button.register" />
            </button>
          </div>
        </form>
      </div>

    </div>
  </body>

</html>