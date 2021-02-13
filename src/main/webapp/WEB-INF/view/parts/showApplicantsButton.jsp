<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization" />

<html>

  <body>
    <br>
    <fmt:message key="label.parts.show.applicants.show.applicants" />
    <br>
    <form method="GET" action="${pageContext.request.contextPath}/controller">
      <input type="hidden" name="command" value="selectionFaculty" />
      <input type="hidden" name="targetPage" value="enteredApplicants" />
      <div class="context-button">
        <button type="submit">
          <fmt:message key="label.parts.show.applicants.button.show" />
        </button>
      </div>
    </form>
  </body>

</html>