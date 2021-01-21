<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <title>
      <fmt:message key="label.authorization.title" />
    </title>
  </head>

  <body>
    <jsp:include page="parts/header.jsp" />
    <div class="container">
      <div class="menu">
        <jsp:include page="parts/menu.jsp" />
      </div>

      <div class="context">
        <div class="form">
          <form method="POST" action="${pageContext.request.contextPath}/controller?command=login">
            <div class="input-form">
              <label for="login">
                <fmt:message key="label.authorization.context.input.login" />
              </label>
              <br>
              <input type="text" required name="login" />
            </div>
            <div class="input-form">
              <label for="password">
                <fmt:message key="label.authorization.context.input.password" />
              </label>
              <br>
              <input type="password" required name="password" />
            </div>
            <div class="button-send">
              <button type="submit">
                <fmt:message key="label.authorization.context.button.login" />
              </button>
            </div>
          </form>
        </div>
        <c:if test="${isVisibleErrorMessage}">
          <fmt:message key="label.authorization.context.error.message" />
        </c:if>
      </div>

    </div>
  </body>

</html>