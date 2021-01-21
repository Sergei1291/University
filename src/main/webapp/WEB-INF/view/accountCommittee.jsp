<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <title>
      <fmt:message key="label.account.title" />
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
          <fmt:message key="label.account.context.welcome" />
        </h2>
        <h3>
          ${sessionScope.userDto.name} ${sessionScope.userDto.surname}
        </h3>
        <br>
        <c:if test="${!isRegistrationFinished}">
          <fmt:message key="label.account.context.apply.continue" />
          <br>
          <form method="POST" action="${pageContext.request.contextPath}/controller?command=closeRegistration">
            <div class="context-button">
              <c:set var="messageConfirmClose" scope="page">
                <fmt:message key="label.account.context.close.message.confirm" />
              </c:set>
              <button type="submit" onclick="confirm('${messageConfirmClose}')">
                <fmt:message key="label.account.context.button.close.registration" />
              </button>
            </div>
          </form>
        </c:if>
        <c:if test="${isRegistrationFinished}">
          <c:if test="${!isApplicantListReady}">
            <br>
            <fmt:message key="label.account.context.registration.finished" />
            <br><br>
            <fmt:message key="label.account.context.edit.applications" />
            <br><br>
            <fmt:message key="label.account.context.form.entered.list" />
            <br>
            <form method="POST" action="${pageContext.request.contextPath}/controller?command=formLists">
              <div class="context-button">
                <c:set var="messageConfirmForm" scope="page">
                  <fmt:message key="label.account.context.form.message.confirm" />
                </c:set>
                <button type="submit" onclick="confirm('${messageConfirmForm}')">
                  <fmt:message key="label.account.context.button.form.entered.list" />
                </button>
              </div>
            </form>
          </c:if>
          <c:if test="${isApplicantListReady}">
            <jsp:include page="parts/showApplicantsButton.jsp" />
          </c:if>
        </c:if>
      </div>

    </div>
  </body>

</html>