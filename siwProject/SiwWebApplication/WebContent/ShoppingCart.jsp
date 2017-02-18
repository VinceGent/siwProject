<%@page import="elements.OrderState"%>
<%@page import="elements.Insertion"%>
<%@page import="java.util.HashMap"%>
<%@page import="elements.Order"%>
<%@page import="java.util.ListResourceBundle"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="scripts/jquery.js"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>


<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/customStyle.css" rel="stylesheet" type="text/css" />
<link href="css/heroic-features.css" rel="stylesheet" type="text/css" />
<script src="scripts/modernizr.js"></script>
<!-- Modernizr -->
<script src="scripts/main.js"></script>
<!-- Gem jQuery -->
<script type="text/javascript" src="scripts/guiScript.js"></script>
<script type="text/javascript" src="scripts/logicScript.js"></script>


<title>Your cart</title>
</head>
<body>
	<%@ include file="template/navbar.html"%>

	<div class="container">
		<div class="row">
			<div class="col-md-10 col-md-offset-1"
				style="background-color: white;">
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="col-md-8 text-left"><h3>Product</h3></th>
							<th class="col-md-1 text-center"><h3>Price</h3></th>
							<th class="col-md-1 text-center"><h3>Action</h3></th>
						</tr>
					</thead>
					<tbody>
						<%
							HashMap<Order, Insertion> orders = (HashMap<Order, Insertion>) request.getSession().getAttribute("orders");
							int total = 0;
							for (Order order : orders.keySet()) {
								if (order.getState().equals(OrderState.pagato))
									continue;
								request.getSession().setAttribute("currentOrder", order);
						%>

						<%@ include file="template/ShoppingCartItem.jsp"%>
						<%
							if (orders.get(order).getAmount() > 0) {
									total += orders.get(order).getPrice();
								}
							}
						%>

						<tr>
							<td class="col-md-8 text-center"> </td>
							<td class="col-md-1 text-center"><h3>Total</h3></td>
							<td class="col-md-1 text-center"><h3>
									<strong> &euro;  <%
 	out.print(total);
 %>   
									</strong>
								</h3></td>
						</tr>
						<tr>

							<td class="col-md-8 text-left">
								<button type="button" class="btn btn-default">
									<span class="glyphicon glyphicon-shopping-cart"></span>
									Continue Shopping
								</button>
							</td>
							<td class="col-md-1 text-center"> </td>

							<td class="col-md-1 text-center">
								<button id="pay-all-button" type="button"
									class="btn btn-success"
									<%if (total == 0)
				out.print("disabled");%>>
									Pay all <span class="glyphicon glyphicon-play"></span>
								</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>

<%@ include file="template/checkSession.jsp"%>
</html>