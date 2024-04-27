<%@page import="utils.StringUtils"%>
<%@page import="controller.database.DBController"%>
<%@page import="model.Catalog"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Category"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String contextPath = request.getContextPath();
DBController dbController = new DBController();
List<Catalog> toolList = dbController.getAllTools();
request.setAttribute(StringUtils.LIST_TOOLS, toolList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DelX.ai || Catalog</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/cataloguser.css">
</head>
<body>
	<jsp:include page="<%=StringUtils.PAGE_URL_HEADER%>" />
	<main class="body">
		<div>
			<h1 class="heading">Itools</h1>
			<hr class="line" />
			<p class="description">Discover and create custom versions of
				ChatGPT that combine instructions, extra knowledge, and any
				combination of skills.</p>
		</div>
		<form class="search">
			<input type="text" placeholder="Chatgpt-3.5" class="search-box" />
			<button class="btn btn-primary">Search</button>
		</form>
		<div class="categories">
			<p class="category-item">
				<a href="#featured"> Featured</a>
			</p>
			<p class="category-item">
				<a href="#writing">Writing</a>
			</p>
			<p class="category-item">
				<a href="#chatbots">ChatBots</a>
			</p>
			<p class="category-item">
				<a href="#programming">Programming</a>
			</p>
		</div>

		<div class="main-product" id="featured">
			<h1>Featured</h1>
			<div class="product-container">
				<div class="product">
					<img src="./resources/other/logo.png" alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].
							Generates Functional Multipage Websites [in BETA]. Generates
							Functional Multipage Websites [in BETA]. Generates Functional
							Multipage Websites [in BETA]. Generates Functional Multipage
							Websites [in BETA]. Generates Functional Multipage Websites [in
							BETA].</p>
					</section>
				</div>
				<div class="product">
					<img src="./resources/other/logo.png" alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].</p>
					</section>
				</div>
				<div class="product">
					<img src="./resources/other/logo.png" alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].</p>
					</section>
				</div>
				<div class="product">
					<img src="./resources/other/logo.png" alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].</p>
					</section>
				</div>
			</div>
		</div>
		<div class="main-product" id="writing">
			<h1>Writing</h1>
			<div class="product-container">
				<div class="product">
					<img src="./resources/other/logo.png" alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].</p>
					</section>
				</div>
				<div class="product">
					<img src="./resources/other/logo.png" alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].</p>
					</section>
				</div>
				<div class="product">
					<img src="./resources/other/logo.png" alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].</p>
					</section>
				</div>
				<div class="product">
					<img src="./resources/other/logo.png" alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].</p>
					</section>
				</div>
			</div>
		</div>
		<div class="main-product" id="chatbots">
			<h1>ChatBots</h1>
			<div class="product-container">
				<div class="product">
					<img src="./resources/other/logo.png" alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].</p>
					</section>
				</div>
				<div class="product">
					<img src="./resources/other/logo.png" alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].</p>
					</section>
				</div>
				<div class="product">
					<img src="./resources/other/logo.png"alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].</p>
					</section>
				</div>
				<div class="product">
					<img src="./resources/other/logo.png" alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].</p>
					</section>
				</div>
			</div>
		</div>
		<div class="main-product" id="programming">
			<h1>Programming</h1>
			<div class="product-container">
				<div class="product">
					<img src="./resources/other/logo.png" alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].</p>
					</section>
				</div>
				<div class="product">
					<img src="./resources/other/logo.png" alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].</p>
					</section>
				</div>
				<div class="product">
					<img src="./resources/other/logo.png" alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].</p>
					</section>
				</div>
				<div class="product">
					<img src="./resources/other/logo.png" alt="" width="150px"
						height="150px" />
					<section class="produt-detail">
						<h4>Instant Website</h4>
						<p>Generates Functional Multipage Websites [in BETA].</p>
					</section>
				</div>
			</div>
		</div>
	</main>

</body>
</html>