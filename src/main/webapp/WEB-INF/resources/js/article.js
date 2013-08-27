//switch off caching for AJAX responses
$.ajaxSetup ({
    cache: false
});

//show profile after a click
$(document).ready(function(){
		$('a.mapLink').click(onClickMapLink);
		
	 	$('a.profileLoader').click(function(){
         	$('#profileBody').load('/profile/' + $(this).attr('href'), function() {
         		
         		//when a new profile is loaded, re-register click event listener for all mapLink links
         		$('a.mapLink').click(onClickMapLink);
         		
             	var position = $(document).scrollTop();
                var _profile = $('#profile');
             	if(position>115) {
             		_profile.css('top', position -115);
             	} else {
             		_profile.css('top', 0);
             	}
             	_profile.show();
         	});

	 		return false;
	 	});
	 	

 }); 
 
//close profile when X button is clicked
$(document).ready(function(){
		$('#closeProfile').click(function() {
			$('#profile').hide();
		});
});

//surround every img.article with border DIV and place img ALT text as a desciption (if there is any)
function imgDesc(elem, cssClass) {
	var _desc = "";
	var _descDiv = "";
	//show description only for defined ALT text
	if(typeof elem.attr("alt") != 'undefined' && elem.attr("alt")!= '') {
		_desc = elem.attr("alt");
		_descDiv = "<div class='img-desc'>" + _desc +"</div>";
	}
		
	return "<div class='" + cssClass +"'><div></div>" + _descDiv + "</div>";
}


$(document).ready(function(){
	/*if (Modernizr.touch) {
		alert("This is touch device!");
	} else {
		alert("This is NOT touch device!");
	}*/
	
	//hide profile at the beginning
	$('#profile').hide();

    //prettyprint all images in articles, except for those marked with .raw class
	$("article.main img:even").not(".raw").wrap(function() {
			return imgDesc($(this), 'article-even');
	});
	$("article.main img:odd").not(".raw").wrap(function() {
		return imgDesc($(this), 'article-odd');
	});
});