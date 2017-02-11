<%@page import="elements.Insertion"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery.js"></script>

<script type="text/javascript" src="scripts/logicScript.js"></script>
<script src="scripts/guiScript.js"></script>
<script type="text/javascript" src="scripts/searchScript.js"></script>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link href="css/styleItem.css" rel="stylesheet" type="text/css" />
<link href="css/styleSearch.css" rel="stylesheet" type="text/css" />
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/customStyle.css" rel="stylesheet" type="text/css" />
<link href="css/heroic-features.css" rel="stylesheet" type="text/css" />
<script src="scripts/modernizr.js"></script>
<!-- Modernizr -->
<script src="scripts/testjq.js"></script>
<script src="scripts/main.js"></script>

</head>
<body>

	<%@ include file="/template/navbar.html"%>

	<div class="container">
		<!-- <input id="clickMe" type="button" value="clickme"
			onclick="provaAdd();" /> -->
		<div class="well well-sm">
			<strong>Category Title</strong>
			<div class="btn-group">
				<a href="#" id="list" class="btn btn-default btn-sm"><span
					class="glyphicon glyphicon-th-list"> </span>List</a> <a href="#"
					id="grid" class="btn btn-default btn-sm"><span
					class="glyphicon glyphicon-th"></span>Grid</a>
			</div>
		</div>
		<div id="products" class="row list-group prova">
			<%
			
				List<Insertion> insertions = (List<Insertion>) request.getSession().getAttribute("insertions");
				
				for (Insertion insertion : insertions) { // stabilire prima il risultato della ricerca
					request.getSession().setAttribute("currentInsertion", insertion);
			%>
			<%@ include file="template/searchItem.jsp"%>
			<%
				}
			%>

		</div>
	</div>
	<%@ include file="/template/checkSession.jsp"%>
</body>
</html>