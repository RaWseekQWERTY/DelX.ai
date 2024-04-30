<%@page import="controller.database.DBController"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Category"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String contextPath = request.getContextPath();
DBController dbController = new DBController();
List<Category> categories = dbController.getAllCategories();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/category.css" />
</head>
<body>

	<div id="wrapper" class="active">

		<!-- Sidebar -->
		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<ul id="sidebar_menu" class="sidebar-nav">
				<li class="sidebar-brand"><a id="menu-toggle" href="#">Menu<span
						id="main_icon" class="icon"><img
							src="https://static.vecteezy.com/system/resources/previews/000/440/847/original/menu-vector-icon.jpg"
							alt="logo" width="30px" height="30px"></span></a></li>
			</ul>
			<ul class="sidebar-nav" id="sidebar">
				<li><a href="<%=contextPath%>/pages/admin/admin.jsp">Dash<span
						class="dash"></span></a></li>
				<li><a href="<%=contextPath%>/pages/admin/catalogDetails.jsp">Catalog<span
						class="catalog"></span></a></li>
				<li><a href="<%=contextPath%>/pages/admin/userDetails.jsp">Users<span
						class="user"></span></a></li>
				<li><a href="<%=contextPath%>/pages/admin/categoryDetails.jsp">Category<span
						class="cat"></span></a></li>
			</ul>
		</div>

		<!-- Page content -->
		<div id="page-content-wrapper">
			<br> <br> <a class="button" href="#popup1">ADD</a>
			<div class="table-wrapper">
				<h3 style="color: white;">Category Details:</h3>
				<table class="fl-table">
					<thead>
						<tr>
							<th>CategoryID</th>
							<th>CategoryName</th>
							<th>CategoryDesc</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<%
						if (categories == null || categories.isEmpty()) {
						%>
						<tr>
							<td colspan="4" style="font-size: 16px; font-weight: 500;">No
								Categories added!Please, add a category</td>
						</tr>
						<%
						} else {
						%>
						<%
						for (Category cat : categories) {
						%>
						<tr>
							<td><%=cat.getCategoryID()%></td>
							<td><%=cat.getCategoryName()%></td>
							<td><%=cat.getCategoryDesc()%></td>
						</tr>
						<%
						}
						%>
						<%
						}
						%>
					</tbody>
				</table>
			</div>

		</div>
	</div>
	<div id="popup1" class="overlay">
		<div class="popup">
			<h2>Add a new Category</h2>
			<a class="close" href="#">&times;</a>
			<div class="content">
				<div class="form_container">
					<form name="form" action="<%=contextPath%>/addtool" method="post">
						<input type="hidden" name="operations" value="addcategory">
						<div class="form_wrap form_grp">
							<div class="form_item">
								<label>CategoryName:</label> <input type="text" name="catTitle"
									required>

							</div>
						</div>
						<div class="form_wrap">
							<div class="form_item">
								<label>CategoryDescription</label>
								<textarea name="desc" rows="4" cols="30" style="resize: none;"></textarea>
							</div>
						</div>
						<div class="btn">
							<input type="submit" value="Add Category">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		document.getElementById("menu-toggle").addEventListener("click",
				function(e) {
					e.preventDefault();
					var wrapper = document.getElementById("wrapper");
					if (wrapper.classList.contains("active")) {
						wrapper.classList.remove("active");
					} else {
						wrapper.classList.add("active");
					}
				});
	</script>
</body>
</html>