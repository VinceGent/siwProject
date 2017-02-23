//grafica
function logUser(loginUsername) {

	logged();
	var span = $('#user-dropdown > a > span');
	$('#user-dropdown > a').text("");
	$('#user-dropdown > a').append(span[0]);
	$('#user-dropdown > a').append("  " + loginUsername + "  ");
	$('#user-dropdown > a').append(span[1]);
	$('#user-dropdown > a').insertBefore();
	$('.cd-user-modal').removeClass('is-visible');

}

// grafica
function buyNow() {
	$('#auctionSale,.current-offer-div').addClass('hidden');

}
// grafica
function auctionSale(priceInsertion) {
	$('.quantity-div, .price-div').addClass('hidden');
	$('#buyNow').addClass('hidden');
	priceInsertion++;
	$('#offer').attr("min", priceInsertion);

}

// grafica
function logged() {
	$('#login, #signup, #login-google').addClass('hidden');
	if ($('#wishlist-button,#shopping-button,#user-dropdown')
			.hasClass('hidden')) {
		$('#wishlist-button,#shopping-button,#user-dropdown').removeClass(
				'hidden');
	
	}
	
}

// grafica
function notLogged() {
	$('#wishlist-button,#shopping-button,#user-dropdown').addClass('hidden');
	if ($('#login, #signup, #login-google').hasClass('hidden')) {
		if (logoutUser()) {
			$('#login, #signup, #login-google').removeClass('hidden');
			var index = /index/;
			var search = /searchInsertion/;
			var item = /item_Selected/;
			var path = window.location.pathname;
			if (!index.test(path) && !search.test(path) && !item.test(path)) {
				goToHomePage();
			} 
		}
	}

}

function removeClass(field) {
	field.removeClass("form-valid");
	field.removeClass("form-invalid");
}
function validationFormUsername(value) {
	if (value == "NO") {
		successField(inputUsername);
	} else {
		failedField(inputUsername);
	}
}

function validationFormMail(value) {
	if (value == "NO") {
		successField(inputMail);
	} else {
		failedField(inputMail);
	}
}

function imageSelected() {
	var c = $(this).attr("src");
	$("#image-selected").attr("src", c);
	zoomify();
}
function failedField(field) {
	field.removeClass("form-valid");
	field.addClass("form-invalid");
}
function successField(field) {
	field.removeClass("form-invalid");
	field.addClass("form-valid");
}

function zoom() {

	$("#image-selected").data('zoom-image', '../images/si1.jpg').elevateZoom({
		responsive : true,
		zoomType : "lens",
		containLensZoom : true
	});
}