<%@page import="elements.UserInformation"%>
<% UserInformation info = (UserInformation)request.getSession().getAttribute("userinfo"); if(info==null) info=new UserInformation();%>
<div class="box-title-main row col-md-12">
	<h2 style="text-align: center;">Informazioni Account</h2>
</div>
<div class="box row">
	<div class="col-md-6 box-left">
		<div class="minibox-title">
			<div class="row">
				<h4 class="col-md-9">Informazioni Contatto</h4>
				<a class="col-md-3" style="margin-top: 10px;" href="#">Modifica</a>
			</div>
		</div>
		<div class="text-container">
			<label>Username </label> <span id="display-username"><% out.print(request.getSession().getAttribute("username"));	%></span> <br>
			<label>Nome Completo</label><span id="display-fullname"> <% out.print(info.getName() + " " + info.getSurname()); %></span> <br> <label>Email </label> <span id="display-email">
			<% out.print(request.getSession().getAttribute("email")); %>
			</span> <br>
		</div>

	</div>
	<div class="col-md-6 box-right">
		<div class="minibox-title row">
			<h4 class="col-md-9">Altre Informazioni</h4>
			<a class="col-md-3">modifica</a>
		</div>
		Vincenzo Gentile <br> vincegent93@gmail.com<br>
		<!-- <a href="#">Cambia password account</a> -->

	</div>
</div>
<!-- <div class="row col-md-12" style="	border-bottom: 1px solid #e5e5e5; margin: 10px 0 10px;	padding-bottom: 3px;" > </div>  -->
<!-- second set of box -->
<div class="minibox-title row col-md-12" style="margin-top: 20px;">

	<h3 class="col-md-10" style="float: left; margin-top: 9px;">Rubrica</h3>
	<!-- <a class="col-md-2" style="float: left;" href="#">gestisci</a> -->
</div>
<div class="box row">
	<div id="billing-address" class="col-md-6 box-left">
		<div class="minibox-title">
			<div class="row">
				<h4 class="col-md-9">Indirizzo Fatturazione</h4>
			</div>
		</div>
		<div  class="user-info-container">
			<span id="address-name"> Vincenzo Gentile </span> <br> <span
				id="address-street"> via Louis braille 7 </span><br> <span
				id="address-city">Rende, Cosenza, 87036</span><br> <span
				id="address-country">Italia</span> <br> <span
				id="address-telephone">3393350401</span> <br> <a
				href="https://www.consoleplanet.it/customer/address/edit/id/62840/">Modifica
				indirizzo</a>
		</div>

	</div>
	<div id="shipping-address" class="col-md-6 box-right">
		<div class="minibox-title row">
			<h4 class="col-md-9">Indirizzo Spedizione Predefinito</h4>
			<a>modifica</a>
		</div>
		<div class="user-info-container">
			<span id="address-name"> Vincenzo Gentile </span> <br> <span
				id="address-street"> via Louis braille 7 </span><br> <span
				id="address-city">Rende, Cosenza, 87036</span><br> <span
				id="address-country">Italia</span> <br> <span
				id="address-telephone">3393350401</span> <br> <a
				href="https://www.consoleplanet.it/customer/address/edit/id/62840/">Modifica
				indirizzo</a>
		</div>
	</div>
</div>

<!-- end second set box -->

