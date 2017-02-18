<tr>
	<td class="col-md-1" ></td>
	<td class="col-md-7  text-left" >
		<div class="media">
			<div class="media-body">
				<h4 class="media-heading">
					<a href="#"><%out.print(request.getSession().getAttribute("currentOrder")); %></a>
				</h4>
			</div>
		</div>
	</td>

	<td class="col-sm-1 col-md-4 text-left"><span>Status: </span><span
		class="text-success"><strong>In Stock</strong></span></td>
</tr>