//global variables
var googleMap = null;
//storage for selected marker
var selectedMarker;
var mapOptions;
//hashMap with prepared markers
var markerMap = {};
//jQuery object with custom controls
var customControls;

//set map to facelifted mode
google.maps.visualRefresh = true;

//put marker into "selected" state 
//& deselect previous selected marker if there is any 
function toggleIcon(marker) {
	if(selectedMarker != null) {
		selectedMarker.setIcon(selectedMarker.icon.replace("-selected", ""));
	}
	
	if(marker != null) {
		marker.setIcon(marker.icon.replace(".", "-selected."));
		selectedMarker = marker;
	}
}

//closure callback function for click event
function onClickMarker(marker) {
	return function() {
		console.log("Marker: " + marker.profile + " clicked.");
		toggleIcon(marker);
		$('#profileBody').load('/profiles/' + marker.profile + '.html', function() {
			//when the new profile loads...
			
			//register mapLink callback for new profile
			$('a.mapLink').click(onClickMapLink);
			//align profile container on with the map
			$('#profile').css('top', 0);
			//and show the profile
			$('#profile').show();
		});
	};
}

function onClickMapLink(event) {
	event.preventDefault();
	
	//align profile container on with the map
	$('#profile').css('top', 0);
	//...and scroll to the top
	$(document).scrollTop(0);
	showMap();
	highlightMarker($(this).attr('href'));
}

//prepare markers in the format suitable for map set-up
function prepareMarkers(markerData) {
	var marker;
	
	$.each(markerData.categories, function(_k, category) {
		$.each(category.items, function(_k, mData) {
			marker = new google.maps.Marker({
				position:  new google.maps.LatLng(mData.lat, mData.lng),
				//map is lazy-initialized by showMap function
				map: null,
				profile: mData.name,
				options: {icon: category.icon},
				category: category.categoryName,
				title: mData.title
			});
			
			console.log("Marker " + mData.name + " prepared.");
			
			//add marker to global Map object
			markerMap[mData.name] = marker;
		});
	});
}

function highlightMarker(markerCode) {
	console.log("MarkerCode: " + markerCode);
	var _mark = markerMap[markerCode];
	
	toggleIcon(_mark);
	//center map to the marker
	googleMap.setCenter(_mark.getPosition());
}

function filterMarkers(categoryName, filterValue) {
	$.each(markerMap, function(markerName, marker){
		if(marker.category===categoryName) {
			marker.setVisible(filterValue);
		}
	});
}

//here we set up a map with predefined markers
function prepareMapOptions(markerData) {
	  var mapCenter = new google.maps.LatLng(50.073665, 14.433141);
	  //prepare global variable with map configuration
	  mapOptions = {
	    zoom: 13,
	    minZoom: 10,
	    center: mapCenter,
	    mapTypeControlOptions: {
		       style: google.maps.MapTypeControlStyle.DROPDOWN_MENU,
		       position: google.maps.ControlPosition.RIGHT_TOP
		     },
		zoomControlOptions: {
			style: google.maps.ZoomControlStyle.SMALL
		},
		navigationControl: false,
		scrollwheel: true,
		streetViewControl: true, 
	    mapTypeId: google.maps.MapTypeId.ROADMAP
	  };
	  
	  //set up custom map buttons
	  setupCustomControls();
}

function setupCustomControls() {
	//set global variable
	customControl = $('#mapCustomControls');
	
	//set onclick handler for mapClose
	$('#closeMap').click(function(event){
			event.preventDefault();
			hideMap();
	});
}

// create and show map (based on config stored in global vars.)
function showMap() {
	console.log("showMap called...");
	if(googleMap===null) {
		googleMap =  new google.maps.Map($("#map-canvas")[0], mapOptions);
		//set up markers
		$.each(markerMap, function(markerCode, marker) {
			marker.setMap(googleMap);
			google.maps.event.addListener(marker, 'click', onClickMarker(marker));
		});
		
		console.log("Attaching custom controls...");
		//attach custom controls
		googleMap.controls[google.maps.ControlPosition.TOP_RIGHT].push(customControl[0]);
		
		//attach onClick handler to filters
		$(".markerFilter").click(function(event) {
			var filterValue = this.checked;
			var filterId = $(this).attr("id");
			
			filterMarkers(filterId, filterValue);
			//console.log("Filter: " + $(this).attr("id") + " clicked, new value: " + this.checked);
		});
		
		
		//customControl is hidden initialy, show it now
		customControl.show(0);
	}

	//hide article
	$('article.main').hide();
	
	var mapCanvas = $('#map-canvas');

	//show prepared map
	mapCanvas.show();
	//set map height ()
	mapCanvas.height($(window).height() - mapCanvas.position().top - 20);
}

function hideMap() {
	$('#map-canvas').hide();
	$('article.main').show();
}

/******* DocumentReady functions ******/

$(document).ready(function() {
	//first, get POI JSON loaded & parsed.
	$.getJSON("../map/poi.json", function(data) {
		//prepare map configuration
		prepareMapOptions();
		//prepare hashMap of markers
		prepareMarkers(data);
	});
});	


