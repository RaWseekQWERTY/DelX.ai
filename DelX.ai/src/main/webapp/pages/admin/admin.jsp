<%@page import="utils.StringUtils"%>
<%@page import="model.UserModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String contextPath = request.getContextPath();

UserModel user = (UserModel) session.getAttribute("username");

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
<title>Insert title here</title>
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
			<%
			String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
			String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);
			if (successMsg != null) {
			%>
			<label> <input type="checkbox" class="alertCheckbox"
				autocomplete="off" />
				<div class="alert success">
					<span class="alertClose">X</span> <span class="alertText">
						<%
						out.println(successMsg);
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
						<br> <span>123</span>
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
						<span>6</span>
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
						<span>20</span>
					</div>
					<div>
						<span class="fas fa-shopping-bag"></span>
					</div>
				</div>
				<div class="card-single">
					<div>
						<br> <span>ADD</span>
					</div>

					<div class="page-content inset">
						<div class="row"></div>
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
	</script>
</body>
</html>