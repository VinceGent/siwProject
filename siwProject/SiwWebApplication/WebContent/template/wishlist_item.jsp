<%@page import="elements.Insertion"%>
<div id="products" class="row list-group">
			<div class="item list-group-item col-xs-4 col-lg-4">
				<div class="thumbnail">
					<img class="group list-group-image"
						src="http://placehold.it/400x250/000/fff" alt="" />
					<div class="caption">
						<h4 class="group inner list-group-item-heading"><%out.print(((Insertion)request.getSession().getAttribute("insertion")).getName()); %></h4>
						<p class="group inner list-group-item-text"><% out.print(((Insertion)request.getSession().getAttribute("insertion")).getDescription());%></p>
						<div class="row">
							<div class="col-xs-12 col-md-6">
								<p class="lead"><% out.print(((Insertion)request.getSession().getAttribute("insertion")).getPrice()+" &euro;");%></p>
							</div>
							<div class="col-xs-12 col-md-2">
								<a class="btn btn-success" href="#">Add
									to cart</a>
							</div>
							<div class="col-xs-12 col-md-2">
								<a class="btn btn-info" href="item_Selected?id_item=<%out.print(((Insertion)request.getSession().getAttribute("insertion")).getId_item());%>">Go to Item</a>
							</div>
							<div class="col-xs-12 col-md-2">
								<a id="removeButton_itm_<%out.print(((Insertion)request.getSession().getAttribute("insertion")).getId_item());%>" class="btn btn-danger rm-wishlist" data-href="removeItem?id_item=<%out.print(((Insertion)request.getSession().getAttribute("insertion")).getId_item());%>">Remove</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>