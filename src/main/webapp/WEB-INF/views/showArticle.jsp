<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Gentle Introduction to Prague</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/desktop.css" />
    <link rel="stylesheet" type="text/css" href="../resources/css/profile.css" />
    <link rel="stylesheet" type="text/css" href="../resources/css/gallery.css" />

    <script type="text/javascript" src="../resources/js/cookies.js"></script>
    <script type="text/javascript" src="../resources/js/modernizr.custom.08112.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="../resources/js/article.js" ></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAOio8LeBNcJ0kYGA-sY274Crvvx7h05Ew&sensor=false"></script>
    <script type="text/javascript" src="../resources/js/map.js"></script>
    <script type="text/javascript" src="../resources/js/jquery.lightbox_me.js"></script>
    <script type="text/javascript" src="../resources/js/gallery.js"></script>
</head>
<body>

<div class='wrapper'>
    <a href="/" style="border-width: 0"><div id='topTitle'></div></a>


    <div id='profile' class='profile'>
        <div id='closeProfile'>x</div>
        <div  id='profileBody'></div>
    </div>

    <article class='main'>
        <c:if test="${not showRaw}">
            <div class="breadcrumbs">${article.breadcrumbs}</div>
        </c:if>


        <h1>${article.title}</h1>

         ${showRaw ? article.text : article.formattedText}


    </article>
    <div id="map-canvas"></div>
</div>
<div id='mapCustomControls'>
    <div id='closeMap' title="Close map and return to article">Close Map</div>
    <div id='filters'>
        <div><input type='checkbox' class='markerFilter' id='coffee' checked>Cafés</div>
        <div><input type='checkbox' class='markerFilter' id='pub' checked>Pubs</div>
        <div><input type='checkbox' class='markerFilter' id='patisserie' checked>Patisseries</div>
    </div>
</div>


</body>
</html>