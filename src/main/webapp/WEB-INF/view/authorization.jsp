<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <title>
      <fmt:message key="label.title.authorization" />
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
        <form method="POST" action="/University/controller?command=login">
          <fmt:message key="label.context.input.login" /><br>
          <input type="text" name="login"/><br>
          <fmt:message key="label.context.input.password" /><br>
          <input type="password" name="password"/><br>
          <input type="submit"/>
        </form>
        <br>

        <c:if test="${isVisibleErrorMessage == true}">
          <fmt:message key="label.context.error.message.login" />
        </c:if>

      </div>

    </div>
  </body>

</html>