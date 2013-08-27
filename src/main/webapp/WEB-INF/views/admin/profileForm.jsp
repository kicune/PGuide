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
<html>
<head>
    <meta charset="utf-8" />
    <title>Seznam profilů</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/admin.css" />
    <link rel="stylesheet" href="/resources/smoothness/jquery-ui.css" />

    <script src="/resources/js/jquery-1.9.1.js"></script>
    <script src="/resources/js/jquery-ui-1.10.3.custom.js"></script>

    <script>
        $(function() {
            var availableTags = ${strImgList};

            $( "#galleryList" ).autocomplete({
                source: availableTags,
                minLength: 0
            });

            $( "#profileImg" ).autocomplete({
                source: availableTags,
                minLength: 0
            });

            $( "#staticMapImg" ).autocomplete({
                source: availableTags,
                minLength: 0
            });

            $( "#galleryContent" ).sortable();
            $( "#galleryContent" ).disableSelection();

            $(function() {
                //add field to field list when "add image" button is pressed
                $("#addImage").click(function() {
                    var imgName =  $("#galleryList").val();
                    //create new DIV with class ui-state-default and custom attribute data-content == value of #imgList
                    var _newDiv = $("<div/>", {
                        class: "ui-state-default",
                        text: imgName
                    });
                    _newDiv.attr('data-content', imgName);
                    _newDiv.prepend('<span class="imgDel">X</span>');

                    //& append the new DIV after the last child of galleryContent
                    _newDiv.appendTo("#galleryContent");

                    //clear the imgList input field afterwards
                    $("#galleryList").val("");

                    //re-register onclick handler (for new images)
                    $("span.imgDel").click(function() {
                        $(this).parent().remove();
                    });
                });
                //fill hidden "gallery" field when submit button is pressed
                $("#profile").submit(function() {
                    //$("#galleryContent > div").append('<input type="hidden" name="gallery" value="' + $(this).text() + '">');
                    $("#galleryContent > div").append(function(index, html) {
                        return '<input type="hidden" name="gallery[' + index + ']" value="' + $(this).attr('data-content') + '"/>';
                    });
                });

            });
        });
    </script>

</head>
<body>
<div id="leftColumn">
    <h3>Seznam profilů</h3>
    <c:forEach var="profile" items="${profileList}">
        <div>
            <a style='text-decoration: none; color: red' href="/admin/profile/${profile.id}?delete" onclick="return confirm('Opravdu SMAZAT?')"><b>X</b></a>
            &nbsp;&nbsp;
            <a href="/admin/profile/${profile.id}">${profile.name}</a>
        </div>
    </c:forEach>
    <br>
    <div><a href="/admin/profile">Přidat nový profil</a></div>

    <hr/>
    <div><a href="/admin/article">Seznam článků</a></div>
    <div><a href="/admin/image">Seznam obrázků</a></div>
</div>

<div id="mainColumn">
<sf:form modelAttribute="profile" method="POST" action="/admin/profile">
    <div>ID:</div>
    <div><sf:input path="id" /></div>

    <div>Název:</div>
    <div><sf:input path="name"/></div>

    <div>Kategorie:</div>
    <div>
        <sf:select path="category">
            <sf:options items="${categoryList}" itemLabel="name" itemValue="id" />
        </sf:select>
    </div>

    <div>URL:</div>
    <div><sf:input path="url"/> <sf:checkbox path="urlCzechOnly" style='width: 20px'/> Jen česky</div>

    <div>GPS:</div>
    <div><sf:input path="gpsCoords"/></div>

    <div>Adresa:</div>
    <div><sf:input path="address"/></div>

    <div>Atributy:</div>
    <div style="width: 600px">
        <sf:checkbox path="attributes.freeWifi" style='width: 20px'/> Free Wifi &nbsp;
        <sf:checkbox path="attributes.noCreditCards" style='width: 20px'/> No credit cards &nbsp;
        <sf:checkbox path="attributes.nonsmokers" style='width: 20px'/> Nonsmokers &nbsp;
        <sf:checkbox path="attributes.smokers" style='width: 20px'/> Smokers;
    </div>

    <div>Otvírací hodiny:</div>
    <div>(pokud zavřeno: nechat prázdné)</div>
    <div><input name="openingHours[0].morning" value="${profile.openingHours[0].morning}" style='width: 80px'/>&nbsp;
        <input name="openingHours[0].afternoon" value="${profile.openingHours[0].afternoon}" style='width: 80px'/></div>
    <div><input name="openingHours[1].morning" value="${profile.openingHours[1].morning}" style='width: 80px'/>&nbsp;
        <input name="openingHours[1].afternoon" value="${profile.openingHours[1].afternoon}" style='width: 80px'/></div>
    <div><input name="openingHours[2].morning" value="${profile.openingHours[2].morning}" style='width: 80px'/>&nbsp;
        <input name="openingHours[2].afternoon" value="${profile.openingHours[2].afternoon}" style='width: 80px'/></div>
    <div><input name="openingHours[3].morning" value="${profile.openingHours[3].morning}" style='width: 80px'/>&nbsp;
        <input name="openingHours[3].afternoon" value="${profile.openingHours[3].afternoon}" style='width: 80px'/></div>
    <div><input name="openingHours[4].morning" value="${profile.openingHours[4].morning}" style='width: 80px'/>&nbsp;
        <input name="openingHours[4].afternoon" value="${profile.openingHours[4].afternoon}" style='width: 80px'/></div>
    <div><input name="openingHours[5].morning" value="${profile.openingHours[5].morning}" style='width: 80px'/>&nbsp;
        <input name="openingHours[5].afternoon" value="${profile.openingHours[5].afternoon}" style='width: 80px'/></div>
    <div><input name="openingHours[6].morning" value="${profile.openingHours[6].morning}" style='width: 80px'/>&nbsp;
        <input name="openingHours[6].afternoon" value="${profile.openingHours[6].afternoon}" style='width: 80px'/></div>


    <div>Ceny:</div>
    <div><sf:textarea path="prices" style="height: 50px"/></div>

    <div>Profilová fotka:</div>
    <div><input name="profileImg" id="profileImg" value="${profile.profileImg}" style="width: 150px"/></div>

    <div>Statická mapka:</div>
    <div><input name="staticMapImg" id="staticMapImg" value="${profile.staticMapImg}" style="width: 150px"/></div>

    <div>Galerie:</div>
    <div>
        <input id="galleryList" style="width: 150px"/>&nbsp;<input id="addImage" type="button" value="Přidat" style="width: 50px"/>

        <div>
            <div class="galleryContent" id="galleryContent">
                <c:forEach var="gallery" items="${profile.gallery}">
                    <div class='ui-state-default' data-content="${gallery}"><span class="imgDel">X</span>${gallery}</div>
                </c:forEach>
            </div>
        </div>
    </div>

    <!-- <div>Perex:</div>
    <div><sf:textarea path="perex" style="height: 100px"/></div> -->

    <div>Text:</div>
    <div><sf:textarea path="text" /></div>

    <div style='text-align: right'>
        <input class='submitButton' value="Uložit" type="submit">
    </div>
</sf:form>
</div>
<div style='clear: both'></div>

</body>
</html>