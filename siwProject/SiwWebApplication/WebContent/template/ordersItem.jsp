<%@page import="elements.Insertion"%>
<tr>
	<td class="col-md-1" ></td>
	<td class="col-md-7  text-left" >
		<div class="media">
			<div class="media-body">
				<h4 class="media-heading">
					<a class="go-to-item" style="cursor: pointer;"  id="<%=((Insertion) request.getSession().getAttribute("currentOrder")).getId_item()%>"><%out.print(((Insertion)request.getSession().getAttribute("currentOrder")).getName()); %></a>
				</h4>
			</div>
		</div>
	</td>

	<td class="col-sm-1 col-md-4 text-left"><span>Status: </span><span
		class="text-success"><strong>Completed</strong></span></td>
</tr>