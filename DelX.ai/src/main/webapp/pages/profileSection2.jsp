<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String contextPath = request.getContextPath();
HttpSession userSession = request.getSession();
userSession.getAttribute(StringUtils.USERNAME);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/ProfileSection2.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>
<body>
	<jsp:include page="<%=StringUtils.PAGE_URL_HEADER%>" />

	<main class="body">
		<section class="profile">
			<div class="avatar">
				<img
					src="${pageContext.request.contextPath}/resources/user/${username.imageUrlFromPart}"
					alt="" width="80px" height="80px" />
				<!-- <button>Edit Profile</button> -->
			</div>
			<div class="info">
				<p class="side_profile">
					<i class="fa-regular fa-user"></i>${username.username}</p>
				<p class="side_profile">
					<i class="fa-regular fa-clock"></i>${username.dob}
				</p>
				<p class="side_profile">
					<i class="fa-regular fa-envelope"></i> ${username.email}
				</p>
			</div>
			<div class="article">
				<h3>Recent Articles</h3>
				<img
					src="https://media.istockphoto.com/id/1300845620/vector/user-icon-flat-isolated-on-white-background-user-symbol-vector-illustration.jpg?s=612x612&w=0&k=20&c=yBeyba0hUkh14_jgv1OKqIH0CCSWU_4ckRkAoy2p73o="
					alt="" width="170px" height="170px" />
			</div>
			<div class="profile-navs">
				<a href="<%=contextPath + StringUtils.PAGE_URL_PROFILE%>"
					title="Profile"> <i class="fa-solid fa-gear fa-2xl"></i>
				</a>
				<form action="<%=contextPath + StringUtils.SERVLET_URL_LOGOUT%>"
					method="post">
					<div>
						<button title="logout"
							style="border: none; background-color: transparent;">
							<i class="fa-solid fa-right-from-bracket fa-2xl"></i>
						</button>
					</div>


				</form>

			</div>
		</section>
		<section class="subscription">
			<div class="box">
				<div class="content">
					<details>
						<summary>Change Username</summary>
						<div class="faq__content">
							<form name="form"
								action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_USER%>"
								method="post" enctype="multipart/form-data" class="edit-form">
								<input type="hidden" name="${StringUtils.EMAIL}"
									value="${username.email}"> <input type="hidden"
									name="${StringUtils.PASSWORD}" value="${username.password}">
								<input type="text" name="${StringUtils.USERNAME}" id=""
									placeholder="Username" value="${username.username}">
								<button type="submit">Save Changes</button>
							</form>
						</div>
					</details>
					<details>
						<summary>Change Password</summary>
						<div class="faq__content">
							<form
								action="<%=contextPath + StringUtils.SERVLET_CHANGE_PASSWORD%>"
								class="edit-form" method="post">
								<input type="hidden" name="${StringUtils.USERNAME}"
									value="${username.username}"> <input type="hidden"
									name="${StringUtils.EMAIL}" value="${username.email}">
								<input type="hidden" name="${StringUtils.PASSWORD}"
									value="${username.password}">
									<i
					class="toggle-password fa fa-eye-slash"></i> <input type="password"
					placeholder="Old Password" id="password"
					name="oldPassword" required>
									
									 <i
					class="toggle-confirm-password fa fa-eye-slash"></i> <input
					type="password" placeholder="Confirm Password" id="confirmPassword"
					name="newPassword" required>

								<button type="submit">Save Changes</button>
							</form>
						</div>
					</details>
				</div>
			</div>
		</section>
	</main>
	<script type="text/javascript">
	//For image loading input file
		var loadFile = function(event) {
			var image = document.getElementById("output");
			image.src = URL.createObjectURL(event.target.files[0]);
		};
		//for conforming user action to delete account
		function confirmDelete(userId, userName) {
			if (confirm("Are you sure you want to delete your account: "
					+ userName + "?")) {
				document.getElementById("deleteForm-" + userId).submit();
			}
		}
		//for dropdown menu
		const summaries = document.querySelectorAll('summary');
        summaries.forEach(summary => {
          summary.addEventListener('click', () => {
            summaries.forEach(s => {
              if (s !== summary) {
                s.parentElement.removeAttribute('open');
              }
            });
          });
        });
 
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