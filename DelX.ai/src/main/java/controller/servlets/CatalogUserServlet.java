package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.Catalog;
import model.Category;
import utils.StringUtils;

/**
 * Servlet implementation class CatalogUserServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_USER_CATALOG })
public class CatalogUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CatalogUserServlet() {
		dbController = new DBController();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * List<Catalog> toolList = dbController.getAllTools();
		 * request.setAttribute(StringUtils.LIST_TOOLS, toolList); List<Category>
		 * categories = dbController.getAllCategories();
		 * request.setAttribute(StringUtils.LIST_CATEGORY, categories);
		 * request.getRequestDispatcher(StringUtils.PAGE_URL_CATALOG).forward(request,
		 * response);
		 * response.getWriter().append("Served at: ").append(request.getContextPath());
		 */
		List<Category> categories = dbController.getAllCategories();
		request.setAttribute(StringUtils.LIST_CATEGORY, categories);
		// Check if the category list is empty
		if (categories.isEmpty()) {
			// Redirect to the catalog page URL
			response.sendRedirect(StringUtils.PAGE_URL_CATALOG);
			return; // Ensure the method exits after redirection
		}

		ArrayList<Category> categoryList = dbController.getAllCategories();

		for (Category category : categoryList) {
			// Fetch catalogs for each category
			ArrayList<Catalog> catalogs = dbController.getToolsByCategoryId(category.getCategoryID());
			// Set the fetched catalogs to the category
			category.setCatalogs(catalogs);
		}

		request.setAttribute(StringUtils.LIST_CATEGORY, categoryList);
		request.getRequestDispatcher(StringUtils.PAGE_URL_CATALOG).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		List<Catalog> toolList = null;
		List<Category> categoryList = dbController.getAllCategories();

		String categoryName = "All";
		if (id != null && !id.equals("all")) {
			int categoryId = Integer.parseInt(id);
			Category selectedCategory = dbController.getCategoryById(categoryId);
			if (selectedCategory != null) {
				categoryName = selectedCategory.getCategoryName();
			}
		}
		request.setAttribute("categoryName", categoryName);

		// Fetch the appropriate tool list based on the id parameter
		/*
		 * if (id != null) { if (id.equals("all")) { toolList =
		 * dbController.getAllTools(); } else { toolList =
		 * dbController.getToolsByCategoryId(Integer.parseInt(id)); } }
		 */
		toolList = dbController.getToolsByCategoryId(Integer.parseInt(id));

		request.setAttribute("toolList", toolList);
		request.setAttribute("categoryList", categoryList);

		// Forward the request to the JSP
		request.getRequestDispatcher(StringUtils.PAGE_URL_CATALOG).forward(request, response);

	}

}
