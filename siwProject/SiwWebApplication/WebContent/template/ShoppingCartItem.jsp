
<%@page import="java.util.List"%>
<%@page import="elements.Sales_type"%>
<%@page import="elements.Insertion"%>
<%@page import="java.util.HashMap"%>
<%@page import="elements.Order"%>
<tr>
	<td class="col-md-8">
		<div class="col-md-4">
			<img style="width: 50%; height: 50%;" class="media-object"
				src="<%if (!((List<String>) request.getSession().getAttribute("currentImage")).isEmpty()) {
				out.print("ServletImage?id_item="
						+ ((Order) request.getSession().getAttribute("currentOrder")).getId_insertion() + "&nameImage="
						+ ((List<String>) request.getSession().getAttribute("currentImage")).get(0));
			} else {
				out.print("images/default.jpg");
			}%>"
				style="width: 72px; height: 72px;">


		</div>
		<div class=" col-md-8">
				<div class=" col-md-12" style="text-align: left ;padding-top: 10%;">
				<span style="font-size: 1.5em;">Name: </span>
					<a class="go-to-item" style="cursor: pointer; font-size: 1.5em;"
						id="<%=((Insertion) request.getSession().getAttribute("insertion")).getId_item()%>">
					
						<%
							out.print(((HashMap<Order, Insertion>) request.getSession().getAttribute("orders"))
									.get(((Order) request.getSession().getAttribute("currentOrder"))).getName());
						%>
					</a>
					</div>
							<div class=" col-md-12" style=" text-align: left ;" >
				<span style="font-size: 1.5em;">Status: </span>
				<%
					if (((HashMap<Order, Insertion>) request.getSession().getAttribute("orders"))
							.get(((Order) request.getSession().getAttribute("currentOrder"))).getAmount() > 0) {
				%>
				<span class="text-success" style="font-size: 1.5em;">Available<strong> <%
 	} else {
 %> <span class="text-danger" style="font-size: 1.5em;">Not available<strong> <%
 	}
 %>

						</strong></span>
						</div>
						</div>
		

	</td>

	<td class="col-sm-1 col-md-1 text-center"><strong>&euro;
			<%
				out.print(((HashMap<Order, Insertion>) request.getSession().getAttribute("orders"))
						.get(((Order) request.getSession().getAttribute("currentOrder"))).getPrice());
			%>
	</strong></td>
	<td class="col-sm-1 col-md-1">
		<button type="button"
			<%if (((HashMap<Order, Insertion>) request.getSession().getAttribute("orders"))
					.get(((Order) request.getSession().getAttribute("currentOrder")))
					.getSales_type() == Sales_type.asta)
				out.print("disabled");%>
			class="btn btn-danger remove-button-shoppingCart"
			id="<%out.print(((Order) request.getSession().getAttribute("currentOrder")).getId_order());%>">
			<span class="glyphicon glyphicon-remove"></span> Remove
		</button>
	</td>
</tr>