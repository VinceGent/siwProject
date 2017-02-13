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
</head>
<body>
	<%@include file="template/navbar.html"%>

	<div class="container wishlist-container">
		<div class="row">
			<h1 class="col-md-12" style="text-align: center;">Wishlist</h1>
		</div>

		<div id="products" class="row list-group">
			<div class="item list-group-item col-xs-4 col-lg-4">
				<div class="thumbnail">
					<img class="group list-group-image"
						src="http://placehold.it/400x250/000/fff" alt="" />
					<div class="caption">
						<h4 class="group inner list-group-item-heading">Product title</h4>
						<p class="group inner list-group-item-text">Product
							description... Lorem ipsum dolor sit amet, consectetuer
							adipiscing elit, sed diam nonummy nibh euismod tincidunt ut
							laoreet dolore magna aliquam erat volutpat.</p>
						<div class="row">
							<div class="col-xs-12 col-md-6">
								<p class="lead">$21.000</p>
							</div>
							<div class="col-xs-12 col-md-2">
								<a class="btn btn-success" href="#">Add
									to cart</a>
							</div>
							<div class="col-xs-12 col-md-2">
								<a class="btn btn-info" href="#">Go to Item</a>
							</div>
							<div class="col-xs-12 col-md-2">
								<a class="btn btn-danger" href="#">Remove</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>


	<%@include file="template/checkSession.jsp"%>
</body>
</html>