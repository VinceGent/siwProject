
<%@page import="java.util.List"%>
<%@page import="elements.Insertion"%>
<div class="col-md-3 col-sm-6 hero-feature">
	<div class="thumbnail">
		<img style="max-height: 200px; min-height: 200px;"
			src="<%if (!((List<String>) request.getSession().getAttribute("image")).isEmpty()) {
				out.print("ServletImage?id_item="
						+ ((Insertion) request.getSession().getAttribute("insertion")).getId_item() + "&nameImage="
						+ ((List<String>) request.getSession().getAttribute("image")).get(0));
			} else {
				out.print("images/default.jpg");
			}%>">
		<div class="caption" style="min-height: 180px; max-height: 180px;">
			<h3 style="overflow: hidden; max-height: 1em;">
				<%
					out.print(((Insertion) request.getSession().getAttribute("insertion")).getName());
				%>
			</h3>
			<p>
				<%
					out.print(((Insertion) request.getSession().getAttribute("insertion")).getCategory());
				%>
			</p>
			<p>
				<a class="btn btn-primary go-to-item" style="margin-bottom: 30px;"
					id="<%=((Insertion) request.getSession().getAttribute("insertion")).getId_item()%>">go
					to item!</a>
			</p>
		</div>
	</div>
</div>

