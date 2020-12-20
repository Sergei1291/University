<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <title>
      <fmt:message key="label.title.recruitment" />
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

        <table width="80%" align="center" border="3">
          <tr>
            <td>
              <fmt:message key="label.context.table.num" />
            </td>
            <td>
              <fmt:message key="label.context.table.faculty" />
            </td>
            <td>
              <fmt:message key="label.context.table.subjects" />
            </td>
            <td>
              <fmt:message key="label.context.table.recruitment" />
            </td>
          </tr>

          <c:forEach var="faculty" items="${faculties}" varStatus="status">
            <tr>
              <td>
                <c:out value="${status.count}"/>
              </td>
              <td>
                <c:out value="${faculty.name}"/>
              </td>
              <td>
                <c:forEach var="subject" items="${faculty.subjects}">
                <c:out value="${subject.name}"/><br>
                </c:forEach>
              </td>
              <td>
                <c:out value="${faculty.recruitment}"/>
              </td>
            </tr>
          </c:forEach>

        </table>

      </div>

    </div>
  </body>

</html>