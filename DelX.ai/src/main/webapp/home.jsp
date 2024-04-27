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
			<p>Discover and manage AI tools effortlessly with DelX.ai.
				Explore a curated collection of cutting-edge AI tools, subscribe
				with ease, and stay ahead of the curve with the latest advancements
				in artificial intelligence. Simplify your AI journey with DelX.ai
				today.</p>
			<button class="btn btn-primary">Get Started</button>
		</section>
		<section class="hero-img">
			<img src="./resources/other/BG-landing.png" alt="">
		</section>
	</main>
	<section class="features">
		<div class="container">
			<div class="section-heading">
				<h2>Why choose DelX?</h2>
				<p>With us, your AI journey is streamlined and simplified,
					putting you in charge of your tech stack. Experience the future of
					AI subscription planning today.</p>
			</div>
			<div class="section-contant">
				<div class="features-grid">
					<div class="feature">
						<div class="feature-icon">
							<img src="./resources/other/Frame 3.png">
						</div>
						<div class="feature-title">
							<h3>Curated Selection</h3>
						</div>
						<div class="feature-description">
							<p>Access a curated collection of cutting-edge AI tools,
								handpicked for their quality and relevance.</p>
						</div>
					</div>
					<div class="feature">
						<div class="feature-icon">
							<img src="./resources/other/Frame 2.png" />
						</div>
						<div class="feature-title">
							<h3>Ease of Use</h3>
						</div>
						<div class="feature-description">
							<p>Subscribe and manage AI tools effortlessly through our
								user-friendly platform.</p>
						</div>
					</div>
					<div class="feature">
						<div class="feature-icon">
							<img src="./resources/other/Frame 4.png" />
						</div>
						<div class="feature-title">
							<h3>Stay Updated</h3>
						</div>
						<div class="feature-description">
							<p>Stay ahead of the curve with the latest advancements in
								artificial intelligence, all in one place.</p>
						</div>
					</div>
					<div class="feature">
						<div class="feature-icon">
							<img src="./resources/other/Frame 5.png" />
						</div>
						<div class="feature-title">
							<h3>Open API</h3>
						</div>
						<div class="feature-description">
							<p>Dive into the world of Open source contrubution like never
								before.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>