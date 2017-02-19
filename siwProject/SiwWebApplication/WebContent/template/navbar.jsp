
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
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

			<a class="navbar-brand" id="brand" >Brand</a>
		</div>


		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="index.jsp">Home <span
						class="sr-only">(current)</span></a></li>
				<li><a id="link-prova">Link 1</a></li>
			</ul>
			<form class="navbar-form navbar-left">
				<div class="form-group">
					<input id="search-input" type="text" class="form-control"
						placeholder="Type to Search">
				</div>
				<button id="search-button" type="button" class="btn btn-default">Search</button>
				<div class="dropdown form-group">
				<select id="categoryNavbar" class="selectpicker show-tick" data-width="100%">
					<option>Tutte le categorie</option>
				<%@include file="category.html"%>
				</select>
			</div>
			</form>
			<ul id="signlog" class="nav navbar-nav navbar-right">
				<li><a class="cd-signin" id="login" href="#">Login</a></li>
				<li><a class="cd-signup" id="signup" href="#">Sign Up</a></li>

				<!-- Shopping Cart menu -->
				<li class="dropdown hidden" id="shopping-button"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false"><i class="fa fa-shopping-cart fa"></i> Shopping Cart
					
					 <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a id="go-to-shopping-cart">Go to shopping cart</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Separated link</a></li>
					</ul></li>
				<!-- Wishlist menu -->
				<li class="dropdown hidden" id="wishlist-button"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false">Wishlist <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#" id="gotoWishlist">Show Your List</a></li>
						<li role="separator" class="divider"></li>
						<li><a id="" >Clear WishList</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>

				<!-- user dropdown menu -->
				<li class="dropdown hidden" id="user-dropdown"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false"> <span
						class="glyphicon glyphicon-user"> </span> username <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="userProfile">Your Profile</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Clear List</a></li>
						<li role="separator" class="divider"></li>
						<li><a id="createInsertion">Crea un inserzione</a></li>
						<li role="separator" class="divider"></li>
						<li id="logout"><a href="#">logout</a></li>
					</ul></li>







			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>



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
					<label class="image-replace cd-username" for="signin-username">Username</label>
					<input class="full-width has-padding has-border"
						id="signin-username" type="username" placeholder="Username">
				</p>

				<p class="fieldset">
					<label class="image-replace cd-password" for="signin-password">Password</label>
					<input class="full-width has-padding has-border"
						id="signin-password" type="text" placeholder="Password"> <a
						href="#0" class="hide-password">Hide</a>
				</p>

				<div id="error-custom" class="hidden">Username or Password
					invalid!</div>

				<p class="fieldset">
					<input id="input-login" class="full-width" type="submit"
						value="Login">
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
					<input id="signup-username"
						class="full-width has-padding has-border" type="text"
						placeholder="Username">
				</p>

				<p class="fieldset">
					<label class="image-replace cd-email" for="signup-email">E-mail</label>
					<input class="full-width has-padding has-border" id="signup-email"
						type="email" placeholder="E-mail">
				</p>

				<p class="fieldset">
					<label class="image-replace cd-password" for="signup-password">Password</label>
					<input class="full-width has-padding has-border"
						id="signup-password" type="text" placeholder="Password"> <a
						href="#0" class="hide-password">Hide</a>
				</p>

				<p class="fieldset">
					<input class="full-width has-padding has-border" id="signup-name" type="text"
						placeholder="Name"> 
				</p>
				
				<p class="fieldset">
						<input class="full-width has-padding has-border"
						id="signup-surname" type="text" placeholder="Surname">
				</p>
				
				<p class="fieldset">
					<input class="full-width has-padding has-border" id="signup-address" type="text"
						placeholder="Address">
				</p>	
				
				<p class="fieldset">
					<input class="full-width has-padding has-border" id="signup-city" type="text"
						placeholder="City">
				</p>
				
				<p class="fieldset">
					<input class="full-width has-padding has-border" id="signup-province" type="text"
						placeholder="Province">
				</p>
				<p class="fieldset">
					<input class="full-width has-padding has-border" id="signup-postalcode" type="text"
						placeholder="Postal Code" maxlength="5">
				</p>	
				<p class="fieldset">
					<input class="full-width has-padding has-border" id="signup-country" type="text"
						placeholder="Country">
				</p>
				<p class="fieldset">
					<input class="full-width has-padding has-border" id="signup-telephone" type="text"
						placeholder="Telephone" maxlength="10">
				</p>	

				<p class="fieldset">
					<input id="signup-createUser" class="full-width has-padding"
						type="submit" value="Create account">
				</p>
			</form>

			<!-- <a href="#0" class="cd-close-form">Close</a> -->
		</div>
		<!-- cd-signup -->


		<a href="#0" class="cd-close-form">Close</a>
	</div>
	<!-- cd-user-modal-container -->
</div>
<!-- cd-user-modal -->
<!-- Page Content -->

