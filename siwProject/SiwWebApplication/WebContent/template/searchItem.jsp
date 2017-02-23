<%@page import="java.util.List"%>
<%@page import="elements.Sales_type"%>
<%@page import="elements.Insertion"%>

<div class="item col-xs-12 col-sm-6 col-md-4 col-lg-4">
	<div class="thumbnail"  >
		<img class="group list-group-image" style="min-height: 13em; max-height: 13em;"
			src="<%if (!((List<String>) request.getSession().getAttribute("currentImages")).isEmpty()) {
				out.print("ServletImage?id_item="
						+ ((Insertion) request.getSession().getAttribute("currentInsertion")).getId_item()
						+ "&nameImage=" + ((List<String>) request.getSession().getAttribute("currentImages")).get(0));
			} else {
				out.print("images/default.jpg");
			}%>"
			alt="" />
		<div class="caption">
			<h4 class="group inner list-group-item-heading">
				<%
					out.print(((Insertion) request.getSession().getAttribute("currentInsertion")).getName());
				%>
			</h4>
			<p class="group inner list-group-item-text">
				Go to Item for Description.
			</p>
			<div class="row">
				<div class="col-xs-12 col-md-5">
					<p class="lead">
						<%
							out.print(((Insertion) request.getSession().getAttribute("currentInsertion")).getPrice() + " &euro;");
						%>
					</p>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 col-md-6 col-lg-6">
					<a class="btn btn-info go-to-item"
						id="<%=((Insertion) request.getSession().getAttribute("currentInsertion")).getId_item()%>">Go
						to item</a>
				</div>
				<div class="col-xs-6 col-md-6 col-lg-6">
					<%
						if (((Insertion) request.getSession().getAttribute("currentInsertion")).getSales_type().toString()
								.equals(Sales_type.compraora.toString())) {
					%>
					<a class="btn btn-success button-addToCart"
						id="<%out.print(((Insertion) request.getSession().getAttribute("currentInsertion")).getId_item());%>">Add
						to cart</a>
					<%
						}
					%>
				</div>
			
			</div>

		</div>
	</div>
</div>

