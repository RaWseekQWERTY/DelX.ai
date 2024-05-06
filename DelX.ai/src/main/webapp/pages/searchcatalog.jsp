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
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DelX.ai || Catalog</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/cataloguser.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

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
		<form class="search"
			action="<%=contextPath + StringUtils.SERVLET_URL_CATALOG_SEARCH%>"
			method="post">
			<input type="text" placeholder="Chatgpt-3.5" class="search-box"
				name="${StringUtils.SEARCH}" />
			<button class="btn btn-primary">Search</button>
		</form>

		<div class="main-product" id="featured">
			<c:choose>
				<c:when test="${empty toolList}">
					<p>No tools found with that name.</p>
				</c:when>
				<c:otherwise>
					<c:forEach var="tool" items="${toolList}">
						<div class="product-container">
							<div class="product">
								<img
									src="${pageContext.request.contextPath}/resources/catalog/${tool.imageUrlFromPart}"
									alt="" width="150px" height="150px" />
								<section class="produt-detail">
									<h4>${tool.toolName}</h4>
									<p>${tool.toolDesc}</p>
									<p>${tool.category.categoryName}</p>
									<p>${tool.toolAuthor}</p>
								</section>
							</div>
						</div>
						<div id="popup1?name=${tool.toolName}" class="overlay">
							<div class="popup">
								<a class="close" href="#">&times;</a>
								<div class="content">
									<img
										src="${pageContext.request.contextPath}/resources/catalog/${tool.imageUrlFromPart}"
										alt="" class="content-img" width="100px" height="100px">
									<h2>${tool.toolName}</h2>
									<div class="author">
										<h4>By :- ${tool.toolAuthor}</h4>
									</div>
									<div class="product-desc">${tool.toolDesc}</div>
									<div class="stats">
										<section class="rating">
											<h3>4.1</h3>
											<p>Ratings (1K+)</p>
										</section>
										<div class="vl"></div>
										<section>
											<h3>#${tool.catalogID}</h3>
											<p>${tool.category.categoryName}</p>
										</section>
										<div class="vl"></div>
										<section>
											<h3>200k+</h3>
											<p>Conversations</p>
										</section>
									</div>
								</div>
								<button class="subscribe">Subscribe</button>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>

		<jsp:include page="<%=StringUtils.PAGE_URL_FOOTER%>" />
	</main>

</body>
</html>