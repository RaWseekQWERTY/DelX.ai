<%@page import="javax.naming.ldap.PagedResultsResponseControl"%>
<%@page import="utils.StringUtils"%>
<%@page import="model.UserModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String contextPath = request.getContextPath();

UserModel user = (UserModel) session.getAttribute("username");

String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);

if (user == null) {
	session.setAttribute(StringUtils.MESSAGE_ERROR, "please login first");
	response.sendRedirect(contextPath + StringUtils.PAGE_URL_LOGIN);
	return;
} else {
	if (user.getUserType().equals("normal")) {
		session.setAttribute(StringUtils.MESSAGE_ERROR, "you don't have admin prieviliges");
		response.sendRedirect(contextPath + StringUtils.PAGE_URL_LOGIN);
		return;
	}
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADMIN || DASH</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/admin.css">

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
						action="${pageContext.request.contextPath}${StringUtils.SERVLET_ADMIN}"
						method="get" style="display: none;"></form></li>
				<li><a href="#"
					onclick="document.getElementById('catalogForm').submit(); return false;">Catalog</a>
					<form id="catalogForm"
						action="${pageContext.request.contextPath}${StringUtils.SERVLET_ADMIN_CATALOG}"
						method="get" style="display: none;"></form></li>
				<li><a href="#"
					onclick="document.getElementById('userForm').submit(); return false;">User</a>
					<form id="userForm"
						action="${pageContext.request.contextPath}${StringUtils.SERVLET_USER_ADMIN}"
						method="get" style="display: none;"></form></li>
				<li><a href="<%=contextPath%>/pages/admin/categoryDetails.jsp">Category<span
						class="cat"></span></a></li>
			</ul>
		</div>

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


			<div class="cards">
				<div class="card-single">

					<div class="card-content">
						<img alt=""
							src="https://static.vecteezy.com/system/resources/previews/000/439/863/original/vector-users-icon.jpg"
							width="150px" height="120px"> <br> <span>USERS</span>
						<br> <span>${userList.size()}</span>
					</div>
					<div>
						<span class="fas fa-users"></span>
					</div>
				</div>
				<div class="card-single">
					<div class='card-content'>
						<img alt=""
							src="https://static.vecteezy.com/system/resources/previews/000/439/792/original/vector-folder-icon.jpg"
							width="140px" height="120px"> <span>Categories</span><br>
						<span>${categoryList.size()}</span>
					</div>
					<div>
						<span class="fas fa-clipboard"></span>
					</div>
				</div>
				<div class="card-single">
					<div class="card-content">
						<img alt=""
							src="https://sbr-technologies.com/wp-content/uploads/2020/07/ai.jpg"
							width="160px" height="120px"> <br> <span>Tools</span><br>
						<span>${toolList.size()}</span>
					</div>
					<div>
						<span class="fas fa-shopping-bag"></span>
					</div>
				</div>
				<form action="<%=contextPath + StringUtils.SERVLET_URL_LOGOUT%>"
					method="post">
					<div class="card-single">
						<div>
							<br>
							<button title="logout"
								style="border: none; background-color: transparent;">
								<i class="fa-solid fa-right-from-bracket fa-2xl"></i>
							</button>
						</div>

						<div class="page-content inset">
							<div class="row"></div>
						</div>
					</div>


				</form>

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