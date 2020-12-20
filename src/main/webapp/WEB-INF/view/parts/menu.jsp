<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization" />

<html>

  <body>
    <div class="button-menu">
      <a href="${pageContext.request.contextPath}/controller?command=main">
        <fmt:message key="label.menu.button.main" />
      </a>
    </div>
    <div class="button-menu">
      <a href="${pageContext.request.contextPath}/controller?command=recruitment">
        <fmt:message key="label.menu.button.recruitment" />
      </a>
    </div>
  </body>

</html>