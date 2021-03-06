<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
    <title>Seznam článků</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/admin.css" />

</head>
<body>
<div id="leftColumn">
    <h3>Seznam článků</h3>
    <c:forEach var="article" items="${articleList}">
        <div>
            <a style='text-decoration: none; color: red' href="/admin/article/${article.id}?delete" onclick="return confirm('Opravdu SMAZAT?')"><b>X</b></a>
            &nbsp;&nbsp;
            <a href="/admin/article/${article.id}">${article.title}</a>
        </div>
    </c:forEach>
    <br>
    <div><a href="/admin/article">Přidat nový článek</a></div>

    <jsp:include page="links.jsp" />
</div>

<div id="mainColumn">
<sf:form modelAttribute="article" method="POST" action="/admin/article">
    <div>ID:</div>
    <div><sf:input path="id" /></div>
    <div>Titulek:</div>
    <div><sf:input path="title"/></div>
    <c:if test="${article.id != 'main'}">
        <div>ID nadřazeného článku (když zůstané prázdné, patří článek přímo pod titulní stránku):</div>
        <div><sf:input path="parent"/></div>
    </c:if>
    <div>Článek:</div>
    <div><sf:textarea path="text" /></div>
    <div style='text-align: right'>
        <input class='submitButton' value="Uložit" type="submit">
    </div>
</sf:form>
</div>
<div style='clear: all'></div>

</body>
</html>