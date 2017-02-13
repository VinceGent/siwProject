<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Web Site</title>

<script type="text/javascript" src="scripts/jquery.js"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/customStyle.css" rel="stylesheet" type="text/css" />
<link href="css/heroic-features.css" rel="stylesheet" type="text/css" />
<script src="scripts/modernizr.js"></script>
<!-- Modernizr -->
<script src="scripts/main.js"></script>
<!-- Gem jQuery -->
<script type="text/javascript" src="scripts/guiScript.js"></script>
<script type="text/javascript" src="scripts/logicScript.js"></script>
</head>
<body>

	
	<%@ include file="template/navbar.html" %>

	<%@include file="template/exampleListInsertion.html" %>
	
	<%@ include file="template/checkSession.jsp" %>

</body>
</html>