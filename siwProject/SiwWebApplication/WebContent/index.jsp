<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="elements.Insertion"%>
<%@page import="java.util.HashMap"%>
<html>
<head>
<meta charset="UTF-8">
<title>VCPbay</title>

<script type="text/javascript" src="scripts/jquery.js"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/customStyle.css" rel="stylesheet" type="text/css" />
<link href="css/heroic-features.css" rel="stylesheet" type="text/css" />
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script src="scripts/modernizr.js"></script>
<!-- Modernizr -->
<script src="scripts/main.js"></script>
<!-- Gem jQuery -->
<script type="text/javascript" src="scripts/guiScript.js"></script>
<script type="text/javascript" src="scripts/logicScript.js"></script>

</head>
<body>

	
	<%@ include file="template/navbar.jsp"%>
	<div class="container">
		<!-- Jumbotron Header -->
		
		<header class="jumbotron hero-spacer" id="jumbo-main">
		<div id="jumbo-container" class="well">
			<div class="">
			<h1>Benvenuto!</h1> 
			<p>Su VCP Bay trovi tutto quello che ti serve, dai un'occhiata!</p>
			</div>
			<div class=""><img class="img-responsive" alt="" src="img/vcp-logo.png" ></div>
		</div>
		</header>

		<hr>

		<!-- Title -->
		<div class="row">
			<div class="col-lg-12">
				<h3>Latest Insertions</h3>
			</div>
		</div>
		<!-- /.row -->

		<!-- Page Features -->
		<div class="row text-center">
		<%HashMap<Insertion,List<String>>insertions=(HashMap<Insertion,List<String>>)request.getSession().getAttribute("lastInsertion"); %>
<%for(Insertion insertion:insertions.keySet()){ 
request.getSession().setAttribute("insertion", insertion);
request.getSession().setAttribute("image", insertions.get(insertion));
%>
	<%@include file="template/lastInsertion.jsp" %>
	<%} %>
	</div>
		<!-- /.row -->

		<hr>

		<!-- Footer -->
		<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright &copy; Carlo Pietro Vincenzo Siw Course 2017</p>
				</div>
			</div>
		</footer>

	</div>
	
	
	
	<%@ include file="template/checkSession.jsp" %>

</body>
</html>