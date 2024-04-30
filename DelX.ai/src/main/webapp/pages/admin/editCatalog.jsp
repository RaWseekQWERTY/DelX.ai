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
DBController dbController = new DBController();
List<Category> categories = dbController.getAllCategories();
String updateId = request.getParameter("id");
int catalogid = Integer.parseInt(updateId);
Catalog tool = dbController.getCatalogById(catalogid);
out.println(tool);
out.println(tool.getCatalogID());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DelX.ai | Edit Catalog</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/editCatalog.css" />
</head>
<body>
	<div class="main-container">
		<div class="content">

			<div class="form_container">
				<h3>Edit Tool</h3>

				<form
					action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_TOOL%>"
					method="post">
					<input type="hidden" name="<%=StringUtils.UPDATE_ID%>"
						value="<%=updateId%>" />
					<%
					out.println("Catalog ID from tool: " + tool.getCatalogID());
					%>
					<%
					out.println("UPDATE_ID  " + StringUtils.UPDATE_ID);
					%>
					<%-- Debug output --%>
					<p>
						Tool Catalog ID:
						<%=tool.getCatalogID()%></p>
					<p>
						UPDATE_ID
						<%=StringUtils.UPDATE_ID%></p>
					<div class="form_wrap form_grp">
						<div class="form_item">
							<label>Tool Name</label> <input type="text" name="toolName"
								value="<%=tool.getToolName()%>" required>

						</div>
					</div>
					<div class="form_wrap">
						<div class="form_item">
							<label>Tool Author</label> <input type="text" name="toolAuthor"
								value="<%=tool.getToolAuthor()%>" required>
						</div>
					</div>
					<div class="form_wrap">
						<div class="form_item">
							<label>Tool Description</label>
							<textarea name="desc" rows="4" cols="50" style="resize: none;"><%=tool.getToolDesc()%></textarea>
						</div>
					</div>
					<!-- Category -->

					<div class="form_wrap">
						<div class="form_item">
							<label>Tool Category:</label> <select name="cateId" id="">
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
								value="<%=tool.getImageUrlFromPart()%>"
								accept="image/png, image/gif, image/jpeg">
						</div>
					</div>


					<div class="btn">
						<button type="submit" class="actionbtn">Edit</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>