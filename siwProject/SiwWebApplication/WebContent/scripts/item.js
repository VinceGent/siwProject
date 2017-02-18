$(document).ready(function() {
	if (sales_type == "asta") {
		window.setInterval(updateOffer, 5000);
		updateOffer();
	} else {
		window.setInterval(buyNowInformation, 5000);
		buyNowInformation();
	}

});

function loadPage(item, sale_type, loggato) {
	console.log(sale_type, item);
	switch (sale_type) {
	case 'compraora':
		compraora();
		break;

	case 'asta':
		asta();
		break;
	}
	if (loggato) {
		$.ajax({
			url : "isWishlistItem",
			data : {
				'id_item' : item
			},
			success : function(data) {
				console.log(data);
				if (data == 'true') {
					$('.remove-from-wishlist').removeClass('hidden');
				} else
					$('.add-to-wishlist').removeClass('hidden');
			}
		});
	}
}

function compraora() {
	console.log("compra ora function");
	$('.add-to-cart').removeClass('hidden');
}

function asta() {
	console.log("asta function");
	$('.auction-offer').removeClass('hidden');
}

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

			$('#price > span').first().text(obj["offer"]);
			price_insertion = obj["offer"];
			if (obj["yourOffer"] == "true") {
				$('#best-offer').removeClass("hidden");
			} else {
				$('#best-offer').addClass("hidden");
			}
			console.log($('#quantity').val());
			$('#quantity > span').first().text(obj["quantity"]);
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
			$('#quantity > span').first().text(obj["quantity"]);
		},
		error : function() {
			console.log("errroreeeeeeeeeeeeeeeeee");
		}

	});

}
