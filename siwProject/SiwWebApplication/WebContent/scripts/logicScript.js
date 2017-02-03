var input;
var inputMail;
//eventi
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
});
// logica
function registration() {
	var newEmail = $('#signup-email'), newUser = $('#signup-username'), newPassword = $('#signup-password'), form = $('#reg_form');
	console.log("nuovo utente ", newUser.val());
	console.log("nuova email ", newEmail.val());
	console.log("nuova password ", newPassword.val());
	// se i dati inseriti sono validi registra il nuovo utente
	$.ajax({
		url : "addUser",
		method : "post",
		data : {
			'user' : newUser.val(),
			'email' : newEmail.val(),
			'password' : newPassword.val()
		},
		success : function(data, textStatus, jqXHR) {
			logUser(newUser);
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
			url : "Validator",
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
		inputUsernameRemoveClass();
	}
}

// logica
function checkEmail() {

	console.log("entro");
	if (inputMail.val() != "") {
		if (emailRegex(inputMail.val())) {
			$.ajax({
				url : "Validator",
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
			validationFormMail("NO");
		}
	} else {
		inputMailRemoveClass();
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
		method : "get",
		data : {
			'id' : id_item
		},
		success : function() {
			console.log("successo");

		},
		error : function() {

		}

	});

}
// logica
function doOffer() {

}

// logica
function goToItemSelected() {
	id_item = 1;
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

	console.log(loginUsername.val());
	console.log(loginPass.val());

	$.ajax({
		url : "loginUser",
		method : "post",
		data : {
			'username' : loginUsername.val(),
			'password' : loginPass.val()
		},
		success : function(data) {
			console.log("success data ", data);
			var obj = $.parseJSON(data);
			if (obj["state"] == "ok") {
				logUser(loginUsername.val());
			} else
				notLogged();

		},
		error : function() {

		}

	});

}
