<%@page import="java.util.HashMap"%>
<%@page import="elements.Insertion"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery.js"></script>

<script type="text/javascript" src="scripts/logicScript.js"></script>
<script src="scripts/guiScript.js"></script>
<script type="text/javascript" src="scripts/searchScript.js"></script>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link href="css/styleItem.css" rel="stylesheet" type="text/css" />
<link href="css/styleSearch.css" rel="stylesheet" type="text/css" />
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/customStyle.css" rel="stylesheet" type="text/css" />
<link href="css/heroic-features.css" rel="stylesheet" type="text/css" />
<script src="scripts/modernizr.js"></script>
<!-- Modernizr -->

<script src="scripts/main.js"></script>

</head>
<body>
	<%
	HashMap<Insertion, List<String>> insertions = (HashMap<Insertion, List<String>>) request.getSession()
	.getAttribute("insertions");
	int limit=6;
	int current=0;
	int limitMin=0;
	int limitMax=0;
	int currentPage=1;
	int size=insertions.size();
		String category = request.getSession().getAttribute("searchedCategory").toString();
	if(request.getSession().getAttribute("loaded")==null)
	{		
	request.getSession().setAttribute("loaded", "true");
	limitMin=current*limit;
	limitMax=limit-1;
	}
	else{
		 currentPage=Integer.parseInt(request.getSession().getAttribute("currentPage").toString());
		limitMin=(currentPage-1)*limit;
		limitMax=limitMin+limit-1;
		
	}
	%>
	<%@ include file="/template/navbar.jsp"%>

	<div class="container">
		<!-- <input id="clickMe" type="button" value="clickme"
			onclick="provaAdd();" /> -->
		<div class="well well-sm">
			<strong style="font-size: 1.3em;">Risultati trovati in: </strong><strong>
				<%
					out.print(category);
				%>
			</strong>
		</div>
		
		<div id="products" class="row list-group">
			<%
			
			int i=0;
				for (Insertion insertion : insertions.keySet()) { // stabilire prima il risultato della ricerca
					request.getSession().setAttribute("currentInsertion", insertion);
					request.getSession().setAttribute("currentImages", insertions.get(insertion));
					if(i>=limitMin&&i<=limitMax)
					{
			%>
		
			<%@ include file="template/searchItem.jsp"%>
			<%}
			i++;
				}
				request.getSession().setAttribute("limitMin", limitMin);

			
			%>

		</div>
		<%if(size==0){ %>
		<div class=" col-md-offset-4 container  thumbnail">
		<p style="font-size: 2em; text-align: center;">La ricerca non ha prodotto risultati</p>
		</div>
		<%} %>
		<nav aria-label="Page navigation" style="text-align:center;">
  <ul class="pagination" id="pagination">

    <% int pages=size/limit;
    if(size%limit!=0)
    	pages++;
    
    for(int j=1;j<=pages;j++){ 
    	{
    %>
    
    <li <%if(j==currentPage) out.print("class=\"active\""); %>><a><%out.print(j); %></a></li>
     <%} }%>
  </ul>
</nav>
	</div>
	
	<%@ include file="/template/checkSession.jsp"%>
</body>
</html>