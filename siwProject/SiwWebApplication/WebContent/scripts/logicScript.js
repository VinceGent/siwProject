var input;
var inputMail;
var inputSearch;
var buttonSearch;
// eventi
$(document).ready(function() {
	$(".img-responsive").click(imageSelected);
	// $("#brand").click(goToItemSelected);
	$('#buyNow').click(buyItem);
	$('#auctionSale').click(doOffer);
	$('#logout').click(notLogged);
	$('#input-login').click(validation);
	$('#signup-createUser').click(registration);
	inputUsername = $('#signup-username');
	inputMail = $('#signup-email');
	inputUsername.keyup(checkUsername);
	inputMail.keyup(checkEmail);
	$('#link-prova').click(linkProva);
	$('#offer').keypress(isNumberKey);
	buttonSearch = $('#search-button');
	inputSearch = $('#search-input');
	buttonSearch.click(searchInsertion);
	$('.go-to-item').click(goToItemSelected);

});

function searchInsertion() {
	if(inputSearch.val()=="")
		return;
	document.location.href = "searchInsertion?name="+inputSearch.val();
}

function linkProva() {

	document.location.href = "insertionPage.jsp";
}

function isNumberKey(evt) {
	var re = /(\d+,d+)|(\d*)/;
	var charCode = (evt.which) ? evt.which : event.keyCode
	if (charCode > 31 && (charCode != 44 && (charCode < 48 || charCode > 57)))
		return false;
	return true;
}

// logica
function registration() {
	var newEmail = $('#signup-email'), newUser = $('#signup-username'), newPassword = $('#signup-password'), form = $('#reg_form');
	$.ajax({
		url : "addUser",
		method : "post",
		data : {
			'user' : newUser.val(),
			'email' : newEmail.val(),
			'password' : newPassword.val()
		},
		success : function(data, textStatus, jqXHR) {
			logUser(newUser.val());
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("ajax error");
		}

	});
}
// logica
function checkUsername() {

	if (inputUsername.val() != "") {
		$.ajax({
			url : "validateUsername",
			method : "post",
			data : {
				'newUser' : inputUsername.val()
			},
			success : function(data, textStatus, jqXHR) {
				validationFormUsername(data)

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
	$.ajax({
		url : "buyNow",
		method : "post",
		data : {
			'id' : id_item
		},
		success : function(data) {
			buyNowInformation();
		},
		error : function() {

		}

	});

}
// logica
function doOffer() {

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
	var id_item=$(this).attr('id');


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
			if (obj["state"] == "ok") {
				logUser(loginUsername.val());
				$('#error-custom').addClass('hidden');
			} else {
				notLogged();
				$('#error-custom').removeClass('hidden');
			}

		},
		error : function() {

		}

	});

}
