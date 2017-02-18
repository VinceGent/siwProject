
<%@page import="elements.Insertion"%>
<%@page import="java.util.HashMap"%>
<%@page import="elements.Order"%>
<tr>
	<td class="col-md-8">
		<div class="media">
			<a class="thumbnail pull-left" href="#"> <img
				class="media-object"
				src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png"
				style="width: 72px; height: 72px;">
			</a>
			<div class="media-body">
				<h4 class="media-heading">
					<a href="#"> <%
 	out.print(((HashMap<Order, Insertion>) request.getSession().getAttribute("orders"))
 			.get(((Order) request.getSession().getAttribute("currentOrder"))).getName());
 %>
					</a>
				</h4>
				<h5 class="media-heading">
					by <a href="#">Brand name</a>
				</h5>
				<span>Status: </span>
				<%
					if (((HashMap<Order, Insertion>) request.getSession().getAttribute("orders"))
							.get(((Order) request.getSession().getAttribute("currentOrder"))).getAmount() > 0) {
				%>
				<span class="text-success">Available<strong> <%
 	} else {
 %>
						<span class="text-danger">Not available<strong> <%
 	}
 %>

						</strong></span>
			</div>
		</div>
	</td>

	<td class="col-sm-1 col-md-1 text-center"><strong>&euro; <%  out.print(((HashMap<Order, Insertion>) request.getSession().getAttribute("orders"))
							.get(((Order) request.getSession().getAttribute("currentOrder"))).getPrice());%></strong></td>
	<td class="col-sm-1 col-md-1">
		<button  type="button" class="btn btn-danger remove-button-shoppingCart" id="<%out.print(((Order) request.getSession().getAttribute("currentOrder")).getId_order());%>">
			<span class="glyphicon glyphicon-remove"></span> Remove
		</button>
	</td>
</tr>