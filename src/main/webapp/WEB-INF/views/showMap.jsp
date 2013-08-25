<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="../resources/css/desktop-map.css" />
    <link rel="stylesheet" type="text/css" href="../resources/css/profile.css" />
    <script src="../resources/js/jquery-1.9.1.js"></script>
    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAOio8LeBNcJ0kYGA-sY274Crvvx7h05Ew&sensor=false">
    </script>

    <script type="text/javascript">
        var selectedMarker;

        google.maps.visualRefresh = true;

        //switch off caching for AJAX responses
        $.ajaxSetup ({
            cache: false
        });

        //put marker into "selected" state
        //& deselect previous selected marker if there is any
        function toggleIcon(marker) {
            if(selectedMarker != null) {
                selectedMarker.setIcon(selectedMarker.icon.replace("-selected", ""));
            }

            if(marker!=null) {
                marker.setIcon(marker.icon.replace(".", "-selected."));
                selectedMarker = marker;
            }
        }


        //prepare markers in the format suitable for map set-up
        function prepareMarkers(data) {
            var markers = [];
            var marker;

            $.each(data.categories, function(_k, category) {
                $.each(category.items, function(_k, mData) {
                    console.log(mData.lat + ", " + mData.lng);
                    marker = {
                        latLng: [mData.lat, mData.lng],
                        data: mData.name,
                        options: {icon: category.icon},
                        title: mData.title
                    };
                    markers.push(marker);
                });
            });

            return markers;
        }

        //here we set up a map with predefined markers
        function setUpMap(preparedMarkers) {
            $('#map-canvas').gmap3({
                map:{
                    options:{
                        center:[50.060665, 14.451141],
                        zoom:11,
                        minZoom: 10,
                        mapTypeId: google.maps.MapTypeId.ROADMAP,
                        mapTypeControl: true,
                        mapTypeControlOptions: {
                            style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
                        },
                        navigationControl: true,
                        scrollwheel: true,
                        streetViewControl: true
                    }
                },

                marker: {
                    values: preparedMarkers,

                    events: {
                        click: function(marker, event, context) {
                            console.log("Marker: " + context.data);
                            toggleIcon(marker);
                            $('#profileBody').load('/profile/' + context.data + '.html');
                            $('#profile').show();
                        }
                    }
                }
            });
        }

        $(document).ready(function() {
            //first, get POI JSON loaded & parsed.
            $.getJSON("/poi.json", function(data) {
                //when POI objects are ready, set up a map with them
                setUpMap(prepareMarkers(data));
            });

            /* Setting  up profile */

            //hide profile at the beginning
            $('#profile').hide();

            //close profile when X button is clicked
            $('#closeProfile').click(function() {
                $('#profile').hide();
            });
        });


    </script>

    <title>Gentle Introduction to Prague</title>
</head>
<body>
<div id="map-canvas"></div>
<div id='profile' class='profile'>
    <div id='closeProfile'>x</div>
    <div  id='profileBody'></div>
</div>
</body>
</html>
