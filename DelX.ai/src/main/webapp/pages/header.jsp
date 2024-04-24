<%@page import="utils.StringUtils"%>
<%@page import="model.UserModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
// Get the session and request objects
String contextPath = request.getContextPath();
HttpSession userSession = request.getSession();
UserModel user = (UserModel) userSession.getAttribute("user");
String currentUser = null;

if (user != null) {
	currentUser = user.getUsername();
} else {
	Object attribute = userSession.getAttribute(StringUtils.USERNAME);
	if (attribute != null) {
		currentUser = attribute.toString();
	} else {
		// Handle the case when no user is logged in
		currentUser = null;
	}
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/header.css" />
</head>
<body>
	<header class="header">
		<a href="<%=contextPath + StringUtils.URL_HOME%>" class="logo"> <img
			src="./resources/other/logo.png" alt="logo" width="50px"
			height="50px">
		</a> <input class="menu-btn" type="checkbox" id="menu-btn" /> <label
			class="menu-icon" for="menu-btn"><span class="navicon"></span></label>
		<ul class="menu">
			<li><a href="#work">Our Work</a></li>
			<li><a href="#about">About</a></li>
			<li><a href="#careers">Careers</a></li>
			<li><a href="#contact">Contact</a></li>
			<%
			if (currentUser != null) {
			%>
			<li><a href="<%=contextPath + StringUtils.PAGE_URL_PROFILE%>">
					<button class="btn btn-nav btn-in">Profile</button>
			</a></li>
			<%
			} else {
			%>
			<%-- If currentUser is null (user is logged out), show Login and Signup buttons --%>
			<li><a href="<%=contextPath + StringUtils.PAGE_URL_LOGIN%>">
					<button class="btn btn-nav btn-in">Login</button>
			</a></li>
			<li><a href="<%=contextPath + StringUtils.PAGE_URL_REGISTER%>">
					<button class="btn btn-nav">Signup</button>
			</a></li>
			<%
			}
			%>
		</ul>
		<!-- <div class="profile">
                <h1>O</h1>
            </div> -->
	</header>

</body>
</html>