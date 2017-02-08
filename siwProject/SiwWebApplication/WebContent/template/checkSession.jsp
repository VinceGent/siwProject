
<%
	boolean logged=false;
	HttpSession sess = request.getSession(false);
	if (sess != null && sess.getAttribute("login") != null && sess.getAttribute("login").equals("logged")) {
	logged=true;	
%>
<script type="text/javascript">
	var username="<%=sess.getAttribute("username")%>";
	logUser(username);
</script>
<%
	} else {
%>
<script type="text/javascript">
	notLogged();
</script>
<%
	}
%>
