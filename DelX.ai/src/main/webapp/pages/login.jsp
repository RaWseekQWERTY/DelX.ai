<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String contextPath = request.getContextPath();
String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);
String username = (String) request.getAttribute(StringUtils.USERNAME);
String successParam = request.getParameter(StringUtils.SUCCESS);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delx | Login</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/login.css" />
</head>
<body>
	<div class="nav">
		<a href="<%=contextPath + StringUtils.URL_HOME%>" class="logo"> <img
			src="../resources/other/logo.png" alt="logo" width="50px"
			height="50px"></a>

	</div>
	<div class="container">
		<form action="<%=contextPath + StringUtils.SERVLET_URL_LOGIN%>"
			method="post">
			<h3>Login</h3>

			<label for="username">Username</label> <input type="text"
				placeholder="Email or Phone" id="username" name="username"
				value="<%if (username != null && !username.isBlank()) {
	out.println(username);
}%>"
				required> <label for="password">Password</label> <input
				type="password" placeholder="Password" id="password" name="password"
				required>
			<%
			if (request.getAttribute("errorMessage") != null) {
			%>
			<p style="color: red;"><%=request.getAttribute("errorMessage")%></p>
			<%
			}
			%>
			<%
			if (session.getAttribute(StringUtils.MESSAGE_ERROR) != null) {
			%>
			<p style="color: red;"><%=session.getAttribute(StringUtils.MESSAGE_ERROR)%></p>
			<%
			}
			%>
			<p>
				Don't have an account? <a
					href="<%=contextPath + StringUtils.PAGE_URL_REGISTER%>">Signup</a>
			</p>
			<a href="#" class="forgot">Forgot Password</a>


			<button>Log In</button>
			<%
			if (successParam != null && successParam.equals(StringUtils.TRUE)) {
			%>
			<h2 class="success-msg" style="color: green;">Registration
				Successful!</h2>
			<%
			}
			%>
		</form>
	</div>
</body>
</html>