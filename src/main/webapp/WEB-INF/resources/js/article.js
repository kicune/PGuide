//switch off caching for AJAX responses
$.ajaxSetup ({
    cache: false
});

var arrowsVisible = true;
var ARROW_HEAD_WIDTH;
var ARROW_WIDTH;
var PROFILE_WIDTH;

//minimal & maximal width of main column
// if width < than MIN_COLUMN, arrows starts hidden
var MIN_COLUMN_WIDTH = 500;
var MAX_COLUMN_WIDTH;

//true if profile must be shifted to view (due to small viewport)
var PROFILE_SHIFT_SHOW = false;
var PROFILE_SHOWN = false;

//global variables for DIV container selectors
var $arrows, $main, $profile, $article, $mapCanvas, $profileBody;

function reallyWeirdWorkaroundForFixingArticleArtifactWhileScrolling() {
    //I have absolutely no idea why this works, but it does.
    //Without it, on mobile browsers (iphone, android...) scrolling leaves artifacts after hide/show arrows
    //append() actualy does not display anything, it's DIV is hidden.
    $('#fixArtifacts').append($article.width());

    //TODO? maybe change article scrolling to iScroll as well...?
}

function log(text) {
    $('#log').append("<div>" + text + "</div>");
}

function showArrows() {
    arrowsVisible = true;
    //$article.css('overflow-y', 'hidden');
    $arrows.offset({left: 0});
    $main.offset({left: $arrows.width()});
    reallyWeirdWorkaroundForFixingArticleArtifactWhileScrolling();
}

function hideArrows() {
    //TODO: efektové!
    arrowsVisible = false;
    $main.offset({left: ARROW_HEAD_WIDTH});
    $arrows.offset({left: ARROW_HEAD_WIDTH-$arrows.width() });
    reallyWeirdWorkaroundForFixingArticleArtifactWhileScrolling();

}

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

function fitDivToViewport($div) {
    $div.css('height', ($(window).height() - $('#pageHeader').height() - 10) + 'px');
}

function showProfile() {
    //when a new profile is loaded, re-register click event listener for all mapLink links
    $('a.mapLink').click(onClickMapLink);

    if(PROFILE_SHIFT_SHOW) {
        //shift main and arrows out of the way
        var profileLeft = $(window).width() - $profile.width();
        var mainLeft = profileLeft - $main.width();
        var arrowsLeft = mainLeft - $arrows.width();

        $arrows.offset({left: arrowsLeft});
        $main.offset({left: mainLeft});
        $profile.css('left', profileLeft);
        //.offset() doesnt work properly here - doubles the left position every second click. wtf?
    } else {
        //re-set "basic" coordinates (resize/device orientation could change since last profile show)
        $arrows.offset({left: 0});
        $main.offset({left: $arrows.width()});
        $profile.css('left', ($main.width() + $arrows.width()) + "px");
    }

    $profile.show();

    $profileBody.prepend("<div id='closeProfile'>x</div>");

    //swipe profile to hide
    if(Modernizr.touch) {
        $('#profileSwiper').swipe({
            swipe:function(event, direction, distance, duration, fingerCount) {
                if(direction == 'right') {
                    hideProfile();
                }
            }
        });

        //instantiate iScroll for profile
        if(profileScroll !== undefined) {
            profileScroll.destroy();
            profileScroll = null;
        }
        profileScroll = new iScroll('profile', {
            hideScrollbar: false
        });
    }

    //close profile when X button is clicked
    $('#closeProfile').click(function() {
        hideProfile();
    });

    PROFILE_SHOWN = true;
}

function hideProfile() {
    $profile.hide();

    //unshift columns if necessary
    if(PROFILE_SHIFT_SHOW) {
        if(arrowsVisible) {
            showArrows();
        } else {
            hideArrows();
        }
    }
    PROFILE_SHOWN = false;
}

function onClickMapLink(event) {
    event.preventDefault();

    //if there is not enough place for profile & article to be shown at once,
    //map cannot be shown either
    if(PROFILE_SHIFT_SHOW && PROFILE_SHOWN) {
        hideProfile();
    }

    //take only the last part of the URL - it's the marker code
    showMap($(this).attr('href').replace(/^.*[\/]/, ""));
}


$(document).ready(function(){

    $arrows = $('#arrows');
    $main = $('#mainColumn');
    $profile = $('#profile');
    $article = $('#article');
    $mapCanvas = $('#map-canvas');
    $profileBody = $('#profileBody');

    //set constants based on CSS values
    PROFILE_WIDTH = $profile.width();
    ARROW_WIDTH = $arrows.width();
    ARROW_HEAD_WIDTH = 30;
    MAX_COLUMN_WIDTH = 640;


    //TODO: background - probably different background image for different aspect ratios

    //set up article's width - if possible, leave place for a profile
    var articleWidth = $(window).width() - ARROW_WIDTH - PROFILE_WIDTH;
    if(articleWidth >= MIN_COLUMN_WIDTH) {
        $main.width(articleWidth);
        PROFILE_SHIFT_SHOW = false;
    } else {
        //if there is not enough width for a profile, arrows/articles must be moved out of the way
        articleWidth = $main.width();
        PROFILE_SHIFT_SHOW = true;
    }

    //FIXME: landscape orientace na telefonu - neni videt spodni sipka
    //FIXME: hide content before redraw to prevent flickering

    //hide arrows (on small viewscreens). isMain is defined on main page
    if($main.width()<MIN_COLUMN_WIDTH && !isMain) {

        //arrows shown only on title page, hidden otherways.
        //after click the article shifts to the right
        $main.offset({left: ARROW_HEAD_WIDTH});
        $main.width($main.parent().width() - ARROW_HEAD_WIDTH);
        $arrows.offset({left: ARROW_HEAD_WIDTH-$arrows.width() });
        arrowsVisible = false;

        if (Modernizr.touch === true) {
            $arrows.swipe({
                swipe:function(event, direction, distance, duration, fingerCount) {
                    if(arrowsVisible && direction == 'left') {
                        hideArrows();
                    }

                    if(!arrowsVisible && direction == 'right') {
                        showArrows();
                    }
                }
            });

        } else {
            $arrows.click(function() {
                if(arrowsVisible) {
                    hideArrows();
                } else {
                    showArrows();
                }
            });
        }
    } else {
        arrowsVisible = true;
    }

    //resize profile's height
    fitDivToViewport($profile);

    //resize article's height
    fitDivToViewport($article);

    //fixme: resize makes funny things with content...
    /*$(window).resize(function() {
        fitDivToViewport($profile);
        fitDivToViewport($article);
    });*/

    $('a.profileLoader').click(function(){
        $profileBody.load('/profile/' + $(this).attr('href'), showProfile);
        return false;
    });

    var arrowScroll = new iScroll('arrowsWrapper');
    //wait & refresh iScroll to recompute height of scrollable content
    setTimeout( function(){
        arrowScroll.refresh() ;
    }, 200);


    //prettyprint all images in articles, except for those marked with .raw class
	$article.find("img:even").not(".raw").wrap(function() {
			return imgDesc($(this), 'article-even');
	});
	$article.find("img:odd").not(".raw").wrap(function() {
		return imgDesc($(this), 'article-odd');
	});
});
