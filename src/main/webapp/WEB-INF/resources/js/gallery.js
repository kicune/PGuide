
function Gallery(id) {
		var gallery = this;

		//string with id of the gallery node
		this.galleryId = id;
		//array of jquery IMG nodes
		this.imgList = new Array();
		//jquery reference to main container
		this.$holder = null;
		//jquery reference to the container encapsulating images
		this.wrapper = null;
		//index in the img array for the current image
		this.currentImgIdx = 0;
		//# of images loaded
		this.imagesLoaded = 0;
		//navigation elements
		this.arrowLeft = null;
		this.arrowRight = null;

        this.lightboxShown = false;
		
		//opacity value for inactive arrows
		this.inactiveOpacity = 0.3;
		
		/*** Gallery functions ***/

        this.lightboxClick = function() {
            gallery.showLightbox(gallery.imgList[gallery.currentImgIdx]);
            return false;
        }
		
		this.scrollLeft = function(event) {
			if(gallery.currentImgIdx >= gallery.imgList.length-1)
				return;

			var scrollWidth = gallery.imgList[gallery.currentImgIdx].parent().outerWidth(true)/2
							+ gallery.imgList[gallery.currentImgIdx+1].parent().outerWidth(true)/2;
			
			//remove onclick handler for the image that is being scrolled away
			gallery.imgList[gallery.currentImgIdx].off('click')
				   .css("cursor", "default");
			
			gallery.wrapper.animate({
				left: "-=" + scrollWidth + "px"
			});
			
			gallery.currentImgIdx += 1;
			
			//add onclick handler for new centered image
            gallery.imgList[gallery.currentImgIdx].off('click');
			gallery.imgList[gallery.currentImgIdx].on("click", gallery.lightboxClick).css("cursor", "pointer");
			
		};
				
		this.scrollRight = function(event) {
			if(gallery.currentImgIdx <= 0) return;

			var scrollWidth = gallery.imgList[gallery.currentImgIdx].parent().outerWidth(true)/2
			+ gallery.imgList[gallery.currentImgIdx-1].parent().outerWidth(true)/2;
			
			//remove onclick handler for the image that is being scrolled away
			gallery.imgList[gallery.currentImgIdx].off('click');
			
			gallery.wrapper.animate({
				left: '+=' + scrollWidth + "px"
			});
			
			gallery.currentImgIdx -= 1;
			//add onclick handler for new centered image
            gallery.imgList[gallery.currentImgIdx].off('click');
			gallery.imgList[gallery.currentImgIdx].on("click", gallery.lightboxClick).css("cursor", "pointer");
			
		};
		
		this.showLightbox = function(img) {
            if(gallery.lightboxShown) return;

			console.log("Lightboxing " + img.attr('src'));

			var $lbImg = $(document.createElement("img")).attr('src', img.attr('src'));
            $lbImg.addClass("lbImage");

            var $lightboxWrapper = $("<div class='lightbox'></div>");
            $lightboxWrapper.hide();


            $lightboxWrapper.on("click", function() {
                $lightboxWrapper.trigger('close');
            });

			$lbImg.on("load", function() {
                $lightboxWrapper.append($lbImg);

                $lightboxWrapper.lightbox_me({
                    centered: true,
                    destroyOnClose: true,
                    closeSelector: ".lbImage",
                    onClose: function() { gallery.lightboxShown = false; }
                });

                $lbImg.css("maxWidth", ($(window).width() - 20) + "px");
                $lbImg.css("maxHeight", ($(window).height() - 20) + "px");
                $lightboxWrapper.show();
			});
            gallery.lightboxShown = true;
		};


		this.arrowHoverIn = function(event) {
			$(this).fadeTo('normal', 1);
		};
		
		this.arrowHoverOut = function(event) {
			$(this).fadeTo('normal', gallery.inactiveOpacity);
		};
		
		/*** Gallery functions end ***/

		//initialize holder element
		this.$holder = $(this.galleryId);
		this.$holder.css("overflow","hidden")
					.css("position", "relative");
        this.$holder.hide();
		
		//store images in imgList
		_list = $(this.galleryId + ' > img' );
		_list.each(function() {
			gallery.imgList.push($(this));
		});
		//remove images from original DOM
		_list.detach();
		
		//set up navigation buttons
		this.$arrowLeft = $("<div />")
			.addClass("galleryArrowLeft");
		this.$holder.append(this.$arrowLeft);
		
		this.$arrowLeft.css({
			top: this.$holder.height()/2 - this.$arrowLeft.height()/2,
			left: 5,
			opacity: this.inactiveOpacity
		});
		
		this.$arrowRight = $("<div />")
		.addClass("galleryArrowRight");
		this.$holder.append(this.$arrowRight);
		
		this.$arrowRight.css({
			top: this.$holder.height()/2 - this.$arrowRight.outerHeight(true)/2,
			left: this.$holder.width()-this.$arrowRight.outerWidth(true)-5,
			opacity: this.inactiveOpacity
		});
		
		this.$arrowLeft.hover(this.arrowHoverIn, this.arrowHoverOut);
		this.$arrowRight.hover(this.arrowHoverIn, this.arrowHoverOut);
			
		//add wrapper element
		this.wrapper = $(document.createElement("div"));
		this.wrapper.css({
			position: "relative",
			padding: 0,
			margin: 0
		});
		this.$holder.append(this.wrapper);
		
		this.$holder.on("load", function() {
			console.log("Holder loaded!");
		});
		
		if(this.$holder[0].complete) {
			console.log("Holder completed!");
			this.$holder.load();
		}
			
		$.each(this.imgList, function(i, img){
			//construct placeholder for every image
			d = document.createElement("div");
			$(d).addClass("imgHolder")
				.height(gallery.$holder.innerHeight());
			gallery.wrapper.append(d);

			$(d).append(img);
			img.height(gallery.$holder.innerHeight());
            img.hide();
			
			img.on("load", function() {
                img.show();
				//as soon as the first img is loaded, we will set up leading space:
				if(i==0) {
                    //disable gallery when there are no pictures in it
                    gallery.$holder.show();
					var _offset = gallery.$holder.width() /2 -
					$(this).parent().outerWidth(true)/2;
					gallery.wrapper.css({left: _offset + "px"});
					//set onclick handler for first image
					img.on("click", function(){gallery.showLightbox(img);});
					img.css("cursor", "pointer");
				}

                //refresh profileScroll height after image is loaded
                if(profileScroll != null)
                    setTimeout(function () {
                        profileScroll.refresh();
                    }, 0);
			});

		});

		//set up click handlers for scroll buttons, but only after all images in wrapper element load 
		//(so we know their widths for scrolling)
		//onLoad doesn't work for wrapper, only for $(window)
		//$(window).on("load", function (){

        if (Modernizr.touch) {
            $('#gallerySwiper').swipe({
                swipe:function(event, direction, distance, duration, fingerCount) {
                    if(direction == 'right') {
                        gallery.scrollRight();
                    }

                    if(direction == 'left') {
                        gallery.scrollLeft();
                    }
                }
            });
        }


        gallery.$arrowLeft.click(gallery.scrollRight);
        gallery.$arrowRight.click(gallery.scrollLeft);
		//});			
}

