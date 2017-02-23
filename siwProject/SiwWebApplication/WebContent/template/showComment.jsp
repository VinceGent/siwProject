<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="elements.Feedback"%>

<%
	List<Feedback> feedback = (List<Feedback>) request.getSession().getAttribute("feedbacks");
%>
<div class="row " id="comment-section">
	<div id="demo">
		<hr>
		<div class="container col-md-offset-4" id="section container" >
			<div class=" user-comment well panel panel-default">
				<h4>
					<i class="fa fa-paper-plane-o"></i>
				</h4>
				 <input
					id="input-1" name="input-1" class="rating rating-loading ratingVal"
					data-min="0" data-max="5" data-step="1" >
				<form role="form">
					<div class="form-group">
						<textarea class="form-control description" rows="3"
							placeholder="leave a comment..."></textarea>
					</div>
					<button type="button" name="say" class="btn btn-primary"
						id="provaFeedback">
						<i class="fa fa-reply"></i> Send
					</button>
				</form>
			</div>

			<hr>
			<%
				for (Feedback feed : feedback) {
			%>
			<div class="comment-warpper  panel-group ">
				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="fa fa-comment"></i>
						<%
							out.print(feed.getUsername());
						%>
						<div class="row lead">
							<div id="stars-existing" class="starrr col-md-3" style="color: #FFBF18"
								data-rating=<%out.print(feed.getRating());%>></div>
						</div>
					</div>
					<div class="panel-body">
						<p>
							<%
								out.print(feed.getDescription());
							%>
						</p>
					</div>
				</div>
			</div>
			<%
				}
			%>
		</div>
	</div>
</div>