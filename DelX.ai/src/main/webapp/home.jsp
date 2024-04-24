<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" text="text/css"
	href="<%=contextPath%>/stylesheets/home.css">
<meta charset="ISO-8859-1">
<title>DelX.ai | Home</title>
</head>
<body>
	<jsp:include page="<%=StringUtils.PAGE_URL_HEADER%>" />

	<!-- main body -->
	<main class="body">
		<section class="desc">
			<h2>Explore, Subscribe, Stay Informed</h2>
			<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Saepe
				voluptatum sed suscipit temporibus aliquid, cumque quaerat est et
				asperiores ipsam, quia eveniet. Corrupti recusandae ab possimus
				corporis facilis veniam esse.</p>
			<button class="btn btn-primary">Get Started</button>
		</section>
		<section class="hero-img">
			<img src="./resources/other/BG-landing.png" alt="">
		</section>

	</main>
</body>
</html>