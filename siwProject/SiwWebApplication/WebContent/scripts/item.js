$(document).ready(function() {
	if (sales_type == "asta") {
		window.setInterval(updateOffer, 5000);
		updateOffer();
	} else{
		window.setInterval(buyNowInformation, 5000);
		buyNowInformation();
	}

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
			console.log("update offerrrrrrrrrrrrrrrrr");
			var obj = $.parseJSON(data);

			$('#current-offer').text(obj["offer"] + "  $");
			price_insertion = obj["offer"];
			if (obj["yourOffer"] == "true") {
				$('#best-offer').removeClass("hidden");
			} else {
				$('#best-offer').addClass("hidden");
			}
			console.log($('#quantity').val());
			$('#quantity').text(obj["quantity"]);
		},
		error : function() {
			console.log("errroreeeeeeeeeeeeeeeeee");
		}

	});

}






function buyNowInformation() {

	$.ajax({
		url : "buyNowInformation",
		method : "post",
		data : {
			"id_item" : id_item
		},
		success : function(data) {
			if (data.length == 0)
				return;
			var obj = $.parseJSON(data);
			 $('#quantity').text(obj["quantity"]);
		},
		error : function() {
			console.log("errroreeeeeeeeeeeeeeeeee");
		}

	});

}
