<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/context.css" rel="stylesheet" type="text/css">
    <title>
      <fmt:message key="label.error.title" />
    </title>
  </head>

  <body>
    <h2>
      ${message}
    </h2>
  </body>

</html>