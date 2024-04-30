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
DBController dbController = new DBController();
List<Catalog> toolList = dbController.getAllTools();
request.setAttribute(StringUtils.LIST_TOOLS, toolList);
List<Category> categories = dbController.getAllCategories();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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

							<%
							if (toolList.isEmpty()) {
							%>
							<tr>
								<td colspan="7" style="font-size: 16px; font-weight: 400;">No
									Ai tools not found! Please, add a tool</td>
							</tr>
							<%
							} else {
							for (Catalog tool : toolList) {
							%>
							<tr>
								<td><%=tool.getCatalogID()%></td>
								<td><%=tool.getToolName()%></td>
								<td><%=tool.getCategory().getCategoryName()%></td>
								<td><%=tool.getToolDesc()%></td>
								<td><%=tool.getToolAuthor()%></td>
								<td><img
									src="${pageContext.request.contextPath}/resources/catalog/<%=tool.getImageUrlFromPart() %>"
									width="20px" height="20px" alt="picture"></td>
								<td><a class="edit -btn"
									href="#popup2?id=<%=tool.getCatalogID()%>">Edit</a>

									<form id="deleteForm-<%=tool.getCatalogID()%>" method="post"
										action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_TOOL%>">
										<input type="hidden" name="<%=StringUtils.DELETE_ID%>"
											value="<%=tool.getCatalogID()%>" />
										<button type="button" class="actionbtn"
											onclick="confirmDelete('<%=tool.getCatalogID()%>', '<%=tool.getToolName()%>')">Delete</button>
									</form>
									<div id="popup2?id=<%=tool.getCatalogID()%>" class="overlay">
										<div class="popup">
											<h2>EDIT</h2>
											<a class="close" href="#">&times;</a>
											<div class="content">
												<div class="form_container">
													<form name="form"
														action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_TOOL%>"
														method="post" enctype="multipart/form-data">
														<input type="hidden" name="<%=StringUtils.UPDATE_ID%>"
															value="<%=tool.getCatalogID()%>" />
														<div class="form_wrap form_grp">
															<div class="form_item">
																<label>Tool Name</label> <input type="text"
																	name="toolName" value="<%=tool.getToolName()%>"
																	required>

															</div>
														</div>
														<div class="form_wrap">
															<div class="form_item">
																<label>Tool Author</label> <input type="text"
																	value="<%=tool.getToolAuthor()%>" name="toolAuthor"
																	required>
															</div>
														</div>
														<div class="form_wrap">
															<div class="form_item">
																<label>Tool Description</label>
																<textarea name="desc" rows="4" cols="50"
																	style="resize: none;"><%=tool.getToolDesc()%></textarea>
															</div>
														</div>
														<!-- Category -->

														<div class="form_wrap">
															<div class="form_item">
																<label>Tool Category:</label> <select name="catId" id="">
																	<%
																	for (Category cat : categories) {
																		boolean isSelected = (tool.getCategory().getCategoryID() == cat.getCategoryID());
																		if (isSelected) {
																	%>
																	<option selected="selected"
																		value="<%=cat.getCategoryID()%>">
																		<%=cat.getCategoryName()%>
																	</option>
																	<%
																	} else {
																	%>
																	<option value="<%=cat.getCategoryID()%>">
																		<%=cat.getCategoryName()%>
																	</option>
																	<%
																	}
																	}
																	%>
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
							<%
							}
							}
							%>

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
												<%
												if (categories == null || categories.isEmpty()) {
												%>
												<option value="none">none</option>
												<%
												} else {
												for (Category cat : categories) {
												%>
												<option value="<%=cat.getCategoryID()%>"><%=cat.getCategoryName()%></option>
												<%
												}
												}
												%>
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