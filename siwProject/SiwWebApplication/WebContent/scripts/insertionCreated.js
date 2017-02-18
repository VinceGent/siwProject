$(document).ready(function(){
//	window.setInterval(goToNewInsertion, 2000);
	console.log(newInsertion_id);

});

function goToNewInsertion()
{
	document.location.href = "item_Selected?id_item="+newInsertion_id;

}
