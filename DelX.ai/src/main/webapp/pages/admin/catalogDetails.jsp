<%@page import="controller.database.DBController"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Category"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/CatalogDetails.css" />
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
				<li><a>Users<span class="user"></span></a></li>
				<li><a href="<%=contextPath%>/pages/admin/categoryDetails.jsp">Category<span
						class="cat"></span></a></li>
			</ul>
		</div>

		<!-- Page content -->
		<div id="page-content-wrapper">
			<a class="button" href="#popup1">ADD</a> <br> <br>
			<div class="table-wrapper">
				<h3 style="color: white;">Tools Details:</h3>
				<table class="fl-table">
					<thead>
						<tr>
							<th>CatalogID</th>
							<th>ToolName</th>
							<th>ToolCategory</th>
							<th>ToolDesc</th>
							<th>ToolAuthor</th>
							<th>ToolImg</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Content 1</td>
							<td>Content 1</td>
							<td>Content 1</td>
							<td>Content 1</td>
							<td>Content 1</td>
							<td>Content 1</td>
							<td><button>Edit</button>
								<button>Delete</button></td>
						</tr>
					<tbody>
				</table>
			</div>
			<div id="popup1" class="overlay">
				<div class="popup">
					<h2>Add a new Tool</h2>
					<a class="close" href="#">&times;</a>
					<div class="content">
						<div class="form_container">
							<form name="form" action="<%=contextPath%>/addtool" method="post">
								<input type="hidden" name="operations" value="addtools">
								<div class="form_wrap form_grp">
									<div class="form_item">
										<label>Tool Name</label> <input type="text" required>

									</div>
								</div>
								<div class="form_wrap">
									<div class="form_item">
										<label>Tool Author</label> <input type="text" required>
									</div>
								</div>
								<div class="form_wrap">
									<div class="form_item">
										<label>Tool Description</label>
										<textarea name="desc" rows="4" cols="50" style="resize: none;"></textarea>
									</div>
								</div>
								<!-- Category -->
								<%
								DBController dbController = new DBController();
								List<Category> categories = dbController.getAllCategories();
								%>
								<div class="form_wrap">
									<div class="form_item">
										<label>Tool Category:</label> <select name="catId" id="">
											<%
											for (Category cat : categories) {
											%>
											<option value="<%= cat.getCategoryID()%>"><%= cat.getCategoryName() %></option>

											<%
											}
											%>
										</select>
									</div>
								</div>
								<div class="form_wrap">
									<div class="form_item">
										<label>Tool Image</label> <input type="file" name="toolpic"
											accept="image/png, image/gif, image/jpeg">
									</div>
								</div>


								<div class="btn">
									<input type="submit" value="Add Tool">
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
		document.addEventListener('DOMContentLoaded', function() {
			var startBtns = document.querySelectorAll('.start-btn');
			var closeButton = document.querySelector('.fa-times');
			var modalBox = document.querySelector('.modal-box');

			// Function to toggle modal visibility
			function toggleModal() {
				modalBox.classList.toggle("show-modal");
				for (var i = 0; i < startBtns.length; i++) {
					startBtns[i].classList.toggle("show-modal");
				}
			}

			// Add click event listeners to start buttons
			startBtns.forEach(function(startBtn) {
				startBtn.addEventListener('click', toggleModal);
			});

			// Add click event listener to close button
			closeButton.addEventListener('click', toggleModal);
		});
	</script>
</body>
</html>