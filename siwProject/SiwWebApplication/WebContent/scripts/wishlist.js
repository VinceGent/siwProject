$(document).ready(function() {
	$('.rm-wishlist').click(function(elem){
		var id = $(elem.target).attr("id");
		
		var reference = $(elem.target).data("href");
	
		$.ajax({
			url: reference,
			method:"post",
			success:function(){
				$(elem.target).closest("#products").remove();	
				location.reload();
			},
			error : function(){
			}
			
		});
	});
});
