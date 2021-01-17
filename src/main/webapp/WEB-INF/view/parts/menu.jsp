<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization" />

<html>

  <body>
    <div class="button-menu">
      <c:if test="${sessionScope.userDto == null}">
        <a href="${pageContext.request.contextPath}/controller?command=main">
          <fmt:message key="label.menu.button.main" />
        </a>
      </c:if>
      <c:if test="${sessionScope.userDto != null}">
        <a href="${pageContext.request.contextPath}/controller?command=account">
          <fmt:message key="label.menu.button.account" />
        </a>
      </c:if>
    </div>
    <c:if test="${sessionScope.userDto != null}">
      <div class="button-menu">
        <c:if test="${sessionScope.userDto.role == 'ENROLLEE'}">
          <a href="${pageContext.request.contextPath}/controller?command=personalApplication">
            <fmt:message key="label.menu.button.personal.application" />
          </a>
        </c:if>
        <c:if test="${sessionScope.userDto.role == 'COMMITTEE'}">
          <a href="${pageContext.request.contextPath}/controller?command=selectionFaculty&targetPage=applications">
            <fmt:message key="label.menu.button.applications" />
          </a>
        </c:if>
      </div>
    </c:if>
    <div class="button-menu">
      <a href="${pageContext.request.contextPath}/controller?command=entranceCompany">
        <fmt:message key="label.menu.button.entrance.company" />
      </a>
    </div>
  </body>

</html>