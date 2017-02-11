<%@page import="elements.Insertion"%>

<div class="item  col-xs-4 col-lg-4">
	<div class="thumbnail">
		<img class="group list-group-image"
			src="http://placehold.it/400x250/000/fff" alt="" />
		<div class="caption">
			<h4 class="group inner list-group-item-heading">
				<%
					out.print(((Insertion) request.getSession().getAttribute("currentInsertion")).getName());
				%>
			</h4>
			<p class="group inner list-group-item-text">
				<%
					out.print(((Insertion) request.getSession().getAttribute("currentInsertion")).getDescription());
				%>

			</p>
			<div class="row">
				<div class="col-xs-12 col-md-5">
					<p class="lead">
						<%
							out.print(((Insertion) request.getSession().getAttribute("currentInsertion")).getPrice());
						%>
					</p>
				</div>
						<div class="col-xs-12 col-md-3">
					<a class="btn btn-info go-to-item" id="<%=((Insertion) request.getSession().getAttribute("currentInsertion")).getId_item()%>" >Go to item</a>
				</div>
			<div class="col-xs-12 col-md-3" >
					<a class="btn btn-success">Add
						to cart</a>
				</div>


			</div>

			
		</div>
	</div>
</div>

