$(document).ready(function() {

	if (sales_type == "asta")
		window.setInterval(updateOffer, 5000);
	updateOffer();

});

function updateOffer() {
	$.ajax({
		url : "update_item",
		method : "post",
		data : {
			"id_item" : id_item
		},
		success : function(data) {
			if (data.length == 0)
				return;

			var obj = $.parseJSON(data);
			console.log(obj);
			$('#current-offer').text(obj["offer"] + "  $");
			if (obj["yourOffer"] == "true") {
				$('#best-offer').removeClass("hidden");
			} else {
				$('#best-offer').addClass("hidden");
			}
		},
		error : function() {
			console.log("errroreeeeeeeeeeeeeeeeee");
		}

	});

}