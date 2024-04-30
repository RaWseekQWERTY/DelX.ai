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
	href="<%=contextPath%>/stylesheets/profile.css" />
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
					<i class="fa-light fa-calendar-days"></i>${username.dob}
				</p>
				<p class="side_profile">My Subscriptions</p>
			</div>
			<div class="article">
				<h3>Recent Articles</h3>
				<img
					src="https://media.istockphoto.com/id/1300845620/vector/user-icon-flat-isolated-on-white-background-user-symbol-vector-illustration.jpg?s=612x612&w=0&k=20&c=yBeyba0hUkh14_jgv1OKqIH0CCSWU_4ckRkAoy2p73o="
					alt="" width="170px" height="170px" />
			</div>
			<div class="profile-navs">
				<a href="#" title="security"> <i class="fa-solid fa-gear fa-2xl"></i>
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
			<form action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_USER%>"
				method="post" enctype="multipart/form-data">
				<h3>Edit Profile</h3>
				<div class="profile-pic">
					<label class="-label" for="file"> <span
						class="glyphicon glyphicon-camera"></span> <span><img
							src="<%=contextPath%>/resources/other/camera.png" alt=""
							width="50px" height="50px" /></span>
					</label> <input id="file" type="file" onchange="loadFile(event)"
						accept="image/png, image/gif, image/jpeg" /> <img
						src="https://cdn.pixabay.com/photo/2017/08/06/21/01/louvre-2596278_960_720.jpg"
						id="output" width="200" class="fr-img" />
				</div>

				<div class="row">
					<div class="col">
						<label for="firstName">First Name</label> <input type="text"
							value="${username.firstName}" name="firstName" id="firstName" />
					</div>
					<div class="col">
						<label for="lastName">Last Name</label> <input type="text"
							value="${username.lastName}" id="lastName" />
					</div>
				</div>

				<div class="row">
					<div class="col">
						<label for="username">Username</label> <input type="text"
							value="${username.username}" id="username" />
					</div>
					<div class="col">
						<label for="gender">Gender</label> <select id="gender">
							<option value="male">Male</option>
							<option value="female">Female</option>
							<option value="other">Other</option>
						</select>
					</div>
				</div>

				<div class="button-container">
					<button class="btn-left">Save Changes</button>
					<button class="btn-left">Delete Account</button>
				</div>
			</form>
		</section>
	</main>
	<script type="text/javascript">
		var loadFile = function(event) {
			var image = document.getElementById("output");
			image.src = URL.createObjectURL(event.target.files[0]);
		};
	</script>

</body>
</html>