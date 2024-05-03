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
	href="<%=contextPath%>/stylesheets/passwordReset.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>
<body>
	<div class="nav">
		<a href="<%=contextPath + StringUtils.URL_HOME%>" class="logo"> <img
			src="${pageContext.request.contextPath}/resources/other/logo.png"
			alt="logo" width="50px" height="50px"></a>

	</div>
	<div class="container">
		<form action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_USER%>"
			method="post">
			<%
			String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
			String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);

			if (errMsg != null) {
				// print
			%>
			<p style="color: red;">
				<%
				out.println(errMsg);
				%>
			</p>
			<%
			}

			if (successMsg != null) {
			// print
			%>
			<p style="color: green;">
				<%
				out.println(successMsg);
				%>
			</p>
			<%
			}
			%>
			<h3>Login</h3>
			<div class="label-container">
				<label for="username">Username</label> <input type="text"
					placeholder="username" id="username"
					name="<%=StringUtils.USERNAME%>" required> <label
					for="password">New Password</label><i
					class="toggle-password fa fa-eye-slash"></i> <input type="password"
					placeholder="Password" id="password"
					name="<%=StringUtils.PASSWORD%>" required> <label
					for="confirmPassword">Confirm Password</label> <i
					class="toggle-confirm-password fa fa-eye-slash"></i> <input
					type="password" placeholder="Confirm Password" id="confirmPassword"
					name="<%=StringUtils.RETYPE_PASSWORD%>" required>
			</div>




			<button>Reset Password</button>
		</form>
	</div>
	<script type="text/javascript">
		document.addEventListener("DOMContentLoaded", function() {
			var togglePassword = document.querySelector(".toggle-password");
			var passwordInput = document.getElementById("password");
			var toggleConfirmPassword = document
					.querySelector(".toggle-confirm-password");
			var confirmPasswordInput = document
					.getElementById("confirmPassword");

			togglePassword.addEventListener("click", function() {
				togglePassword.classList.toggle("fa-eye");
				togglePassword.classList.toggle("fa-eye-slash");

				if (passwordInput.type === "password") {
					passwordInput.type = "text";
				} else {
					passwordInput.type = "password";
				}
			});

			toggleConfirmPassword.addEventListener("click", function() {
				toggleConfirmPassword.classList.toggle("fa-eye");
				toggleConfirmPassword.classList.toggle("fa-eye-slash");

				if (confirmPasswordInput.type === "password") {
					confirmPasswordInput.type = "text";
				} else {
					confirmPasswordInput.type = "password";
				}
			});
		});
	</script>
</body>
</html>