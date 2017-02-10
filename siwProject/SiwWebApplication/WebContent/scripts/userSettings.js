var usernamefield, namefield, surnamefield, emailfield;
var saveButton, clearButton;
var init_email, init_username;
var bool_email = true, bool_username = true;
var oldPassword, newPassword,repeatNewPassword;
$(document).ready(function() {
	console.log("user settings on ready");
	usernamefield = $('#username-field');
	usernamefield.keyup(checkUsernameField);
	namefield = $('#name-field');
	surnamefield = $('#surname-field');
	emailfield = $('#email-field');
	emailfield.val("test");
	emailfield.keyup(checkEmailField);
	saveButton = $('#save-settings');
	saveButton.click(save);

	clearButton = $('#clear-settings');
	clearButton.click(clear);
	setValue();
	
	oldPassword = $('#old-password-field');
	newPassword = $('#new-password-field');
	repeatNewPassword = $('#repeat-new-password-field');
});

function setValue() {
	$.ajax({
		url : "userinfo",
		method : "post",
		success : function(data) {
			var json = $.parseJSON(data);
			console.log("ajax success setvalue");
			console.log("ololololo ", json["username"]);
			usernamefield.val(json["username"]);
			init_username = json["username"];
			namefield.val(json["name"]);
			emailfield.val(json["email"]);
			init_email = json["email"];
			surnamefield.val(json["surname"]);
		},
		error : function() {
			console.log("ajax error setvalue");
		}
	});
}

function save() {
	
	changeInfo();
	changePassword();
}

function clear() {

}

function checkUsernameField() {
	console.log("called check username field");
	console.log(usernamefield.val() != ""
			&& usernamefield.val() != init_username);
	if (usernamefield.val() != "" && usernamefield.val() != init_username) {
		$.ajax({
			url : "validateUsername",
			method : "post",
			data : {
				'newUser' : usernamefield.val()
			},
			success : function(data, textStatus, jqXHR) {
				if (data == "NO") {
					usernamefield.addClass("form-valid");
					usernamefield.removeClass("form-invalid");
					bool_username = true;
				} else {
					usernamefield.addClass("form-invalid");
					usernamefield.removeClass("form-valid");
					bool_username = false;
				}
			},
			error : function() {
				console.log("ajax error");
			}
		});
	} else {
		usernamefield.removeClass("form-invalid");
		usernamefield.removeClass("form-valid");
	}
}
function checkEmailField() {
	if (emailfield.val() != "" && emailRegex(emailfield.val())
			&& emailfield.val() != init_email) {
		$.ajax({
			url : "validateEmail",
			method : "post",
			data : {
				'newEmail' : emailfield.val()
			},
			success : function(data, textStatus, jqXHR) {
				if (data == "NO") {
					emailfield.addClass("form-valid");
					emailfield.removeClass("form-invalid");
					bool_email = true;
				} else {
					emailfield.addClass("form-invalid");
					emailfield.removeClass("form-valid");
					bool_eamail = false;
				}
			},
			error : function() {
				console.log("ajax error");
			}
		});
	} else {
		emailfield.removeClass("form-invalid");
		emailfield.removeClass("form-valid");
	}
}

function changePassword(){
//	console.log("vecchia password " + oldPassword.val());
//	console.log("nuova password " + newPassword.val());
//	console.log("ripeti nuova password " + repeatNewPassword.val());
	
	var check = false;
	if(oldPassword.val() != "" && newPassword.val() != "" && repeatNewPassword.val() != ""){
		$.ajax({
			url : "getUserPassword",
			method : "post",
			async : false,
			data: {"oldpass":oldPassword.val()},
			success: function(data) {
				console.log("data ",data);
				if(data == "OK"){
					console.log("modifico check con true");
					check = true;
				}
			},
			error: function() {
				console.log("ajax getpassword error");
			}
		});
		console.log("after check ", check);
		if(check && newPassword.val() != oldPassword.val() && newPassword.val() == repeatNewPassword.val()){
			$.ajax({
				url : "modifyPassword",
				async : false,
				method : "post",
				data : {
					"newPassword" : newPassword.val()
				}, 
				success : function() {
					console.log("password changed");
				}
			});
		}
	}
}
function changeInfo() {

	console.log("hai inserito username " + usernamefield.val());
	console.log("hai inserito name " + namefield.val());
	console.log("hai inserito surname " + surnamefield.val());
	console.log("hai inserito email " + emailfield.val());

	if (bool_username && bool_email && namefield.val() != ""
			&& surnamefield.val() != "") {
		$.ajax({
			url : "modifyUser",
			method:"post",
			data : {
				"username" : usernamefield.val(),
				"name":namefield.val(),
				"email" : emailfield.val(),
				"surname" : surnamefield.val()
			},
			success: function() {
				console.log("success ajax modify");
				document.location.href = "userProfile.jsp";
			},
			error: function() {
				console.log("error ajax modify");
			}
		});
	}
}