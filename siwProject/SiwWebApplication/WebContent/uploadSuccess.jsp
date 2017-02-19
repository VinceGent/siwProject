<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insertion created correctly</title>

<script type="text/javascript" src="scripts/jquery.js"></script>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="scripts/insertionCreated.js"></script>
<link href="css/style.css" rel="stylesheet"
	type="text/css" />
</head>
<body>




	<div class="container">

		<div class="row">
			
			<div class="col-md-7" style="text-align:center">
				<img src="images/ok.png" height="50%" width="50%">
			</div>
			<div class="col-md-5" style="margin-top: 20px">
<h1 style="text-align:center">Congratulations!</h1>
<h3>You have successfully created your insertion </h3><p id="id_time">Wait <span>5</span> seconds to redirecting... or <a onclick="goToNewInsertion()">go to your insertion</a></p>

</div>
		</div>
	</div>











<script type="text/javascript">
 var newInsertion_id="<%=request.getSession().getAttribute("id_item").toString()%>";
	</script>

</body>
</html>