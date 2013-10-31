<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!--
 main - hlavni stranka, sipky se nedaji zasunout
 article - stranka s clankem, sipky jsou zasunute a pri vysunuti odsunou clanek doprava
        // alternativa - pri sirce vetsi nez X jsou sipky nastalo vysunute (jako v main)
profile - po kliknuti na profil se zprava vysune profil a odsune clanek i se sipkami doleva
-->

<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8" />
    <title>Gentle Introduction to Prague</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=2.0, user-scalable=1">
    <meta name="HandheldFriendly" content="true"/>
    <meta name="MobileOptimized" content="320"/>

    <link rel='stylesheet' media='screen' href='../resources/css/screen.css' />
    <link rel='stylesheet' media='screen' href='../resources/css/arrows.css' />
    <link rel="stylesheet" type="text/css" href="../resources/css/profile.css" />
    <link rel="stylesheet" type="text/css" href="../resources/css/gallery.css" />

    <script type="text/javascript" src="../resources/js/cookies.js"></script>
    <script type="text/javascript" src="../resources/js/modernizr.custom.08112.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="../resources/js/article.js" ></script>
    <script type="text/javascript" src="../resources/js/jquery.lightbox_me.js"></script>
    <script type="text/javascript" src="../resources/js/gallery.js"></script>
    <script type="text/javascript" src="../resources/js/jquery.touchSwipe.min.js"></script>
    <script type="text/javascript" src="../resources/js/iscroll-lite.js"></script>
    <script type="text/javascript" src="../resources/js/gallery.js"></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAOio8LeBNcJ0kYGA-sY274Crvvx7h05Ew&sensor=false"></script>
    <script type="text/javascript" src="../resources/js/map.js"></script>

    <script type="text/javascript">
        //additional arguments from controller to JS
        var isMain = ${isMain ? "true" : "false" };
                //true for the main (root) article. Originally used for showing arrows on the main page.
                // Ignored ATM
        var mapOnly = ${mapOnly ? "true" : "false" };
        //global flag to determine whether map is shown
        var MAP_SHOWN;

        var profileScroll;
    </script>

</head>
<body>
     <a href='/'><div id="pageHeader"></div></a><div id="background"></div>
     <div id="bodyWrapper">

        <div id="arrowsWrapper">
            <div id="arrows">
                    <div class="arrow" id="tasteArrow">
                        <div class="title">Taste of Prague</div>
                        <div class="arrowCategories">
                            <div class="category"><a href="/article2/pubs">Pubs</a></div>
                            <div class="category">Patisseries</div>
                            <div class="category"><a href="/article2/cafes">Caffés</a></div>
                            <div class="category">Restaurants</div>
                            <div class="category">Wine bars</div>
                        </div>
                    </div>

                <div class="arrow" id="storyArrow">
                    <div class="title">Story of Prague</div>
                    <div class="arrowCategories">
                        <div class="category">Architecture</div>
                        <div class="category">History</div>
                        <div class="category">Legends &amp; Mysteries</div>
                    </div>
                </div>

                <div class="arrow" id="lifeArrow">
                    <div class="title">Life in Prague</div>
                    <div class="arrowCategories">
                        <div class="category">Transport</div>
                        <div class="category">Lodging</div>
                        <div class="category">Annoyances</div>
                        <div class="category">Internet</div>
                    </div>
                </div>

                <div class="arrow" id="funArrow">
                    <div class="title">Fun in Prague</div>
                    <div class="arrowCategories">
                        <div class="category">Geocaches</div>
                        <div class="category">City walks</div>
                        <div class="category">Museums &amp; Galleries</div>
                        <div class="category">Surroundings</div>
                    </div>
                </div>

            </div>
         </div>

         <div id="mainColumn">
            <div id="article">
                <div class="breadcrumbs">${article.breadcrumbs}</div>
                <h1>${article.title}</h1>
                <div id="log"></div>

                ${showRaw ? article.text : article.formattedText}

            </div>
             <div id="map-canvas"></div>
             <div id="fixArtifacts" style='display: hidden'></div>
        </div>

        <div id='profile'>
           <div id='profileBody'></div>
        </div>


</div>
    <div id='mapCustomControls'>
        <div id='filters'>
            <div><input type='checkbox' class='markerFilter' id='coffee' checked>Cafés</div>
            <div><input type='checkbox' class='markerFilter' id='pub' checked>Pubs</div>
            <div><input type='checkbox' class='markerFilter' id='patisserie' checked>Patisseries</div>
            <div><input type='checkbox' class='markerFilter' id='restaurant' checked>Restaurants</div>
        </div>
        <div id='closeMap' title="Close map and return to article">Close Map</div>
    </div>

</body>
</html>