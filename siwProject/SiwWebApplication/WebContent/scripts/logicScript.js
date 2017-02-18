var inputMail;
var inputSearch;
var buttonSearch;
// eventi
$(document).ready(function() {
	$(".img-responsive").click(imageSelected);
	// $("#brand").click(goToItemSelected);
	$('.add-to-cart').click(buyItem);
	$('.offer-button').click(doOffer);
	$('#logout').click(notLogged);
	$('#input-login').click(validation);
	$('#signup-createUser').click(registration);
	$('#signup-postalcode').keypress(isNumericValue);
	$('#signup-telephone').keypress(isNumericValue);

	inputUsername = $('#signup-username');
	inputMail = $('#signup-email');
	inputUsername.keyup(checkUsername);
	inputMail.keyup(checkEmail);
	$('#createInsertion').click(createInsertion);
	$('#offer').keypress(isNumberKey);
	buttonSearch = $('#search-button');
	inputSearch = $('#search-input');
	buttonSearch.click(searchInsertion);
	$('.go-to-item').click(goToItemSelected);
	$('#gotoWishlist').click(wishlist);
	$('.add-to-wishlist').click(addToWishlist);
	$('.remove-from-wishlist').click(removeFromWishlist);
	$('.button-addToCart').click(addToCart);
	$('#go-to-shopping-cart').click(goToShoppingCart);
	$('.payButton').click(payItem);
	$('.remove-button-shoppingCart').click(removeShoppingCart);
	$('#pay-all-button').click(paymentPage);
});

function payAllCart() {

	$.ajax({
		url : "payAllCart",
		method : "get",
		success : function(data, textStatus, jqXHR) {

		},
		error : function() {
			console.log("ajax error");
		}
	});
}

function removeShoppingCart() {
	var id_order = $(this).attr('id');
	$.ajax({
		url : "removeFromCart",
		method : "post",
		data : {
			'id_order' : id_order
		},
		success : function(data, textStatus, jqXHR) {
			location.reload();

		},
		error : function() {
			console.log("ajax error");
		}
	});
}
function paymentPage() {
	document.location.href = "payment?id_item=" + 0;
}
function payItem() {

	var item = $(this).attr('id');
	var success = false;
console.log("payItem value        "+item);
	if (item == "pay-all-button")
		item = 0;
	$.ajax({
		url : "payItem",
		async : false,
		method : "post",
		data : {
			'id_item' : item
		},
		success : function(data, textStatus, jqXHR) {
			success = true;
		},
		error : function() {
			console.log("ajax error");
		}
	});
	if (item == 0) {
		payAllCart();
		goToShoppingCart();
		window.alert("Tutti gli oggetti nel carrello sono stati pagati");
		return;
	}

	if (success) {
		window.alert("oggetto acquistato");
		document.location.href = "item_Selected?id_item=" + item;

	}

}
function goToShoppingCart() {
	document.location.href = "shoppingCartPage";
}
function wishlist() {
	document.location.href = "loadWishlist";
}
function addToCart() {
	var id_item = $(this).attr('id');
	$.ajax({
		url : "addToCart",
		method : "post",
		data : {
			'id_item' : id_item
		},
		success : function(data) {
			var obj = $.parseJSON(data);
			if (obj["state"] == "OK") { // 
				window.alert("Oggetto aggiunto al carrello");
			} else {
				// window.alert("Devi eseguire il login per aggiungere un
				// oggetto al carrello");
				login_selected();
			}
		},
		error : function() {

		}

	});

}

function addToWishlist() {
	console.log("add to wishlist");
	$.ajax({
		url : "addWishlistItem",
		method : "post",
		data : {
			'id_item' : id_item
		},
		success : function(data, textStatus, jqXHR) {
			var obj = $.parseJSON(data);
			console.log(obj["state"]);
			if (obj["state"] == "OK") { // aggiunto alla wishlist
				$('.add-to-wishlist').addClass("hidden");
				$('.remove-from-wishlist').removeClass("hidden");
			} else {

				login_selected();
			}

		},
		error : function() {
			console.log("ajax error");
		}
	});

}

function inWishlist(value) {
	console.log(value);
	if (value) {
		console.log($('#removeFromWishlist'));
		$('#removeFromWishlist').removeClass("hidden");
	} else {
		$('#addToWishlist').removeClass("hidden");
	}
}

function removeFromWishlist() {
	console.log("remove");
	$.ajax({
		url : "removeWishlistItem",
		method : "post",
		data : {
			'id_item' : id_item
		},
		success : function(data, textStatus, jqXHR) {
			var obj = $.parseJSON(data);
			console.log(obj["state"]);
			if (obj["state"] == "OK") { // aggiunto alla wishlist
				$('.remove-from-wishlist').addClass("hidden");
				$('.add-to-wishlist').removeClass("hidden");

			} else {

				login_selected();
			}

		},
		error : function() {
			console.log("ajax error");
		}
	});
}

function searchInsertion() {
	if (inputSearch.val() == "")
		return;
	document.location.href = "searchInsertion?name=" + inputSearch.val();
}

function isNumericValue(evt) {

	var charCode = (evt.which) ? evt.which : event.keyCode
	if ((charCode < 48 || charCode > 57))
		return false;
	return true;
}
function createInsertion() {

	document.location.href = "insertionPage.jsp";
}

function isNumberKey(evt) {

	var charCode = (evt.which) ? evt.which : event.keyCode
	if (charCode > 31 && (charCode != 44 && (charCode < 48 || charCode > 57)))
		return false;
	return true;
}

