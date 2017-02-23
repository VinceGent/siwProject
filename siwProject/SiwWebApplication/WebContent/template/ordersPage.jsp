
<%@page import="elements.Insertion"%>
<%@page import="java.util.List"%>
<%List<Insertion>insertion=(List<Insertion>)request.getSession().getAttribute("orderInsertion"); %>

<div class="box-title-main row col-md-12">
	<h2 style="text-align: center;">Lista Ordini</h2>
</div>
<div class="row">
	<div class="col-md-7 text-left col-md-offset-1"><h3>Product</h3></div>
						<div class="col-md-4 text-left"><h3>State</h3></div>
						</div>
<div>
	<div class="row">
		<div class="col-md-12"
			style="background-color: white; height: 24em; overflow: auto; overflow-x: hidden;">
			<table class="table table-hover">
				<thead>
					<tr>
						<%
							for (Insertion ins: insertion) {
								request.getSession().setAttribute("currentOrder", ins);
						%>
						<%@include file="ordersItem.jsp"%>
						<%
							}
						%>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
