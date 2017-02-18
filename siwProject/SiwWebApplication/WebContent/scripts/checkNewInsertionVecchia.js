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
	sellerType.change(selectSellerType);
	sendInsertion = $('#send_insertion');
	price.keypress(isNumberKey);
	amount.keypress(isNumberKey);
	sendInsertion.click(checkFields);
	expirationDate = $('#date');
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

function selectSellerType() {
	console.log(sellerType.find(':selected').val());
	// if(sellerType.find(':selected').val()=="asta")
	$('#row-quantity').toggleClass("hidden");
	if ($('#row-quantity').hasClass("hidden"))
		amount.val(0);
	else
		amount.val("");

}

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
		successField(title);
	} else {
		failedField(title);
		ok = false;
	}
	if (str.test(description.val())) {
		successField(title);
	} else {
		failedField(title);
		ok = false;
	}
	var number = /^\d+(\.\d+)?$/;
	if (number.test(price.val())) {
		successField(title);
	} else {
		failedField(title);
		ok = false;
	}
	console.log(amount.val());
	number = /^\d+$/;
	if (number.test(amount.val())) {
		successField(title);
	} else {
		failedField(title);
		ok = false;

	}
	console.log()
	if (!expirationDate.val() == "") {
		successField(title);

	} else {
		failedField(title);
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
			'expiration_date' : expirationDate.val()

		},
		success : function(data, textStatus, jqXHR) {

		},
		error : function() {
			console.log("ajax error");
		}

	});

}
