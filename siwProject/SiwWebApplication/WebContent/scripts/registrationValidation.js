/*$(document).ready(new function() {

});*/

function validation() {
	var loginForm = $('#login-form'), loginemail = $('#signin-email'), loginPass = $('#signin-password');

	console.log(loginemail.val());
	console.log(loginPass.val());

	// controlla nome utente e password
}

function registration() {
	var newEmail = $('#signup-email'), newUser = $('#signup-username'), newPassword = $('#signup-password'), form = $('#reg_form');

	// se i dati inseriti sono validi registra il nuovo utente
	$.ajax({
		url : "AddUser",
		method : "POST",
		data : {
			'user' : newUser.val(),
			'email' : newEmail.val(),
			'password' : newPassword.val()
		},
		success : function(data, textStatus, jqXHR) {
			console.log("ajax success");
			console.log(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("ajax error");
		}

	});

	/*
	 * console.log(newEmail.val()); console.log(newUser.val());
	 * console.log(newPassword.val()); console.log(form.val());
	 * console.log(form.attr('action')); console.log(form.attr('method'));
	 */
	// form.submit();
}

function checkUsername() {
	console.log("execute check username");
	console.log($('#signup-username').val());

	$.ajax({
		url : "Validator",
		method : "POST",
		data : {
			'newUser' : $('#signup-username').val()
		},
		success : function(data, textStatus, jqXHR) {
			console.log("check username success");
			console.log(data);
			console.log($('#signup-username').next());
			$('#signup-username').next().text(data);
//			$('#signup-username').next().toggleClass('is-visible');
//			$('#signup-username').next().css('background-color','green');
		},
		error : function() {

		}
	});
}

function checkEmail() {
	$.ajax({
		url : "Validator",
		method : 'post',
		data : newUser = $('#signup-email'),
		success : function() {

		},
		error : function() {

		}
	});
}
