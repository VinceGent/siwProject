<%@page import="java.util.List"%>
<%@page import="elements.Sales_type"%>
<%@page import="elements.Insertion"%>
<div id="products" class="row list-group">
	<div class="item list-group-item col-xs-4 col-lg-4">
		<div class="thumbnail">
			<img class="group list-group-image" style="max-height: 7em;"
				src="<%if (!((List<String>) request.getSession().getAttribute("image")).isEmpty())
				out.print("ServletImage?id_item="+((Insertion) request.getSession().getAttribute("insertion")).getId_item()+"&nameImage="+((List<String>) request.getSession().getAttribute("image")).get(0)); else
				out.print("images/default.jpg");%>"
				alt="" />
			<div class="caption">
				<h4 class="group inner list-group-item-heading">
					<%
						out.print(((Insertion) request.getSession().getAttribute("insertion")).getName());
					%>
				</h4>
				<p class="group inner list-group-item-text">
					<%
						out.print(((Insertion) request.getSession().getAttribute("insertion")).getDescription());
					%>
				</p>
				<div class="row">
					<div class="col-xs-12 col-md-4">
						<p class="lead">
							<%
								out.print(((Insertion) request.getSession().getAttribute("insertion")).getPrice() + " &euro;");
							%>
						</p>
					</div>
					<%
						if (!((Insertion) request.getSession().getAttribute("insertion")).getSales_type().equals(Sales_type.asta)) {
					%>
					<div class="col-xs-12 col-md-2">
						<a class="btn btn-success button-addToCart"
							id="<%out.print(((Insertion) request.getSession().getAttribute("insertion")).getId_item());%>">Add
							to cart</a>
					</div>
					<%
						}
					%>
					<div class="col-xs-12 col-md-2">
						<a class="btn btn-info"
							href="item_Selected?id_item=<%out.print(((Insertion) request.getSession().getAttribute("insertion")).getId_item());%>">Go
							to Item</a>
					</div>
					<div class="col-xs-12 col-md-2">
						<a
							id="removeButton_itm_<%out.print(((Insertion) request.getSession().getAttribute("insertion")).getId_item());%>"
							class="btn btn-danger rm-wishlist"
							data-href="removeWishlistItem?id_item=<%out.print(((Insertion) request.getSession().getAttribute("insertion")).getId_item());%>">Remove</a>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>