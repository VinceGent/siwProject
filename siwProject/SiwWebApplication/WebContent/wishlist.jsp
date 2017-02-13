<%@page import="elements.Insertion"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wishlist</title>
<script type="text/javascript" src="scripts/jquery.js"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="scripts/modernizr.js"></script>
<link href="css/customStyle.css" rel="stylesheet" type="text/css" />
<!-- Modernizr -->
<script src="scripts/main.js"></script>
<!-- Gem jQuery -->
<script src="scripts/guiScript.js"></script>
<script type="text/javascript" src="scripts/logicScript.js"></script>
<script type="text/javascript" src="scripts/wishlist.js"></script>

</head>
<body>
	<%@include file="template/navbar.html"%>

	<div class="container wishlist-container">
		<div class="row">
			<h1 class="col-md-12" style="text-align: center;">Wishlist</h1>
		</div>

		<%
			List<Insertion> wishlist = (List<Insertion>) request.getSession().getAttribute("wishlist");
			for (Insertion i : wishlist) {
				request.getSession().setAttribute("insertion", i);
		%>
		<%@include file="template/wishlist_item.jsp"%>

		<%
			}
		%>

	</div>


	<%@include file="template/checkSession.jsp"%>
</body>
</html>