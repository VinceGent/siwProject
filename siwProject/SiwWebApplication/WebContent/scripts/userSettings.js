var usernamefield, namefield, surnamefield, emailfield;
var saveButton, clearButton;
var init_email, init_username;
var bool_email = true, bool_username = true;
var oldPassword, newPassword, repeatNewPassword;
var address, telephone, city, province, postal_code, country;

$(document).ready(function() {
	usernamefield = $('#username-field');
	usernamefield.keyup(checkUsernameField);
	namefield = $('#name-field');
	surnamefield = $('#surname-field');
	emailfield = $('#email-field');
	emailfield.keyup(checkEmailField);
	saveButton = $('#save-settings');
	saveButton.click(save);
	clearButton = $('#clear-settings');
	clearButton.click(clear);
	address = $('#address-field');
	telephone = $('#telephone-field');
	city = $('#city-field');
	province = $('#province-field');
	postal_code = $('#postal_code-field');
	country = $('#country-field');
	telephone.keypress(isNumericValue);
	postal_code.keypress(isNumericValue);
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
			usernamefield.val(json["username"]);
			init_username = json["username"];
			namefield.val(json["name"]);
			emailfield.val(json["email"]);
			init_email = json["email"];
			surnamefield.val(json["surname"]);
			address.val(json["address"]);
			telephone.val(json["telephone"]);
			city.val(json["city"]);
			province.val(json["province"]);
			postal_code.val(json["postal_code"]);
			country.val(json["country"]);
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

function checkFieldsEmpty() {
	var check = true;
	if (emailfield.val() == "") {
		check = false
		failedField(emailfield);
	} else {
		successField(emailfield);
	}
	if (usernamefield.val() == "") {
		check = false
		failedField(usernamefield);
	} else {
		successField(usernamefield);
	}
	if (namefield.val() == "") {
		check = false
		failedField(namefield);
	} else {
		successField(namefield);
	}
	if (surnamefield.val() == "") {
		check = false
		failedField(surnamefield);
	} else {
		successField(surnamefield);
	}
	if (address.val() == "") {
		check = false
		failedField(address);
	} else {
		successField(address);
	}
	if (telephone.val() == "") {
		check = false
		failedField(telephone);
	} else {
		successField(telephone);
	}
	if (city.val() == "") {
		check = false
		failedField(city);
	} else {
		successField(city);
	}
	if (province.val() == "") {
		check = false
		failedField(province);
	} else {
		successField(province);
	}
	if (postal_code.val() == "") {
		check = false
		failedField(postal_code);
	} else {
		successField(postal_code);
	}
	if (country.val() == "") {
		check = false
		failedField(country);
	} else {
		successField(country);
	}
	return check;
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

function changePassword() {
	// console.log("vecchia password " + oldPassword.val());
	// console.log("nuova password " + newPassword.val());
	// console.log("ripeti nuova password " + repeatNewPassword.val());

	var check = false;
	if (oldPassword.val() != "" && newPassword.val() != ""
			&& repeatNewPassword.val() != "") {
		$.ajax({
			url : "getUserPassword",
			method : "post",
			async : false,
			data : {
				"oldpass" : oldPassword.val()
			},
			success : function(data) {
				console.log("data ", data);
				if (data == "OK") {
					console.log("modifico check con true");
					check = true;
				}
			},
			error : function() {
				console.log("ajax getpassword error");
			}
		});
		console.log("after check ", check);
		if (check && newPassword.val() != oldPassword.val()
				&& newPassword.val() == repeatNewPassword.val()) {
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

	if (bool_username && bool_email && checkFieldsEmpty()) {
		$.ajax({
			url : "modifyUser",
			method : "post",
			data : {
				"username" : usernamefield.val(),
				"name" : namefield.val(),
				"email" : emailfield.val(),
				"surname" : surnamefield.val(),
				"address" : address.val(),
				"telephone" : telephone.val(),
				"city" : city.val(),
				"province" : province.val(),
				"postal_code" : postal_code.val(),
				"country" : country.val()
			},
			success : function() {
				console.log("success ajax modify");
				document.location.href = "userProfile.jsp";
			},
			error : function() {
				console.log("error ajax modify");
			}
		});
	}
}