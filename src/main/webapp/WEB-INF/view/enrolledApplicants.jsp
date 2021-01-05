<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ex" uri="tag/custom.tld"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization" />

<html>

  <head>
    <link href="${pageContext.request.contextPath}/static/styles/style.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/static/js/pagination.js"></script>
    <title>
      <fmt:message key="label.applicants.title" />
    </title>

  </head>

  <body>
    <jsp:include page="parts/header.jsp" />
    <div class="container">
      <div class="menu">
        <jsp:include page="parts/menu.jsp" />
      </div>

      <div class="context">

        <ex:hello>HI mmm</ex:hello>
        <br>

        <div class="table">
          <table>
            <tr class="th">
              <td>
                <fmt:message key="label.applicants.context.table.num" />
              </td>
              <td>
                <fmt:message key="label.applicants.context.table.surname" />
              </td>
              <td>
                <fmt:message key="label.applicants.context.table.name" />
              </td>
              <td>
                <fmt:message key="label.applicants.context.table.bal" />
             </td>
            </tr>
            <c:forEach var="enrollee" items="${enrolledApplicants}" varStatus="status">
              <tr class="row">
                <td>
                  <c:out value="${status.count}"/>
                </td>
                <td>
                  <c:out value="${enrollee.surname}"/>
                </td>
                <td>
                  <c:out value="${enrollee.name}"/>
                </td>
                <td>
                  <c:out value="${enrollee.balAmount}"/>
                </td>
              </tr>
            </c:forEach>
          </table>
        </div>

        <div class="pagination"></div>

        <script>
          initialize();
          display();
          displayPagination();
          eventHandle();
        </script>

      </div>

    </div>
  </body>

</html>