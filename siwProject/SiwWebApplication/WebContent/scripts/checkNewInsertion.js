var category;
var price;
var title;
var description;
var sellerType;
var sendInsertion;
var amount;
var expirationDate;
$(document).ready(function() {
	category = $('#category');
	price = $('#price');
	title = $('#title');
	amount = $('#amount');
	description = $('#description');
	sellerType = $('#seller_type');
	sendInsertion = $('#send_insertion');
	price.keypress(isNumberKey);
	amount.keypress(isNumberKey);
	sendInsertion.click(checkFields);
	expirationDate=$('#date');
	$('#datePicker').datepicker({
		format : 'dd/mm/yyyy',
		autoclose : true,
		startDate : '+1d'
	}).on('changeDate', function(e) {

	});

});

// $('#datePicker').datepicker({
// format : 'mm/dd/yyyy',
// autoclose:true,
// startDate:'+1d'
// }).on(
// 'changeDate',
// function(e) {
//
//           
// });

function isNumberKey(evt) {
	var re = /(\d+,d+)|(\d*)/;
	var charCode = (evt.which) ? evt.which : event.keyCode
	if (charCode > 31 && (charCode != 44 && (charCode < 48 || charCode > 57)))
		return false;
	return true;
}

function checkFields() {
	var ok = true;
	// console.log(category.find(":selected").val());
	// console.log(title.val());
	var str = /(\w+ *)/;

	if (str.test(title.val())) {
		addClass(title, "form-valid");
		removeClass(title, "form-invalid")
	} else {
		addClass(title, "form-invalid");
		removeClass(title, "form-valid");
		ok = false;
	}
	if (str.test(description.val())) {
		addClass(description, "form-valid");
		removeClass(description, "form-invalid")
	} else {
		addClass(description, "form-invalid");
		removeClass(description, "form-valid");
		ok = false;
	}
	var number = /^\d+(\.\d+)?$/;
	if (number.test(price.val())) {
		addClass(price, "form-valid");
		removeClass(price, "form-invalid")
	} else {
		removeClass(price, "form-valid");
		addClass(price, "form-invalid");
		ok = false;
	}
	console.log(amount.val());
	number = /^\d+$/;
	if (number.test(amount.val())) {
		addClass(amount, "form-valid");
		removeClass(amount, "form-invalid")

	} else {
		removeClass(amount, "form-valid");
		addClass(amount, "form-invalid");
		ok = false;

	}
	console.log()
	if(!expirationDate.val()=="")
	{
		addClass(expirationDate, "form-valid");
		removeClass(expirationDate, "form-invalid")

	} else {
		removeClass(expirationDate, "form-valid");
		addClass(expirationDate, "form-invalid");
		ok = false;

	}

		
	console.log(expirationDate.val());
	if (ok) {
		addNewInsertion();
	}

}
function addNewInsertion() {

	console.log($('#datePicker').datepicker("getDate"));
	$.ajax({

		url : "newInsertion",
		method : "post",
		data : {
			'category' : category.find(":selected").val(),
			'title' : title.val(),
			'price' : price.val(),
			'description' : description.val(),
			'seller_type' : sellerType.find(":selected").val(),
			'amount' : amount.val(),
			'expiration_date':expirationDate.val()
			
			

		},
		success : function(data, textStatus, jqXHR) {

		},
		error : function() {
			console.log("ajax error");
		}

	});

}
