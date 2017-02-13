<%@page import="java.text.SimpleDateFormat"%>
<%@page import="elements.Sales_type"%>
<%@page import="elements.Insertion"%>

<div class="container">
	<div class="col-md-5 single-top">
		<ul id="list_single_image">
			<li style="width: 54%; margin-left: 44px;"><img id="image-selected"
					src="images/si1.jpg" ></li>
		</ul>
	</div>
	<%
		Insertion insertion = (Insertion) request.getAttribute("insertion");
	%>
	<div class="col-md-7 single-top-in">
		<div class="single-para">


			<div class="row">
				<h4 id="category-itemLabel" class="col-md-4">Categoria:</h4>

				<h4 id="category-item" class="col-md-6">
					<%
						out.print("inserire la categoria");
					%>
				</h4>
			</div>



			<div class="row">
				<h4 id="name-itemLabel" class="col-md-4">Nome:</h4>

				<h4 id="name-item" class="col-md-6">
					<%
						out.print(insertion.getName());
					%>
				</h4>
			</div>








			<div class="row">
				<h4 id="insertion-dateLabel" class="col-md-4">Data inserzione:</h4>

				<h4 id="insertion-date" class="col-md-6">
					<%
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						out.print(dateFormat.format(insertion.getInsertion_date()));
					%>
				</h4>
			</div>





			<div class="row current-offer-div">
				<h4 id="expirationLabel" class="col-md-4">Data scadenza:</h4>

				<h4 id="expiration-date" class="col-md-6">
					<%
						out.print(dateFormat.format(insertion.getExpiration_date()));
					%>
				</h4>
			</div>



			<div class="row quantity-div">
				<h4 id="quantityLabel" class="col-md-4">Rimanenti:</h4>

				<h4 id="quantity" class="col-md-6">
					<%
						out.print(insertion.getAmount());
					%>
				</h4>
			</div>

			<div class="row price-div">
				<h4 id="priceLabel" class="col-md-4">Prezzo:</h4>

				<h4 id="price" class="col-md-6">
					<%
						out.print(insertion.getPrice() + "  $");
					%>
				</h4>
			</div>

			<div class="row current-offer-div">
				<h4 id="current-offerLabel" class="col-md-4">Offerta corrente:</h4>

				<h4 id="current-offer" class="col-md-6">
					<%
						out.print(insertion.getPrice() + "  $");
					%>
				</h4>
			</div>

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


			<div class="row form-group current-offer-div">
				<h4 class="col-md-2">Offri:</h4>
				<div class="col-md-7 ">
					<input style="padding-top: 2%" id="offer" type="number"
						name="quantity" STEP="0.01" min="0" placeholder="Inserisci offerta"></input>
				</div>
			</div>








			<div class="row">
				<ul class="button-list">
					<li><a id="buyNow" class="cart ">Compra subito</a></li>
					<li><a id="auctionSale" class="cart ">piazza offerta</a></li>
					<li id="best-offer" class="hidden"><h4>Sei il miglior offerente!!</h4><li>
				</ul>
			</div>
		</div>
	</div>

	<div id="div-etalage" class="col-md-6">
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
<div class="panel panel-default container">
	<div class="single-para panel-heading" id="div-description">
		<h4>Descrizione:</h4>
	</div>
	<div class="panel-body">
		<p id="description-item">
			<%
				out.print(insertion.getDescription());
			%>
		</p>
	</div>
</div>
<script type="text/javascript">
	var id_item ="<%=insertion.getId_item()%>";
	var price_insertion="<%=insertion.getPrice()%>";
	var sales_type="<%=insertion.getSales_type()%>";
	var amount="<%=insertion.getAmount()%>";
</script>

<%
	if (insertion.getSales_type().equals(Sales_type.compraora)) {
%>
<script type="text/javascript">
	buyNow();
</script>
<%
	} else {
%>
<script type="text/javascript">
var priceInsertion="<%=insertion.getPrice()%>";
	auctionSale(priceInsertion);
</script>
<%
	}
%>
