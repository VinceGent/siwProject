<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Colonne bootstrap</title>
<script type="text/javascript" src="scripts/jquery.js"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link href="css/myNavbar.css" rel="stylesheet" type="text/css" />
<link href="css/itemdisplay.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="flipkart-navbar" class="navbar-fixed-top">
		<div class="container">
			<div class="row row1">
				<ul class="largenav pull-right">
					<li class="upper-links"><a class="links">Link 1</a></li>
					<li class="upper-links"><a class="links">Link 2</a></li>
					<li class="upper-links"><a class="links">Link 3</a></li>
					<li class="upper-links user-button"><a class="links">User</a>
						<span class="glyphicon glyphicon-user"></span></li>
				</ul>
			</div>
			<!-- row 1 end tag -->
			<div class="row row2">
				<div class="col-sm-2">
					<h2 style="margin: 0px;">
						<span class="smallnav menu" onclick="openNav()">☰ Home</span>
					</h2>
					<h1 style="margin: 0px;">
						<span class="largenav">Home</span>
					</h1>
				</div>
				<div class="flipkart-navbar-search smallsearch col-sm-8 col-xs-11">
					<input class="flipkart-navbar-input col-xs-11" type="text"
						placeholder="Search for Products, Brands and more" name="">
					<button class="flipkart-navbar-button col-xs-1">
						<svg width="15px" height="15px"> <path
							d="M11.618 9.897l4.224 4.212c.092.09.1.23.02.312l-1.464 1.46c-.08.08-.222.072-.314-.02L9.868 11.66M6.486 10.9c-2.42 0-4.38-1.955-4.38-4.367 0-2.413 1.96-4.37 4.38-4.37s4.38 1.957 4.38 4.37c0 2.412-1.96 4.368-4.38 4.368m0-10.834C2.904.066 0 2.96 0 6.533 0 10.105 2.904 13 6.486 13s6.487-2.895 6.487-6.467c0-3.572-2.905-6.467-6.487-6.467 "></path>
						</svg>
					</button>
				</div>
				<!-- search bar end tag -->
				<div class="cart largenav col-sm-2">
					<a class="cart-button"> <svg class="cart-svg " width="16px"
							height="16px" viewBox="0 0 16 16 "> <path
							d="M15.32 2.405H4.887C3 2.405 2.46.805 2.46.805L2.257.21C2.208.085 2.083 0 1.946 0H.336C.1 0-.064.24.024.46l.644 1.945L3.11 9.767c.047.137.175.23.32.23h8.418l-.493 1.958H3.768l.002.003c-.017 0-.033-.003-.05-.003-1.06 0-1.92.86-1.92 1.92s.86 1.92 1.92 1.92c.99 0 1.805-.75 1.91-1.712l5.55.076c.12.922.91 1.636 1.867 1.636 1.04 0 1.885-.844 1.885-1.885 0-.866-.584-1.593-1.38-1.814l2.423-8.832c.12-.433-.206-.86-.655-.86 "
							fill="#fff "></path> </svg> Shopping Cart <span class="item-number ">0</span>
					</a>
				</div>
				<!-- shopping cart item -->
			</div>
		</div>
		<!-- container end -->
	</div>
	<!-- navbar end tag -->



	<!-- Main Page Content  -->

	<div class="col-md-9">
		<div class="col-md-5">
			<div class="container-fluid" style="background-color: red;">
				<ul id="image-container">
					<li class="big-image"><img alt="" src="images/si1.jpg">
					</li>
					<li>
						<ul id="small-image-container">
							<li class="small-image"><img alt="" src="images/si2.jpg">
							</li>
							<li class="small-image"><img alt="" src="images/si3.jpg">
							</li>
							<li class="small-image"><img alt="" src="images/si3.jpg">
							</li>
							<li class="small-image"><img alt="" src="images/si3.jpg">
							</li>
							<li class="small-image"><img alt="" src="images/si3.jpg">
							</li>
							<li class="small-image"><img alt="" src="images/si3.jpg">
							</li>
							<li class="small-image"><img alt="" src="images/si3.jpg">
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>

		<div class="col-md-7" style="background-color: blue">
			<div class="container-fluid">
				<h2>title 2</h2>
				<br>
				<p>
					ciaoasidaodhòajsfnòajfònaaaaaaaaaaaaa<br>aaaaaaaaaaaaaaaaaaaaa<br>aaaaaaaaaaaaaaaaaaaaa<br>aaaaaaaaaa
				</p>
			</div>
		</div>
	</div>

	<div class="col-md-3" style="background-color: green;">
		<p>COLONNA 3</p>
		<br>
	</div>


</body>
</html>