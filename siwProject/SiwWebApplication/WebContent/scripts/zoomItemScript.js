/**
 * 
 */


var zoomify = function() {

	$("#image-selected").mlens({
		imgSrc: $("#image-selected").attr("data-big"),
		imgSrc2x: $("#image-selected").attr("data-big2x"),
		lensShape: "square",
		lensSize: ["40%","60%"],
		borderSize: 4,
		borderColor: "#fff",
		borderRadius: 0,
		imgOverlay: $("#image-selected").attr("data-overlay"),
		overlayAdapt: true,
		zoomLevel: 1,
		responsive: true
	});
};


$(document).ready(function() {
	zoomify();
//	$('#image-selected').mouseover(zoomify);
	
});