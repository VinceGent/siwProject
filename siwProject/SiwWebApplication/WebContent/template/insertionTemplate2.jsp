<%@page import="java.util.List"%>
<%@page import="elements.Insertion"%>
<div class="container">
	<div class="card">
		<div class="">
			<div class="wrapper row">
				<div class="preview col-md-5">
					<%
						Insertion insertion = (Insertion) request.getAttribute("insertion");
					List<String>images=(List<String>)request.getSession().getAttribute("images");
					
					%>
					
					<div class="preview-pic tab-content">	
							<% 
							if(images.isEmpty()) {%>				
							<div class="tab-pane active" id="pic-1">
								<img id="image-selected" src="images/default.jpg" style="min-height:34em" />
							</div>
							<%}
							
					for(int i=0;i<images.size();i++ ){ 
					if(i<1) {%>				
						<div class="tab-pane active" id="pic-<%out.print(i);%>">
							<img id="image-selected" src="<%out.print("ServletImage?id_item="+insertion.getId_item()+"&nameImage="+images.get(i));%>" />
						</div>
						<%} else {%>
						
					<div class="tab-pane" id="pic-<%out.print(i);%>">
							<img id="image-selected" src="<%out.print("ServletImage?id_item="+insertion.getId_item()+"&nameImage="+images.get(i));%>" />
						</div>
						<%}} %>
					</div>
					
					<ul class="preview-thumbnail nav nav-tabs">
					<% 
					for(int i=0;i<images.size();i++ ){ %>
					<%if(i<1) {%>
						<li class="active"><a data-target="#pic-<%out.print(i);%>" data-toggle="tab"><img
								src="<%out.print("ServletImage?id_item="+insertion.getId_item()+"&nameImage="+images.get(i));%>"/></a></li>
						<%} else{%>
						<li><a data-target="#pic-<%out.print(i);%>" data-toggle="tab"><img
								src="<%out.print("ServletImage?id_item="+insertion.getId_item()+"&nameImage="+images.get(i));%>" /></a></li>
					<%}} %>
					</ul>

				</div>
				<div class="details col-md-7">
					<h3 id="product-title" class="product-title">
						<%
							out.print(insertion.getName());
						%>
					</h3>
					<div class="rating">
						<div class="stars">
							<span class="fa fa-star checked"></span> <span
								class="fa fa-star checked"></span> <span
								class="fa fa-star checked"></span> <span class="fa fa-star"></span>
							<span class="fa fa-star"></span>
						</div>
						<span class="review-no">41 reviews</span>
					</div>

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
						category: <span>category</span>
					</h4>
					<h4 id="quantity" class="item-info">
						quantity: <span> <%
 	out.print(insertion.getAmount());
 %>
						</span>
					</h4>
					<h4 id="sales_type" class="item-info">
						sales type: <span> <%
 	out.print(insertion.getSales_type());
 %>
						</span>
					</h4>
					<h4 id="price" class="item-info">
						price: <span> <%
 	out.print(insertion.getPrice());
 %> &euro;
						</span>
					</h4>
					<!-- <p class="vote"><strong>91%</strong> of buyers enjoyed this product! <strong>(87 votes)</strong></p> -->
					<!-- <h5 class="sizes">sizes:
							<span class="size" data-toggle="tooltip" title="small">s</span>
							<span class="size" data-toggle="tooltip" title="medium">m</span>
							<span class="size" data-toggle="tooltip" title="large">l</span>
							<span class="size" data-toggle="tooltip" title="xtra large">xl</span>
						</h5>
						<h5 class="colors">colors:
							<span class="color orange not-available" data-toggle="tooltip" title="Not In store"></span>
							<span class="color green"></span>
							<span class="color blue"></span>
						</h5>  -->
					<div class="auction-offer col-md-5 col-sm-9 hidden">
						<div class="input-auction input-group">
							<span class="input-group-addon">&euro;</span> <input type="text"
								class="form-control" aria-label="Amount (to the nearest dollar)" placeholder="Insert Your Offer Here">
							
						</div>
						<div class="action col-md-12" style="margin-top:5px;">
							<a class="offer-button btn" type="button">Offer</a>
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
	</div>
</div>
<%if(request.getSession().getAttribute("login")!=null){%>
<script type="text/javascript">var loggato=true;</script>
<% }else{%>
<script type="text/javascript">var loggato=false;</script>
<%} %>
<script type="text/javascript">var sale_type="<%=insertion.getSales_type()%>"; 

	var id_item ="<%=insertion.getId_item()%>";
	loadPage(id_item, sale_type,loggato);
	var price_insertion="<%=insertion.getPrice()%>";
	var sales_type="<%=insertion.getSales_type()%>";
	var amount="<%=insertion.getAmount()%>";

</script>