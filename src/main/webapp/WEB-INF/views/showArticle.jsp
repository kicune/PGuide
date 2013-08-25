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

    <div id='topTitle'></div>

    <div id='profile' class='profile'>
        <div id='closeProfile'>x</div>
        <div  id='profileBody'></div>
    </div>

    <article class='main'>
        <div>TODO: breadcrumbs</div>

        <h1>${article.title}</h1>

        ${article.formattedText}

    </article>
    <div id="map-canvas"></div>
</div>
<div id='mapCustomControls'>
    <div id='closeMap' title="Close map and return to article">Close Map</div>
    <div id='filters'>
        <div><input type='checkbox' class='markerFilter' id='Cafes' checked>Cafés</div>
        <div><input type='checkbox' class='markerFilter' id='Pubs' checked>Pubs</div>
        <div><input type='checkbox' class='markerFilter' id='Patisseries' checked>Patisseries</div>
    </div>
</div>


</body>
</html>