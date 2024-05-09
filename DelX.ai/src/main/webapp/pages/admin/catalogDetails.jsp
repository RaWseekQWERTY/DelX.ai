<%@page import="utils.StringUtils"%>
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
<title>ADMIN || CATALOG</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/CatalogDetails.css" />
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
					onclick="document.getElementById('userForm').submit(); return false;">USER</a>
					<form id="userForm"
						action="${pageContext.request.contextPath}${StringUtils.SERVLET_USER_ADMIN}"
						method="get" style="display: none;"></form></li>
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

				<a class="button" href="#popup1">ADD</a> <br> <br>
				<div class="table-wrapper">
					<h3 style="color: white;">Tools Details:</h3>
					<table class="fl-table">
						<thead>
							<tr>
								<th>CatalogID</th>
								<th>ToolName</th>
								<th>ToolCategory</th>
								<th>ToolDesc</th>
								<th>ToolAuthor</th>
								<th>ToolImg</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>

							<%-- <c:choose>
							<c:when test="${empty toolList}">
								<tr>
									<td colspan="7" style="font-size: 16px; font-weight: 400;">No
										Ai tools found! Please, add a tool</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="tool" items="${toolList}">
									<tr>
										<td>${tool.catalogID}</td>
										<td>${tool.toolName}</td>
										<td>${tool.category.categoryName}</td>
										<td>${tool.toolDesc}</td>
										<td>${tool.toolAuthor}</td>
										<td><img
											src="${pageContext.request.contextPath}/resources/catalog/${tool.imageUrlFromPart}"
											width="120px" height="120px" alt="picture"></td>
										<td><a class="actionBtn"
											href="<%=contextPath + StringUtils.PAGE_URL_ADMIN_CATALOG_EDIT%>?id=${tool.catalogID}">Edit</a>


											<form id="deleteForm-${tool.catalogID}" method="post"
												action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_TOOL %>">
												<input type="hidden" name="<%=StringUtils.DELETE_ID %>"
													value="${tool.catalogID}" />
												<button type="button" class="actionbtn"
													onclick="confirmDelete('${tool.catalogID}', '${tool.toolName}')">Delete</button>

											</form>
											</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose> --%>

							<c:choose>
								<c:when test="${empty toolList}">
									<tr>
										<td colspan="7" style="font-size: 16px; font-weight: 400;">No
											Ai tools not found! Please, add a tool</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="tool" items="${toolList}">
										<tr>
											<td>${tool.catalogID}</td>
											<td>${tool.toolName}</td>
											<td>${tool.category.categoryName}</td>
											<td>${tool.toolDesc}</td>
											<td>${tool.toolAuthor}</td>
											<td><img
												src="${pageContext.request.contextPath}/resources/catalog/${tool.imageUrlFromPart}"
												width="20px" height="20px" alt="picture"></td>
											<td><a class="edit-btn"
												href="#popup2?id=${tool.catalogID}">Edit</a>

												<form id="deleteForm-${tool.catalogID}" method="post"
													action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_TOOL%>">
													<input type="hidden" name="deleteId"
														value="${tool.catalogID}" />
													<button type="button" class="actionbtn"
														onclick="confirmDelete('${tool.catalogID}', '${tool.toolName}')">Delete</button>
												</form>

												<div id="popup2?id=${tool.catalogID}" class="overlay">
													<div class="popup">
														<h2>EDIT</h2>
														<a class="close" href="#">&times;</a>
														<div class="content">
															<div class="form_container">
																<form name="form"
																	action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_TOOL%>"
																	method="post" enctype="multipart/form-data">
																	<input type="hidden" name="updateId"
																		value="${tool.catalogID}" />
																	<div class="form_wrap form_grp">
																		<div class="form_item">
																			<label>Tool Name</label> <input type="text"
																				name="toolName" value="${tool.toolName}" required>
																		</div>
																	</div>
																	<div class="form_wrap">
																		<div class="form_item">
																			<label>Tool Author</label> <input type="text"
																				value="${tool.toolAuthor}" name="toolAuthor"
																				required>
																		</div>
																	</div>

																	<div class="form_wrap">
																		<div class="form_item">
																			<label>Tool Description</label>
																			<textarea name="desc" rows="4" cols="50"
																				style="resize: none;">${tool.toolDesc}</textarea>
																		</div>
																	</div>

																	<!-- Category -->
																	<div class="form_wrap">
																		<div class="form_item">
																			<label>Tool Category:</label> <select name="catId"
																				id="">
																				<c:forEach var="cat" items="${categories}">
																					<c:choose>
																						<c:when
																							test="${tool.category.categoryID eq cat.categoryID}">
																							<option selected="selected"
																								value="${cat.categoryID}">${cat.categoryName}</option>
																						</c:when>
																						<c:otherwise>
																							<option value="${cat.categoryID}">${cat.categoryName}</option>
																						</c:otherwise>
																					</c:choose>
																				</c:forEach>
																			</select>
																		</div>
																	</div>

																	<div class="form_wrap">
																		<div class="form_item">
																			<label>Tool Image</label> <input type="file"
																				name="toolpic"
																				accept="image/png, image/gif, image/jpeg">
																		</div>
																	</div>


																	<div class="btn">
																		<input type="submit" value="Save changes">
																	</div>
																</form>
															</div>
														</div>
													</div>
												</div></td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>

					</table>
				</div>
				<div id="popup1" class="overlay">
					<div class="popup">
						<h2>Add a new Tool</h2>
						<a class="close" href="#">&times;</a>
						<div class="content">
							<div class="form_container">
								<form name="form" action="<%=contextPath%>/addtool"
									method="post" enctype="multipart/form-data">
									<input type="hidden" name="operations" value="addtools">
									<div class="form_wrap form_grp">
										<div class="form_item">
											<label>Tool Name</label> <input type="text" name="toolName"
												required>

										</div>
									</div>
									<div class="form_wrap">
										<div class="form_item">
											<label>Tool Author</label> <input type="text"
												name="toolAuthor" required>
										</div>
									</div>
									<div class="form_wrap">
										<div class="form_item">
											<label>Tool Description</label>
											<textarea name="desc" rows="4" cols="50"
												style="resize: none;"></textarea>
										</div>
									</div>
									<!-- Category -->

									<div class="form_wrap">
										<div class="form_item">
											<label>Tool Category:</label> <select name="catId" id="">
												<c:choose>
													<c:when test="${empty categories}">
														<option value="none">none</option>
													</c:when>
													<c:otherwise>
														<c:forEach var="cat" items="${categories}">
															<option value="${cat.categoryID}">${cat.categoryName}</option>
														</c:forEach>
													</c:otherwise>
												</c:choose>
											</select>
										</div>
									</div>

									<div class="form_wrap">
										<div class="form_item">
											<label>Tool Image</label> <input type="file" name="toolpic"
												accept="image/png, image/gif, image/jpeg">
										</div>
									</div>


									<div class="btn">
										<input type="submit" value="Add Tool">
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
		function confirmDelete(toolId, toolName) {
			if (confirm("Are you sure you want to delete this Tool: "
					+ toolName + "?")) {
				document.getElementById("deleteForm-" + toolId).submit();
			}
		}
	</script>
</body>
</html>