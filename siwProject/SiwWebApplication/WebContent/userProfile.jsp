<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile Page</title>

<!-- JQUERY & BOOTSTRAP  -->
<script type="text/javascript" src="scripts/jquery.js"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<!-- JQUERY & BOOTSTRAP END -->

<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/reset.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="scripts/profilePage.js"></script>
<link href="css/userProfile.css" rel="stylesheet" type="text/css" />
<link href="css/customStyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="scripts/logicScript.js"></script>
<script type="text/javascript" src="scripts/guiScript.js"></script>
<script type="text/javascript" src="scripts/userSettings.js"></script>
</head>
<body>
<%
	UserInformation info1 = (UserInformation) request.getSession().getAttribute("userinfo");
%>
	<%@include file="template/navbar.html"%>
	<div class="container">
		<div class="row profile">
			<div class="col-md-3">
				<div class="profile-sidebar sidebar-container">
					<!-- SIDEBAR USERPIC -->
					<div class="profile-userpic">
						<img src="img/missingImage.png" class="img-responsive" alt="">
					</div>
					<!-- END SIDEBAR USERPIC -->
					<!-- SIDEBAR USER TITLE -->
					<div class="profile-usertitle">
						<div class="profile-usertitle-name">
							<%
								out.print(info1.getName() + " " + info1.getSurname());
							%>
						</div>
						<div class="profile-usertitle-job">Developer</div>
					</div>
					<!-- END SIDEBAR USER TITLE -->
					<!-- SIDEBAR BUTTONS -->
					<div class="profile-userbuttons">
						<!--    <button type="button" class="btn btn-success btn-sm">Follow</button> -->
						<!--	<button type="button" class="btn btn-danger btn-sm">Message</button> -->
					</div>
					<!-- END SIDEBAR BUTTONS -->
					<!-- SIDEBAR MENU -->
					<div class="profile-usermenu">
						<ul class="nav">
							<li id="overview-button" class="active"><a href="#"> <i
									class="glyphicon glyphicon-home"></i> Overview
							</a></li>
							<li id="settings-button"><a href="#"> <i
									class="glyphicon glyphicon-cog"></i> Account Settings
							</a></li>
							<li id="order-button"><a href="#"> <i
									class="glyphicon glyphicon-shopping-cart"></i> Order
							</a></li>
							<li><a href="#"> <i class="glyphicon glyphicon-envelope"></i>
									Messages
							</a></li>
							<li><a href="#"> <i class="glyphicon glyphicon-flag"></i>
									Help
							</a></li>
						</ul>
					</div>
					<!-- END MENU -->
				</div>
			</div>
			<div class="col-md-9 ">
				<div class="profile-content settings-container">
					<div id="overview-div">
						<%@include file="template/profileOverview.jsp"%>
					</div>
					<div id="settings-div" class="hidden">
						<%@include file="template/settings.jsp"%>
					</div>
					<div id="order-div" class="hidden"></div>
					<div id="messages" class="hidden"></div>
					<!-- overview content end -->
				</div>


			</div>
		</div>
	</div>
	<br>
	<br>
	<%@include file="template/checkSession.jsp"%>
</body>
</html>