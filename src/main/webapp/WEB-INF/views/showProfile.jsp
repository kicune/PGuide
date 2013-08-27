<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
</head>
<body>
     <h1>${profile.name}</h1>

     <div id='address'>${profile.address}</div>
     <div id='GPS'>${profile.formattedGpsCoords}</div>
     <div id='externalURL'><a href='${profile.url}'>${profile.url}</a>
         <c:if test="${profile.urlCzechOnly}">(Czech only)</c:if>
     </div>

     <img id='profileImg' src="/image/${profile.profileImg}" />

     <div id='openingHours'>
         <strong>Opening hours:</strong>
         <table>
             <tr><td>Mo</td><td>${profile.openingHours[0].morning} - ${profile.openingHours[0].afternoon}</td></tr>
             <tr><td>Tu</td><td>${profile.openingHours[1].morning} - ${profile.openingHours[1].afternoon}</td></tr>
             <tr><td>Wed</td><td>${profile.openingHours[2].morning} - ${profile.openingHours[2].afternoon}</td></tr>
             <tr><td>Thu</td><td>${profile.openingHours[3].morning} - ${profile.openingHours[3].afternoon}</td></tr>
             <tr><td>Fri</td><td>${profile.openingHours[4].morning} - ${profile.openingHours[4].afternoon}</td></tr>
             <tr><td>Sa</td><td>${profile.openingHours[5].morning} - ${profile.openingHours[5].afternoon}</td></tr>
             <tr><td>Sun</td><td>${profile.openingHours[6].morning} - ${profile.openingHours[6].afternoon}</td></tr>
         </table>
     </div>

     <c:if test="${profile.attributes.nonsmokers}">
        <img class='attribute' title='Nonsmokers' src='../resources/img/nonsmoking.png'>
     </c:if>
     <c:if test="${profile.attributes.smokers}">
         <img class='attribute' title='Smokers' src='../resources/img/smoking.png'>
     </c:if>
     <c:if test="${profile.attributes.freeWifi}">
         <img class='attribute' title='Free Wifi' src='../resources/img/wifiIcon.png'>
     </c:if>
     <c:if test="${profile.attributes.noCreditCards}">
         <img class='attribute' title='No credit cards accepted' src='../resources/img/nocredit.png'>
     </c:if>

     <div id='prices'>
         <strong>Prices:</strong><br/>
        ${profile.formatedPrices}
     </div>

     ${profile.formattedText}

     <div id='profileMap'>
         <a class='mapLink' href="/profile/${profile.id}"><img src="/image/${profile.staticMapImg}"></a>
     </div>

     <div id="gallery">
         <c:forEach var="imageId" items="${profile.gallery}">
         <img src="/image/${imageId}">
         </c:forEach>
     </div>

     <script>
         /**** DocumentReady functions ****/
         $(document).ready(function(){
             gallery = new Gallery('#gallery');
         });
     </script>


</body>
</html>