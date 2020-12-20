<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization" />

<html>

  <body>
    <div class="header">

      <div class="buttons-container">
        <div class="dropdown">
          <div class="button-language">
            <fmt:message key="label.header.button.language" />
          </div>
          <div class="dropdown-child">
            <a href="${pageContext.request.contextPath}/controller?command=local&local=en">
              <fmt:message key="label.header.button.eng" />
            </a>
            <br>
            <a href="${pageContext.request.contextPath}/controller?command=local&local=ru">
              <fmt:message key="label.header.button.rus" />
            </a>
            <br>
            <a href="${pageContext.request.contextPath}/controller?command=local&local=by">
              <fmt:message key="label.header.button.blr" />
            </a>
          </div>
        </div>

        <div class="button-login">
          <c:if test="${sessionScope.userDto == null}">
            <a href="${pageContext.request.contextPath}/controller?command=authorization">
              <fmt:message key="label.header.button.login" />
            </a>
          </c:if>
          <c:if test="${sessionScope.userDto != null}">
            <a href="${pageContext.request.contextPath}/controller?command=logout">
              <fmt:message key="label.header.button.logout" />
            </a>
          </c:if>
        </div>
      </div>

      <div class="header-name">
        <h1>
          <fmt:message key="label.header" />
        </h1>
      </div>

    </div>
  </body>

</html>