// logica
function registration() {
	var newEmail = $('#signup-email'), newUser = $('#signup-username'), newPassword = $('#signup-password'), form = $('#reg_form');
	if (checkNewInfo()) {
		$.ajax({
			url : "addUser",
			method : "post",
			data : {
				'user' : newUser.val(),
				'email' : newEmail.val(),
				'password' : newPassword.val(),
				'name' : $('#signup-name').val(),
				'surname' : $('#signup-surname').val(),
				'address' : $('#signup-address').val(),
				'city' : $('#signup-city').val(),
				'postalcode' : $('#signup-postalcode').val(),
				'province' : $('#signup-province').val(),
				'telephone' : $('#signup-telephone').val(),
				'country' : $('#signup-country').val()
			},
			success : function(data, textStatus, jqXHR) {
				logUser(newUser.val());
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("ajax error");
			}

		});
	}
}

function checkNewInfo() {
	var check = true;
	if ($('#signup-name').val() == "") {
		failedField($('#signup-name'));
		check = false;
	} else
		successField($('#signup-name'));

	if ($('#signup-surname').val() == "") {
		failedField($('#signup-surname'));
		check = false;
	} else
		successField($('#signup-surname'));
	if ($('#signup-address').val() == "") {
		failedField($('#signup-address'));
		check = false;
	} else
		successField($('#signup-address'));
	if ($('#signup-province').val() == "") {
		failedField($('#signup-province'));
		check = false;
	} else
		successField($('#signup-province'));
	if ($('#signup-city').val() == "") {
		failedField($('#signup-city'));
		check = false;
	} else
		successField($('#signup-city'));
	if ($('#signup-postalcode').val() == "") {
		failedField($('#signup-postalcode'));
		check = false;
	} else
		successField($('#signup-postalcode'));
	if ($('#signup-telephone').val() == "") {
		failedField($('#signup-telephone'));
		check = false;
	} else
		successField($('#signup-telephone'));
	if ($('#signup-country').val() == "") {
		failedField($('#signup-country'));
		check = false;
	} else
		successField($('#signup-country'));

	return check;
}
// logica
function checkUsername() {
	console.log("checkusername");
	if (inputUsername.val() != "") {
		$.ajax({
			url : "validateUsername",
			method : "post",
			data : {
				'newUser' : inputUsername.val()
			},
			success : function(data, textStatus, jqXHR) {
				console.log(data);
				validationFormUsername(data);

			},
			error : function() {
				console.log("ajax error");
			}
		});
	} else {
		removeClass(inputUsername);
	}
}

// logica
function checkEmail() {

	if (inputMail.val() != "") {
		if (emailRegex(inputMail.val())) {
			$.ajax({
				url : "validateEmail",
				method : "post",
				data : {
					'newEmail' : inputMail.val()
				},
				success : function(data, textStatus, jqXHR) {
					validationFormMail(data);
				},
				error : function() {
					console.log("ajax error");
				}
			});
		} else {
			validationFormMail("OK");
		}
	} else {
		removeClass(inputMail);
	}
}
// logica
function emailRegex(email) {
	var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}

// logica
function buyItem() {
	if (amount <= 0) {
		return;
	}
	var value = false;
	$.ajax({
		url : "addToCartAndPay",
		method : "post",
		async : false,
		data : {
			'id_item' : id_item
		},
		success : function(data) {
			var obj = $.parseJSON(data);

			if (obj["state"] == "OK") { // vai al pagamento
				value = true;
			} else {

				login_selected();
			}
		},
		error : function() {

		}

	});

	if (value) {
		document.location.href = "payment?id_item=" + id_item;
	}

	//	
	// $.ajax({
	// url : "buyNow",
	// method : "post",
	// data : {
	// 'id' : id_item
	// },
	// success : function(data) {
	// buyNowInformation();
	// },
	// error : function() {
	//
	// }
	//
	// });

}
// logica
function doOffer() {
if(!loggato)
	{
	
	login_selected();
	 $('#offer').val("");
	return;
	}
	var ok = true;
	var offer = $('#offer');
	if (offer.val() <= price_insertion) {
		failedField(offer);
		ok = false;
	} else {
		successField(offer);
	}
	if (ok) {
		sendOffert(id_item, offer.val());
	}

}

function sendOffert(id_item, offer) {

	$.ajax({
		url : 'auctionSales',
		method : 'post',
		data : {
			'id_item' : id_item,
			'offer' : offer
		},
		success : function() {
			updateOffer();

		},
		error : function() {
			console.log("error");
		}

	});

}

// logica
function goToItemSelected() {
	var id_item = $(this).attr('id');

	document.location.href = "item_Selected?id_item=" + id_item;
}

// logica
function goToHomePage() {
	document.location.href = "index.jsp";

}

// logica
function logoutUser() {
	return $.ajax({
		url : 'logoutUser',
		method : 'get',
		success : function() {
			location.reload();
			return true;

		},
		error : function() {
			return false;
		}

	});

}

// logica
function validation() {
	var loginUsername = $('#signin-username'), loginPass = $('#signin-password');

	$.ajax({
		url : "loginUser",
		method : "post",
		data : {
			'username' : loginUsername.val(),
			'password' : loginPass.val()
		},
		success : function(data) {
			var obj = $.parseJSON(data);
			if (obj["state"] == "OK") {
				logUser(loginUsername.val());
				$('#error-custom').addClass('hidden');
				location.reload();
			} else {
				notLogged();
				$('#error-custom').removeClass('hidden');

			}

		},
		error : function() {

		}

	});

}
