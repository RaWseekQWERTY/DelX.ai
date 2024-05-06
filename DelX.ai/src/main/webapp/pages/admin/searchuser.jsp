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
			<!-- Page content -->
			<div id="page-content-wrapper">
				<%
				if (successMsg != null) {
				%>
				<label> <input type="checkbox" class="alertCheckbox"
					autocomplete="off" />
					<div class="alert success">
						<span class="alertClose">&times;</span> <span class="alertText">
							<%
							out.println(successMsg);
							%><br class="clear" />
						</span>
					</div>
				</label>
				<%
				}
				%>
				<%
				if (errMsg != null) {
				%>
				<label> <input type="checkbox" class="alertCheckbox"
					autocomplete="off" />
					<div class="alert error">
						<span class="alertClose">&times;</span> <span class="alertText">
							<%
							out.println(errMsg);
							%><br class="clear" />
						</span>
					</div>
				</label>
				<%
				}
				%>
				<form
					action="<%=contextPath + StringUtils.SERVLET_URL_USER_SEARCH%>"
					method="post">
					<div class="box">
						<i class="fa-brands fa-searching"></i> <input type="text"
							name="searchValue">

						<button type="submit">Search</button>
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
							<c:if test="${empty userList }">
								<tr>
									<td colspan="9" style="font-weight: bold; font-size: 19px;">404:
										User doesn't exist</td>
								</tr>

							</c:if>
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
										<td>Change</td>
									</tr>
								</c:forEach>
							</c:if>

						</tbody>

					</table>
				</div>
				<div id="popup1" class="overlay">
					<div class="popup">
						<h2>Change User Type</h2>
						<a class="close" href="#">&times;</a>
						<div class="content">
							<div class="form_container">
								<form name="form" action="<%=contextPath%>/modifyuser"
									method="post" enctype="multipart/form-data">
									<input type="hidden" name="operations" value="addtools">
									<div class="form_wrap">
										<div class="form_item">
											<label>Tool Category:</label> <select name="userType" id="">
												<option value="admin">admin</option>
												<option value="normal">normal</option>

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
		/*document.addEventListener('DOMContentLoaded', function() {
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
		});*/
	</script>
</body>
</html>