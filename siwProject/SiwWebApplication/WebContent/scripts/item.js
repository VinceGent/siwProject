
$(document).ready(function() {
	if (sales_type == "asta") {
		window.setInterval(updateOffer, 5000);
		updateOffer();
		makeTimer();
		window.setInterval(makeTimer,1000);
	} else {
		window.setInterval(buyNowInformation, 5000);
		buyNowInformation();
	}

});

function initializeClock(id){

	  var clock = document.getElementById(id);
	var timeinterval = setInterval(function(){
	    var t = getTimeRemaining();
	    clock.innerHTML = 'days: ' + t.days + '<br>' +
	                      'hours: '+ t.hours + '<br>' +
	                      'minutes: ' + t.minutes + '<br>' +
	                      'seconds: ' + t.seconds;
	    if(t.total<=0){
	      clearInterval(timeinterval);
	    }
	  },1000);
	}
function getTimeRemaining(){
	  var t = Date.parse(endtime) - Date.parse(new Date());
	  var seconds = Math.floor( (t/1000) % 60 );
	  var minutes = Math.floor( (t/1000/60) % 60 );
	  var hours = Math.floor( (t/(1000*60*60)) % 24 );
	  var days = Math.floor( t/(1000*60*60*24) );
	  return {
	    'total': t,
	    'days': days,
	    'hours': hours,
	    'minutes': minutes,
	    'seconds': seconds
	  };
	}


function makeTimer() {
// var endTime = new Date("August 10, 2017 12:00:00 PDT");
	var endTime = (Date.parse(endtime)) / 1000;

	var now = new Date();
	var now = (Date.parse(now) / 1000);

	var timeLeft = endTime - now;

	var days = Math.floor(timeLeft / 86400); 
	var hours = Math.floor((timeLeft - (days * 86400)) / 3600);
	var minutes = Math.floor((timeLeft - (days * 86400) - (hours * 3600 )) / 60);
	var seconds = Math.floor((timeLeft - (days * 86400) - (hours * 3600) - (minutes * 60)));

	if (hours < "10") { hours = "0" + hours; }
	if (minutes < "10") { minutes = "0" + minutes; }
	if (seconds < "10") { seconds = "0" + seconds; }

	$("#days").html(days + "<span>Days</span>");
	$("#hours").html(hours + "<span>Hours</span>");
	$("#minutes").html(minutes + "<span>Minutes</span>");
	$("#seconds").html(seconds + "<span>Seconds</span>");	
		if(timeLeft<0)
		{		
		$('.auction-offer').addClass("hidden");
		$('#timer').addClass('hidden');
		$('#timer-title').addClass('hidden');
		$('#expired').removeClass('hidden');
	      clearInterval(this);
		}

}


function loadPage(item, sale_type, loggato) {
	switch (sale_type) {
	case 'compraora':
		compraora();
		break;

	case 'asta':
		asta();
		break;
	}
	if (loggato) {
		$.ajax({
			url : "isWishlistItem",
			data : {
				'id_item' : item
			},
			success : function(data) {
				if (data == 'true') {
					$('.remove-from-wishlist').removeClass('hidden');
				} else
					$('.add-to-wishlist').removeClass('hidden');
			}
		});
	}
}

function compraora() {
	$('.add-to-cart').removeClass('hidden');
	$('#quantity').removeClass('hidden');
}

function asta() {
	$('.auction-offer').removeClass('hidden');

	
}

function updateOffer() {

	$.ajax({
		url : "update_item",
		method : "post",
		data : {
			"id_item" : id_item
		},
		success : function(data) {
			if (data.length == 0)
				return;
			var obj = $.parseJSON(data);

			$('#price > span').first().text(obj["offer"]);
			price_insertion = obj["offer"];
			if (obj["yourOffer"] == "true") {
				$('#best-offer').removeClass("hidden");
			} else {
				$('#best-offer').addClass("hidden");
			}
			$('#quantity > span').first().text(obj["quantity"]);
		},
		error : function() {
		}

	});

}

function buyNowInformation() {

	$.ajax({
		url : "buyNowInformation",
		method : "post",
		data : {
			"id_item" : id_item
		},
		success : function(data) {
			if (data.length == 0)
				return;
			var obj = $.parseJSON(data);
			$('#quantity > span').first().text(obj["quantity"]);
		},
		error : function() {
		}

	});

}
