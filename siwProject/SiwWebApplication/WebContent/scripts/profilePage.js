var overview, overviewDiv;
var settings, settingsDiv;
var order, orderDiv;

var popover;
$(document).ready(function() {
	overview = $('#overview-button');
	overviewDiv = $('#overview-div');
	overview.click(overviewClick);
	settings = $('#settings-button');
	settingsDiv = $('#settings-div');
	settings.click(settingsClick);
	order = $('#order-button');
	orderDiv = $('#order-div');
	order.click(orderClick);
	
});

function overviewClick(e) {
	console.log("click on overview");
	hideAll();
	overviewDiv.toggleClass('hidden');
	overview.addClass('active');
	e.preventDefault();
}

function settingsClick(e) {
	console.log("click on settings");
	hideAll();
	settings.addClass('active');
	settingsDiv.toggleClass('hidden');
	e.preventDefault();
}

function orderClick(e) {
	console.log("click on order");
	hideAll();
	order.addClass('active');
	orderDiv.toggleClass('hidden');
	e.preventDefault();
}

function hideAll(){
	
	overviewDiv.addClass('hidden');
	overview.removeClass('active');
	settingsDiv.addClass('hidden');
	settings.removeClass('active');
	orderDiv.addClass('hidden');
	order.removeClass('active');
}
