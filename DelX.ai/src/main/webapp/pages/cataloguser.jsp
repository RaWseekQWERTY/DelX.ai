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
		<form class="search">
			<input type="text" placeholder="Chatgpt-3.5" class="search-box" />
			<button class="btn btn-primary">Search</button>
		</form>


		<div class="categories">
			<c:if test="${empty categoryList }">
				<div class="maintance-container">
					<img alt="" src="<%=contextPath%>/resources/other/UnderMain.svg">
				</div>

			</c:if>
			<c:forEach var="cat" items="${categoryList}">
				<p class="category-item">
					<a href="#${cat.categoryName}">${cat.categoryName}</a>
				</p>
			</c:forEach>
		</div>

		<div class="main-product" id="featured">
			<c:if test="${empty categoryList }">
				<div class="maintance-container">
					<img alt="" src="<%=contextPath%>/resources/other/UnderMain.svg">
				</div>

			</c:if>
			<c:forEach var="cat" items="${categoryList}">
				<h1 id="${cat.categoryName}">${cat.categoryName}</h1>
				<!-- Display the category title -->

				<c:forEach var="tool" items="${cat.catalogs}">
					<div class="product-container">
						<a>
							<div class="product">
								<img
									src="${pageContext.request.contextPath}/resources/catalog/${tool.imageUrlFromPart}"
									alt="" width="150px" height="150px" />
								<section class="product-detail">
									<h4>${tool.toolName}</h4>
									<p>${tool.toolDesc}</p>
									<p>${tool.category.categoryName}</p>
									<!-- You can display the category title here -->
									<p>${tool.toolAuthor}</p>
								</section>
							</div>
						</a>
					</div>
				</c:forEach>
			</c:forEach>
		</div>







		<%-- 	<div class="main-product" id="featured">

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

			</c:forEach>

		</div> --%>
		<jsp:include page="<%=StringUtils.PAGE_URL_FOOTER%>" />
	</main>

</body>
</html>