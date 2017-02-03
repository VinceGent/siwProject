<%@page import="elements.Sales_type"%>
<%@page import="elements.Insertion"%>

<div class="container">
		<div class="col-md-5 single-top">
			<ul id="list_single_image">
				<li><img id="image-selected" alt="" src="images/si1.jpg"
					data-zoom-image="images/si1.jpg"></li>
			</ul>
		</div>
		<%
			Insertion insertion = (Insertion) request.getAttribute("insertion");
		%>
		<div class="col-md-7 single-top-in">
			<div class="single-para">
				<h4 id="name-item">
					NOME:
					<%
					out.print(insertion.getName());
				%>
				</h4>
				<h4 id="category-item">Categoria:</h4>

				<h4 id="insertion-date">
					Data inserzione:
					<%
					out.print(insertion.getInsertion_date());
				%>
				</h4>
				<h4 id="expiration-date">
					Data scadenza:
					<%
					out.print(insertion.getExpiration_date());
				%>
				</h4>
				<h4 id="sales-type">
					Tipo vendita:
			
				</h4>
				<h4 id="quantity">
					Quantità rimanente:
					<%
					out.print(insertion.getAmount());
				%>
				</h4>

				<h4 id="price">
					Prezzo:
					<%
					out.print(insertion.getPrice());
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

				<div class="row">
					<ul class="button-list">
						<li><a id="buyNow" class="cart ">Compra subito</a></li>
						<li><a id="auctionSale" class="cart ">piazza offerta</a></li>
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

					<%
					if (insertion.getSales_type().equals(Sales_type.compraora)) {
				%>
					<script type="text/javascript">
						buyNow();
					</script>
					<%
						} else {
							out.print(insertion.getSales_type().toString().toUpperCase());
					%>
					<script type="text/javascript">
						auctionSale();
					</script>
					<%
						}
					%>
		
<script type="text/javascript">
var id_item=<%=insertion.getId_item()%>;
</script>