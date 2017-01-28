<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item Page</title>
<script type="text/javascript" src="scripts/jquery.js"></script>

<script type="text/javascript" src="scripts/script.js"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link href="css/styleItem.css" rel="stylesheet" type="text/css" />
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/customStyle.css" rel="stylesheet" type="text/css" />
<link href="css/heroic-features.css" rel="stylesheet" type="text/css" />
<script src="scripts/modernizr.js"></script>
<!-- Modernizr -->
<script src="scripts/testjq.js"></script>
<script src="scripts/main.js"></script>
<script src="scripts/registrationValidation.js"></script>
<!-- Gem jQuery -->

</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Brand</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home <span class="sr-only">(current)</span></a></li>
				<li><a href="#">Link 1</a></li>
			</ul>
			<form class="navbar-form navbar-left">
				<div class="form-group">
					<input type="text" class="form-control"
						placeholder="Type to Search">
				</div>
				<button type="submit" class="btn btn-default">Search</button>
			</form>
			<ul id="signlog" class="nav navbar-nav navbar-right">
				<li><a class="cd-signin" id="login" href="#">Login</a></li>
				<li><a class="cd-signup" id="signup" href="#">Sign Up</a></li>

				<!-- Shopping Cart menu -->
				<li class="dropdown" id="shopping-button"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false">Shopping Cart <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Separated link</a></li>
					</ul></li>
				<!-- Wishlist menu -->
				<li class="dropdown" id="wishlist-button"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false">Wishlist <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Show Your List</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Clear List</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>



	<div class="cd-user-modal">
		<!-- this is the entire modal form, including the background -->
		<div class="cd-user-modal-container">
			<!-- this is the container wrapper -->
			<ul class="cd-switcher">
				<li><a href="#0">Sign in</a></li>
				<li><a href="#0">New account</a></li>
			</ul>

			<div id="cd-login">
				<!-- log in form -->
				<form id="login-form" class="cd-form">
					<p class="fieldset">
						<label class="image-replace cd-email" for="signin-email">E-mail</label>
						<input class="full-width has-padding has-border" id="signin-email"
							type="email" placeholder="E-mail"> <span
							class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<label class="image-replace cd-password" for="signin-password">Password</label>
						<input class="full-width has-padding has-border"
							id="signin-password" type="text" placeholder="Password">
						<a href="#0" class="hide-password">Hide</a> <span
							class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<input type="checkbox" id="remember-me" checked> <label
							for="remember-me">Remember me</label>
					</p>

					<p class="fieldset">
						<input class="full-width" type="submit" value="Login"
							onclick="validation()">
					</p>
				</form>

				<!-- <a href="#0" class="cd-close-form">Close</a> -->
			</div>
			<!-- cd-login -->

			<div id="cd-signup">
				<!-- sign up form -->
				<form id="reg_form" action="./AddUser" method="POST" class="cd-form">
					<p class="fieldset">
						<label class="image-replace cd-username" for="signup-username">Username</label>
						<input onkeyup="checkUsername()"
							class="full-width has-padding has-border" id="signup-username"
							type="text" placeholder="Username"> <span
							class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<label class="image-replace cd-email" for="signup-email">E-mail</label>
						<input onkeyup="checkEmail()"
							class="full-width has-padding has-border" id="signup-email"
							type="email" placeholder="E-mail"> <span
							class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<label class="image-replace cd-password" for="signup-password">Password</label>
						<input class="full-width has-padding has-border"
							id="signup-password" type="text" placeholder="Password">
						<a href="#0" class="hide-password">Hide</a> <span
							class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<input type="checkbox" id="accept-terms"> <label
							for="accept-terms">I agree to the <a href="#0">Terms</a></label>
					</p>

					<p class="fieldset">
						<input onclick="registration()" class="full-width has-padding"
							type="submit" value="Create account">
					</p>
				</form>
			</div>
			<a href="#0" class="cd-close-form">Close</a>
		</div>
	</div>
	<%
		if (request.getAttribute("logged") != null && !request.getAttribute("logged").equals("ok")) {
	%>
	<script type="text/javascript">
		logged();
	</script>

	<%
		} else {
	%>
	<script type="text/javascript">
		notLogged();
	</script>
	<%
		}
	%>

	<div class="container">
		<div class="col-md-5 single-top">
			<ul id="list_single_image">
				<li><img id="image-selected" alt="" src="images/si1.jpg"
					data-zoom-image="images/si1.jpg"></li>
			</ul>
		</div>

		<div class="col-md-7 single-top-in">
			<div class="single-para">
				<h1 id="name-item">NOME</h1>
				<h3 id="category-item">Categoria:</h3>

				<h4 id="insertion-date">
					Data inserzione:
					<%
					Date d = new Date();
					out.print(d);
				%>
				</h4>
				<h4 id="expiration-date">
					Data scadenza:
					<%
					Date c = new Date();
					out.print(c);
				%>
				</h4>
				<h4 id="sales-type">
					Tipo vendita:
					<%
					
				%>
				</h4>
				<h4 id="quantity">
					Quantit� rimanente:
					<%
					
				%>
				</h4>

				<h4 id="price">
					Prezzo:
					<%
					
				%>
				</h4>


				<div class="star">
					<ul>
						<li><i> </i></li>
						<li><i> </i></li>
						<li><i> </i></li>
						<li><i> </i></li>
						<li><i> </i></li>
					</ul>
					<div class="review">
						<a href="#"> 3 reviews </a>/ <a href="#"> Write a review</a>
					</div>
					<div class="clearfix"></div>
				</div>



				<div class="available">
					<h6>Available Options :</h6>
					<ul>

						<li>Size:<select>
								<option>Large</option>
								<option>Medium</option>
								<option>small</option>
								<option>Large</option>
								<option>small</option>
						</select></li>
						<li>Cost: <select>
								<option>U.S.Dollar</option>
								<option>Euro</option>
						</select></li>
					</ul>
				</div>

				<a href="#" class="cart ">More details</a>


			</div>
		</div>

		<div id="div-etalage">
			<ul id="etalage">
				<li><img class="etalage_thumb_image img-responsive"
					src="images/si1.jpg" alt=""></li>
				<li><img class="etalage_thumb_image img-responsive"
					src="images/si2.jpg" alt=""></li>
				<li><img class="etalage_thumb_image img-responsive"
					src="images/si.jpg" alt=""></li>
				<li><img class="etalage_thumb_image img-responsive"
					src="images/si3.jpg" alt=""></li>
			</ul>
		</div>

	</div>
	<div id="div-description">
		<h1>Descrizione:</h1>
	</div>
	<div>
		<p id="description-item">bla bla bla
			asaassssssssssssssssssssssssss sssssssssssssssssssssssssssss ssssssss sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd</p>
	</div>

</body>
</html>