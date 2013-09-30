<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: lisak
  Date: 09.08.13
  Time: 14:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Seznam obrázků</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/admin.css" />
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <script src="/resources/js/jquery-1.9.1.js"></script>

    <script type="text/javascript">
        $(function() {
            $("#imageData").change(function() {
                var _filename = $('#imageData').val().replace(/^.*[\\\/]/, '');
                _filename = _filename.replace(/\..*/,'');
                $("#id").val(_filename);
            });
        });
    </script>

</head>
<body>
<div id="leftColumn">
    <h3>Seznam obrázků</h3>
    <form method="GET" action="/admin/image">
        <div>Filtr:</div>
        <div><input name="filter" value="${filter}" style='width: 150px'/></div>
    </form>

    <c:forEach var="image" items="${imageList}">
        <div>
            <a style='text-decoration: none; color: red' href="/admin/image/${image.id}?delete" onclick="return confirm('Opravdu SMAZAT?')"><b>X</b></a>
            &nbsp;&nbsp;
            <a href="/admin/image/${image.id}">${image.id}</a>
        </div>
    </c:forEach>
    <br>
    <div><a href="/admin/image">Přidat nový obrázek</a></div>

    <jsp:include page="links.jsp" />
</div>

<div id="mainColumn">
    <sf:form modelAttribute="image" method="POST" action="/admin/image" enctype="multipart/form-data" acceptcharset="UTF-8">
        <div>ID:</div>
        <div><sf:input path="id" /></div>

       <!-- <div>Popisek:</div>
        <div><sf:input path="caption"/></div> -->

        <div>Obrázek:</div>
        <div><input id="imageData" name="imageData" type="file"></div>
        <c:if test="${not empty image.id}">
            <div><img src="/image/${image.id}"></div>
        </c:if>
        <div style='text-align: right'>
            <input class='submitButton' value="Uložit" type="submit">
        </div>
    </sf:form>
</div>
<div style='clear: all'></div>

</body>
</html>