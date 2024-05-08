<%@page import="utils.StringUtils"%>
<%@page import="model.UserModel"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String contextPath = request.getContextPath();
UserModel user = (UserModel) session.getAttribute("username");
String currentUser = (user != null) ? user.getUsername() : null;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/footer.css" />
<!--FONT AWESOME-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!--GOOGLE FONTS-->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Fredoka+One&family=Play&display=swap"
	rel="stylesheet">
</head>
<body>

	<footer>
		<div class="footer">
			<div class="row">
				<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
					class="fa fa-instagram"></i></a> <a href="#"><i
					class="fa fa-youtube"></i></a> <a href="#"><i class="fa fa-twitter"></i></a>
			</div>

			<div class="row">
				<ul>
					<li><a href="#">Contact us</a></li>
					<li><a href="#"
						onclick="document.getElementById('catalogForm').submit(); return false;">Catalog</a>
						<form id="catalogForm"
							action="${pageContext.request.contextPath}${StringUtils.SERVLET_URL_USER_CATALOG}"
							method="get" style="display: none;"></form></li>
					<li><a href="<%=contextPath + StringUtils.URL_HOME%>">Home</a></li>
					<%
					if (currentUser == null) {
					%>
					<li><a href="#">Terms & Conditions</a></li>
					<%
					}
					if (currentUser != null && user.getUserType().equals("admin")) {
					%>
					<li><a href="#"
						onclick="document.getElementById('adminForm').submit(); return false;">Admin</a>
						<form id="adminForm"
							action="${pageContext.request.contextPath}${StringUtils.SERVLET_ADMIN}"
							method="get" style="display: none;"></form></li>
					<%
					}
					%>
				</ul>
			</div>

			<div class="row">DELX Copyright © 2024 Inferno - All rights
				reserved || Designed By: Rasik Kayastha</div>
		</div>
	</footer>
</body>
</html>