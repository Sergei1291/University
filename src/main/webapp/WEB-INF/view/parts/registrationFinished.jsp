<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization" />

<html>

  <body>
    <br>
    <fmt:message key="label.parts.registration.finished.registration.finished" />
    <br>
    <c:if test="${!isApplicantListReady}">
      <br>
      <fmt:message key="label.parts.registration.finished.list.entered.not.ready" />
      <br>
      <fmt:message key="label.parts.registration.finished.visit.site.later" />
    </c:if>
    <c:if test="${isApplicantListReady}">
      <jsp:include page="showApplicantsButton.jsp" />
    </c:if>
  </body>

</html>