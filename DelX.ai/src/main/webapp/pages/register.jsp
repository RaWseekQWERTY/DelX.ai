<%@page import="utils.StringUtils"%>
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
	href="<%=contextPath%>/stylesheets/register.css" />
</head>
<body>
	<div class="nav">
		<a href="<%=contextPath + StringUtils.URL_HOME%>" class="logo"> <img
			src="../resources/other/logo.png" alt="logo" width="50px"
			height="50px"></a>

	</div>
	<div class="container">
		<form action="<%=contextPath%>/registeruser" method="post">

			<h3>Sign Up</h3>

			<div class="row">
				<div class="col">
					<label for="firstName">First Name</label> <input type="text"
						placeholder="First Name" id="firstName" name="firstName" required>
				</div>
				<div class="col">
					<label for="lastName">Last Name</label> <input type="text"
						placeholder="Last Name" id="lastName" name="lastName" required>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<label for="username">Username</label> <input type="text"
						placeholder="Username" id="username" name="username" required>
				</div>
				<div class="col">
					<label for="gender">Gender</label> <select id="gender"
						name="gender">
						<option value="male">Male</option>
						<option value="female">Female</option>
						<option value="other">Other</option>
					</select>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<label for="email">Email</label> <input type="email"
						placeholder="Email" id="email" name="email" required>
				</div>
				<div class="col">
					<label for="dob">Date of Birth</label> <input type="date" id="dob"
						name="dob" required>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<label for="password">Password</label> <input type="password"
						placeholder="Password" id="password" name="password" required>
				</div>
				<div class="col">
					<label for="confirmPassword">Confirm Password</label> <input
						type="password" placeholder="Confirm Password"
						id="confirmPassword" name="conformPass" required>
				</div>
			</div>
			<p>
				Already have an account? <a
					href="<%=contextPath + StringUtils.PAGE_URL_LOGIN%>">Log In</a>
			</p>
			<button>Sign Up</button>
			<%
			String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
			String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);

			if (errMsg != null) {
				// print
			%>
			<p class="error">
				<%
				out.println(errMsg);
				%>
			</p>
			<%
			}

			if (successMsg != null) {
			// print
			%>
			<p class="success">
				<%
				out.println(successMsg);
				%>
			</p>
			<%
			}
			%>
		</form>



	</div>

</body>
</html>