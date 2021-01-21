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
          <div class="dropdown-head">
            <fmt:message key="label.header.button.language" />
          </div>
          <div class="dropdown-child">
            <form method="POST" action="${pageContext.request.contextPath}/controller?command=local">
              <input type="hidden" name="query" value="${pageContext.request.queryString}">
              <input type="hidden" name="local" value="en">
              <button type="submit">
                <fmt:message key="label.header.button.eng" />
              </button>
            </form>
            <form method="POST" action="${pageContext.request.contextPath}/controller?command=local">
              <input type="hidden" name="query" value="${pageContext.request.queryString}">
              <input type="hidden" name="local" value="ru">
              <button type="submit">
                <fmt:message key="label.header.button.rus" />
              </button>
            </form>
            <form method="POST" action="${pageContext.request.contextPath}/controller?command=local">
              <input type="hidden" name="query" value="${pageContext.request.queryString}">
              <input type="hidden" name="local" value="by">
              <button type="submit">
                <fmt:message key="label.header.button.blr" />
              </button>
            </form>
          </div>
        </div>
        <div class="header-button">
          <c:if test="${sessionScope.userDto == null}">
            <form method="GET" action="${pageContext.request.contextPath}/controller">
              <input type="hidden" name="command" value="authorization">
              <button type="submit">
                <fmt:message key="label.header.button.login" />
              </button>
            </form>
          </c:if>
          <c:if test="${sessionScope.userDto != null}">
            <form method="POST" action="${pageContext.request.contextPath}/controller?command=logout">
              <button type="submit">
                <fmt:message key="label.header.button.logout" />
              </button>
            </form>
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