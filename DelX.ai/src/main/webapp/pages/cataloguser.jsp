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
				<a href="#" onclick="submitForm('all')">All</a>
			</p>
			<c:forEach var="cat" items="${categoryList}">
				<p class="category-item">
					<a href="#" onclick="submitForm(${cat.categoryID})">${cat.categoryName}</a>
				</p>
			</c:forEach>
		</div>
		<h1>${categoryName}</h1>
		<div class="main-product" id="featured">

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

		</div>
	</main>
	<script>
    function submitForm(id) {
        var form = document.createElement('form');
        form.method = 'post';
        form.action = '<%=contextPath + StringUtils.SERVLET_URL_USER_CATALOG%>';
        
        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'id';
        input.value = id;
        
        form.appendChild(input);
        document.body.appendChild(form);
        form.submit();
    }
</script>
</body>
</html>