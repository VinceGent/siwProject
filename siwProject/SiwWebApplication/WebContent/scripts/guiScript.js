


//grafica
function logUser(loginUsername)
{
	console.log("loggato");
	logged();
	var span=$('#user-dropdown > a > span');
	$('#user-dropdown > a').text("");
	$('#user-dropdown > a').append(span[0]);
	$('#user-dropdown > a').append("  "+loginUsername+"  ");
	$('#user-dropdown > a').append(span[1]);
	$('#user-dropdown > a').insertBefore();
	$('.cd-user-modal').removeClass('is-visible');	

}

//grafica
function buyNow() {
	$('#auctionSale').addClass('hidden');
}
//grafica
function auctionSale() {
	$('#buyNow').addClass('hidden');

}

//grafica
function logged() {
	$('#login, #signup').addClass('hidden');
	if ($('#wishlist-button,#shopping-button,#user-dropdown')
			.hasClass('hidden'))
		$('#wishlist-button,#shopping-button,#user-dropdown').removeClass(
				'hidden');

}

//grafica
function notLogged() {
	console.log("logout");
	$('#wishlist-button,#shopping-button,#user-dropdown').addClass('hidden');
	if ($('#login, #signup').hasClass('hidden')) {
		if (logoutUser()) {
			$('#login, #signup').removeClass('hidden');
			goToHomePage();
		}
	}

}

function inputUsernameRemoveClass() {
	inputUsername.removeClass("form-valid");
	inputUsername.removeClass("form-invalid");
}
function validationFormUsername(value) {
	if (value == "OK") {
		inputUsername.addClass("form-valid");
		inputUsername.removeClass("form-invalid");
	} else {
		inputUsername.addClass("form-invalid");
		inputUsername.removeClass("form-valid");
	}
}

function validationFormMail(value) {
	if (value == "OK") {
		inputMail.addClass("form-valid");
		inputMail.removeClass("form-invalid");
	} else {
		inputMail.addClass("form-invalid");
		inputMail.removeClass("form-valid");
	}
}

function inputMailRemoveClass() {
	inputMail.removeClass("form-valid");
	inputMail.removeClass("form-invalid");
}

function imageSelected() {
	var c = $(this).attr("src");
	$("#image-selected").attr("src", c);
}

function zoom() {

	$("#image-selected").data('zoom-image', '../images/si1.jpg').elevateZoom({
		responsive : true,
		zoomType : "lens",
		containLensZoom : true
	});
}