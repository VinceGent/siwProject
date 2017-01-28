/**
 * 
 */
var $login;
var $signup;
var $shopping;
var $wishlists;

$(document).ready(function() {
	$(".img-responsive").click(imageSelected);
//	$("#image-selected").mouseenter(zoom);
});

function logged() {
	$('#login, #signup').addClass('hidden');

}

function notLogged() {
	$('#wishlist-button,#shopping-button').addClass('hidden');
}

function imageSelected() {
	var c = $(this).attr("src");
	$("#image-selected").attr("src", c);
}

function zoom(){
	console.log("aok")
	$("#image-selected").data('zoom-image', '../images/si1.jpg').elevateZoom({
		   responsive: true,
		   zoomType: "lens", 
		   containLensZoom: true
		}); 
}