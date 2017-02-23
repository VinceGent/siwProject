var time=5;
$(document).ready(function(){
	window.setInterval(updateTime, 1000);
	window.setInterval(goToNewInsertion, 5000);


});
function updateTime()
{
	if(time>0)
		time--;
	
	$('#id_time').find('span').text(time);
	
}

function goToNewInsertion()
{
	document.location.href = "item_Selected?id_item="+newInsertion_id;

}
