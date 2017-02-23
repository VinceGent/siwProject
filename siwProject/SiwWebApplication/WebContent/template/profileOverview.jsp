<%@page import="elements.UserInformation"%>
<%
	UserInformation info = (UserInformation) request.getSession().getAttribute("userinfo");
%>
<div class="box-title-main row col-md-12">
	<h2 style="text-align: center;">Informazioni Account</h2>
</div>
<div class="box row">
	<div class="col-md-11 box-left">
		<div class="minibox-title">
			<div class="row">
				<h4 class="col-md-11">Informazioni Contatto</h4>
			</div>
		</div>
		<div class="text-container">
			<label>Username </label> <span id="display-username"> <%
 	out.print(request.getSession().getAttribute("username"));
 %>
			</span> <br> <label>Nome Completo</label><span id="display-fullname">
				<%
					out.print(info.getName() + " " + info.getSurname());
				%>
			</span> <br> <label>Email </label> <span id="display-email"> <%
 	out.print(request.getSession().getAttribute("email"));
 %>
			</span> <br>
		</div>

	</div>
</div>
<!-- <div class="row col-md-12" style="	border-bottom: 1px solid #e5e5e5; margin: 10px 0 10px;	padding-bottom: 3px;" > </div>  -->
<!-- second set of box -->
<div class="minibox-title row col-md-11" style="margin-top: 20px;">

	<h3 class="col-md-10" style="text-align: center;">Rubrica</h3>
	<!-- <a class="col-md-2" style="float: left;" href="#">gestisci</a> -->
</div>
<div class="box row">
	<div id="billing-address" class="col-md-11 box-center">
		<div class="minibox-title">
			<div class="row">
				<h4 class="col-md-11">Indirizzo Fatturazione</h4>
			</div>
		</div>
		<div class="user-info-container" style="font-size: 12px;">
			<span id="address-name"><label>Nome completo </label> <%
 	out.print(info.getName() + " " + info.getSurname());
 %> </span> <br> <span id="address-street"><label>Indirizzo
			</label> <%
 	out.print(info.getAddress());
 %> </span><br> <span id="address-city"><label>Città </label> <%
 	out.print(info.getCity() + ", " + info.getProvince() + ", " + info.getPostal_code());
 %> </span><br> <span id="address-country"><label>Nazione </label> <%
 	out.print(info.getCountry());
 %> </span> <br> <span id="address-telephone"><label>N°
					telefono </label> <%
 	out.print(info.getTelephone());
 %></span>
		</div>

	</div>

</div>

<!-- end second set box -->

