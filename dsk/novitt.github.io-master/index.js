let timeout;
$( "document" ).ready(function(){
	$( "#searchQuery" ).keyup(function() {
		clearTimeout(timeout);
		timeout = setTimeout(function () {
			search();
			}, 500);
		});
	$( ".dim" ).click(function(){
		$( ".dim" ).css("display", "none");
		$( ".videobox" ).remove();

	});
});

function search(s){
	$('.entry').remove();
	$('.desc').remove();
	$('.entrybox').remove();
	$.ajax({
		url:"https://api.bm92axr0lmdpdgh1yi5pbw.cf/query/?search="+$('.query').val(),
		dataType:"json",
		success:function(r){
			if(r.status == 0){
				r.results.forEach(function(item){
					$('.results').append("<a href=\"javascript:getLink('"+item.id+"');\"><div class=\"entrybox\"><span class=\"entry\">"+item.title+"</span><p class=\"desc\">Description goes here, but these currently don't have them. We're working on them!</p></div></a>");
				});
				$( "#main" ).css("animation", "up 0.15s ease-in forwards");
			} else {
				$('.entry').remove();
				$('.desc').remove();
				$('.entrybox').remove();
			}
		}
	});
};

function getLink(id){
	$('.videobox').remove();
	$.ajax({
		url:"https://api.bm92axr0lmdpdgh1yi5pbw.cf/get/?id="+id,
		dataType:"json",
		success:function(r){
			if(r.status == 0){
				$( '.dim' ).css("display", "initial");
				$('.vidwrapper').append('<video controls class="videobox" autoplay src="'+r.url+'" ></video>');
			} else {
				$('.vidwrapper').append('<div class="error" ><h5>Sorry, we couldn\'t get your video.</h5</div>');
			}
		}
	});
}


