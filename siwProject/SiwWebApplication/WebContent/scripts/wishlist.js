$(document).ready(function() {
	$('.rm-wishlist').click(function(elem){
		var id = $(elem.target).attr("id");
		console.log(id);
		var reference = $(elem.target).data("href");
		console.log(reference);
		$.ajax({
			url: reference,
			method:"post",
			success:function(){
				$(elem.target).closest("#products").remove();				
			},
			error : function(){
				console.log("remove wishlist error ajax");
			}
			
		});
	});
});
