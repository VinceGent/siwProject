var category;
var price;
var title;
var description;
var sellerType;
var sendInsertion;
var amount, amountDiv;
var expirationDate, dateFormDiv;
var ID_Insertion;
$(document).ready(function() {
	category = $('#category');
//	price = $('#price');
	price = $('#insertion-price');
//	title = $('#title');
	title = $('#insertion-title');
//	amount = $('#amount');
	amount = $('#insertion-quantity');
	amountDiv = $('#amountDiv');
//	description = $('#description');
	description = $('#insertion-description');
//	sellerType = $('#seller_type');
	sellerType = $('#sale_type');
	sellerType.change(selectSellerType);
	sendInsertion = $('#send_insertion');
	price.keypress(isNumberKey);
	amount.keypress(isNumberKey);
	sendInsertion.click(checkFields);
//	expirationDate = $('#date');
	expirationDate = $('#expiration-date');
	dateFormDiv = $('#date-picker');
	$('#date-picker').datepicker({
		format : 'dd/mm/yyyy',
		autoclose : true,
		startDate : '+1d'
	}).on('changeDate', function(e) {

	});
	
	$('#next-button').click(goToImageUploader);
	selectSellerType();
	$('#js-upload-submit').click(unafunzione);
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
function unafunzione() {
	
}

function selectSellerType() {
	console.log(sellerType.find(':selected').val());
	 if(sellerType.find(':selected').val()=="Auction"){
		 dateFormDiv.removeClass('hidden');
		 amountDiv.addClass('hidden');
		 amount.val(0);
		 expirationDate.val("");
	 }else{
		 dateFormDiv.addClass('hidden');
		 amountDiv.removeClass('hidden');
		 amount.val("");
		 expirationDate.val("01/01/1970");
	 }
/*	$('#row-quantity').toggleClass("hidden");
	if ($('#row-quantity').hasClass("hidden"))
		amount.val(0);
	else
		amount.val("");
*/
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
		successField(description);
	} else {
		failedField(description);
		ok = false;
	}
	var number = /^\d+(\.\d+)?$/;
	if (number.test(price.val())) {
		successField(price);
	} else {
		failedField(price);
		ok = false;
	}
	number = /^\d+$/;
	if (number.test(amount.val())) {
		successField(amount);
	} else {
		failedField(amount);
		ok = false;

	}
//	console.log()
	if (!expirationDate.val() == "") {
		successField(expirationDate);

	} else {
		failedField(expirationDate);
		ok = false;

	}
	return ok;
/*	console.log(expirationDate.val());
	if (ok) {
		addNewInsertion();
	}*/

}
var tmp_id;
function addNewInsertion() {

	$.ajax({

		url : "newInsertion",
		method : "post",
		async : false,
		data : {
			'category' : category.find(":selected").val(),
			'title' : title.val(),
			'price' : price.val(),
			'description' : description.val(),
			'seller_type' : sellerType.find(":selected").attr("id"),
			'amount' : amount.val(),
			'expiration_date' : expirationDate.val()

		},
		success : function(data, textStatus, jqXHR) {
			console.log("dopo che inserisco ricevo ",data);
			tmp_id = data;
		},
		error : function() {
			console.log("ajax error");
		}

	});

}

function goToImageUploader() {
	console.log("title is ",title.val());
	console.log("price is ",price.val());
	console.log("amount is ",amount.val());
	console.log("description is ",description.val());
	console.log("date is ",expirationDate.val());
	console.log("check fields is ",checkFields());
	
	if(checkFields()){
		addNewInsertion();
		console.log("tmp id dopo ajax ", tmp_id);
		$('#input_id_insertion').attr("value" , tmp_id);
		$('#insertion-form').addClass("hidden");
		$('#upload-image').removeClass("hidden");
	}else{
		console.log("non inserisco");
	}
	
}
