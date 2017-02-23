<%@page import="elements.Sales_type"%>
<%@page import="elements.Feedback"%>
<%@page import="org.joda.time.Days"%>
<%@page import="org.joda.time.DateTime"%>
<%@page import="java.util.List"%>
<%@page import="elements.Insertion"%>
<div class="container">
	<div class="card">
		<div class="">
			<div class="wrapper row">
				<div class="preview col-md-5">
					<%
						Insertion insertion = (Insertion) request.getAttribute("insertion");
						List<String> images = (List<String>) request.getSession().getAttribute("images");
						Feedback feedbackAvg = (Feedback) request.getSession().getAttribute("feedback");
					%>


					<div class="preview-pic tab-content"
						style="text-align: center; max-height: 450px; min-height: 450px;">
						<%
							if (images.isEmpty()) {
						%>
						<div class="tab-pane active" id="pic-1">
							<img id="image-selected" src="images/default.jpg"
								style="max-height: 450px; min-height: 450px;" />
						</div>
						<%
							}

							for (int i = 0; i < images.size(); i++) {
								if (i < 1) {
						%>
						<div class="tab-pane active" id="pic-<%out.print(i);%>">
							<img id="image-selected"
								src="<%out.print("ServletImage?id_item=" + insertion.getId_item() + "&nameImage=" + images.get(i));%>"
								style="max-height: 450px; min-height: 450px;" />
						</div>
						<%
							} else {
						%>

						<div class="tab-pane" id="pic-<%out.print(i);%>">
							<img id="image-selected"
								style="max-height: 450px; min-height: 450px;"
								src="<%out.print("ServletImage?id_item=" + insertion.getId_item() + "&nameImage=" + images.get(i));%>" />
						</div>
						<%
							}
							}
						%>
					</div>

					<ul class="preview-thumbnail nav nav-tabs">
						<%
							for (int i = 0; i < images.size(); i++) {
						%>
						<%
							if (i < 1) {
						%>
						<li class="active"><a data-target="#pic-<%out.print(i);%>"
							data-toggle="tab"><img
								src="<%out.print("ServletImage?id_item=" + insertion.getId_item() + "&nameImage=" + images.get(i));%>" /></a></li>
						<%
							} else {
						%>
						<li><a data-target="#pic-<%out.print(i);%>" data-toggle="tab"><img
								src="<%out.print("ServletImage?id_item=" + insertion.getId_item() + "&nameImage=" + images.get(i));%>" /></a></li>
						<%
							}
							}
						%>
					</ul>

				</div>
				<div class="details col-md-7">
					<h3 id="product-title" class="product-title">
						<%
							out.print(insertion.getName());
						%>
					</h3>
					<div class="row lead">
						<div id="stars-existing" class="starrr col-md-7"
							style="color: #FFBF18"
							data-rating="<%out.print(feedbackAvg.getAvg());%>"></div>
					</div>



					<%
						if (insertion.getSales_type() == Sales_type.asta) {
					%>
					<h3 id="timer-title" style="text-transform: uppercase; font-weight: bold;" >Time left</h3>
					<div id="timer" class="col-md-10"
						style="border-radius: 10px; margin: 7px 0;">
						<div id="days"></div>
						<div id="hours"></div>
						<div id="minutes"></div>
						<div id="seconds"></div>
					</div>

					<div id="expired" class="hidden">
						<h3>EXIPIRED</h3>
					</div>

					<%
						}
					%>



					<!--  panel  descrizione   -->
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title"
								style="text-align: center; text-transform: uppercase;">Description</h3>
						</div>
						<div class="panel-body">
							<%
								out.print(insertion.getDescription());
							%>
						</div>
					</div>

					<!--  panel descrizione  -->
					<h4 id="category" class="item-info">
						category: <span> <%
 	out.print(insertion.getCategory());
 %>
						</span>
					</h4>
					<h4 id="quantity" class="item-info hidden">
						quantity: <span> <%
 	out.print(insertion.getAmount());
 %>
						</span>
					</h4>
					<h4 id="price" class="item-info">
						price: <span> <%
 	out.print(insertion.getPrice());
 %>
						</span><span>&euro;</span>
					</h4>
					<div>
					<div class="auction-offer col-lg-6 col-md-6 col-sm-6 col-xs-6 hidden">
						<div class="input-auction input-group">
							<span class="input-group-addon">&euro;</span> <input id="offer"
								type="text" class="form-control"
								aria-label="Amount (to the nearest dollar)"
								placeholder="Insert Your Offer Here" style="text-align: center;" >

						</div>
						<div class="action col-md-12" style="margin-top: 5px;" >
							<a class="offer-button btn" type="button">Offer</a>
						</div>
					</div>
					</div>
					<div class="action button-container">
						<a class="add-to-cart btn btn-default hidden" type="button">buy
							now</a> <a class="add-to-wishlist hidden btn btn-info" type="button">add
							to wishlist</a> <a class="hidden remove-from-wishlist btn btn-danger"
							type="button">remove from wishlist</a>
						<!-- <button class="like btn btn-default" type="button"><span class="fa fa-heart"></span></button> -->
					</div>
				</div>
			</div>
		</div>
		<%@ include file="showComment.jsp"%>
	</div>
</div>
<%
	if (request.getSession().getAttribute("login") != null) {
%>
<script type="text/javascript">var loggato=true;</script>
<%
	} else {
%>
<script type="text/javascript">var loggato=false;</script>
<%
	}
	System.out.println("skasdkjasjd     " + insertion.getExpiration_date());
%>
<script type="text/javascript">var sale_type="<%=insertion.getSales_type()%>"; 
	var id_item ="<%=insertion.getId_item()%>";
	loadPage(id_item, sale_type,loggato);
	var price_insertion="<%=insertion.getPrice()%>";
	var sales_type="<%=insertion.getSales_type()%>";
	var amount="<%=insertion.getAmount()%>";
	var endtime="<%=insertion.getExpiration_date().toInstant()%>";
</script>