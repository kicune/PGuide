<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lisak
  Date: 09.08.13
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Report prázdných hodnot</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/admin.css" />

</head>
<body>
<div id="leftColumn">
    <h3>Kontrola prázdných polí</h3>
    <jsp:include page="links.jsp" />
</div>

<div id="mainColumn">
    <c:forEach var="line" items="${emptyValueList}">
        ${line}<br/>
    </c:forEach>
</div>

<div style='clear: all'></div>

</body>
</html>