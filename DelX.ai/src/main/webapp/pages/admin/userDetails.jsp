<%@page import="utils.StringUtils"%>
<%@page import="model.UserModel"%>
<%@page import="controller.database.DBController"%>
<%@page import="model.Catalog"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Category"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String contextPath = request.getContextPath();
String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);
DBController dbController = new DBController();
List<UserModel> user = dbController.getAllUsers();
request.setAttribute(StringUtils.LIST_USERS, user);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/userDetails.css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

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
			<!-- Page content -->
			<div id="page-content-wrapper">
				<form
					action="<%=contextPath + StringUtils.SERVLET_URL_USER_SEARCH%>"
					method="post">
					<div class="box">
						<i class="fa-brands fa-searching"></i> <input type="text"
							name="searchValue">

						<button type="submit">
							<i class="fa fa-search"> <!--  Some content -->
							</i> Search
						</button>
					</div>
				</form>

				<div class="table-wrapper">
					<h3 style="color: white;">Tools Details:</h3>
					<table class="fl-table">
						<thead>
							<tr>
								<th>UserId</th>
								<th>firstName</th>
								<th>lastName</th>
								<th>UserName</th>
								<th>dob</th>
								<th>gmail</th>
								<th>gender</th>
								<th>Avatar</th>
								<th>userType</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty userList}">
								<c:forEach var="user" items="${userList}">
									<tr>
										<td>${user.userID}</td>
										<td>${user.firstName}</td>
										<td>${user.lastName}</td>
										<td>${user.username}</td>
										<td>${user.dob}</td>
										<td>${user.email}</td>
										<td>${user.gender}</td>
										<td><img
											src="${pageContext.request.contextPath}/resources/user/${user.imageUrlFromPart}"
											width="120px" height="120px" alt="picture"></td>
										<td>${user.userType}</td>
										<td><a href="#popup1?id=${user.userID }"> Change </a></td>
									</tr>
									<div id="popup1?id=${user.userID}" class="overlay">
										<div class="popup">
											<h2>Change Role</h2>
											<a class="close" href="#">&times;</a>
											<div class="content">
												<div class="form_container">
													<form name="form" action="<%=contextPath%>/addtool"
														method="post" enctype="multipart/form-data">
														<input type="hidden" name="operations" value="addtools">

														<div class="form_wrap">
															<div class="form_item">
																<label>User Role:</label> <select name="role" id="">
																	<option value="normal">normal</option>
																	<option value="admin">admin</option>
																</select>
															</div>
														</div>

														<div class="btn">
															<input type="submit" value="Save Changes">
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</c:if>

						</tbody>

					</table>
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