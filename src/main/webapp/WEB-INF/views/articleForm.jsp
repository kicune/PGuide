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
</head>
<body>

<sf:form modelAttribute="article" method="POST" action="/admin/article">
    ID: <sf:input path="id" /><br/>
    Titulek: <sf:input size = "30" path="title"/><br/>
    Článek: <sf:textarea path="text" cols="30" rows="10"/>
    <input name="Odeslat" type="submit">
</sf:form>

</body>
</html>