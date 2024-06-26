<%@page import="utils.StringUtils"%>
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
<title>ADMIN || CATEGORY</title>
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
						id="main_icon" class="icon"></span></a></li>
			</ul>
			<ul class="sidebar-nav" id="sidebar">
				<li><a href="#"
					onclick="document.getElementById('adminForm').submit(); return false;">Dash</a>
					<form id="adminForm"
						action="<%=contextPath + StringUtils.SERVLET_ADMIN%>" method="get"
						style="display: none;"></form></li>
				<li><a href="#"
					onclick="document.getElementById('catalogForm').submit(); return false;">Catalog</a>
					<form id="catalogForm"
						action="${pageContext.request.contextPath}${StringUtils.SERVLET_ADMIN_CATALOG}"
						method="get" style="display: none;"></form></li>
				<li><a href="#"
					onclick="document.getElementById('userForm').submit(); return false;">USER</a>
					<form id="userForm"
						action="${pageContext.request.contextPath}${StringUtils.SERVLET_USER_ADMIN}"
						method="get" style="display: none;"></form></li>
				<li><a href="<%=contextPath%>/pages/admin/categoryDetails.jsp">Category<span
						class="cat"></span></a></li>
			</ul>
		</div>

		<!-- Page content -->
		<div id="page-content-wrapper">
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
										<label>CategoryName:</label> <input type="text"
											name="catTitle" required>

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
		function confirmDelete(toolId, toolName) {
			if (confirm("Are you sure you want to delete this Tool: "
					+ toolName + "?")) {
				document.getElementById("deleteForm-" + toolId).submit();
			}
		}
	</script>
</body>
</html>