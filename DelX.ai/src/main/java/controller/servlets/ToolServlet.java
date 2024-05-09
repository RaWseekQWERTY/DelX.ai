package controller.servlets;

import java.io.IOException;
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
 * Servlet implementation class ToolServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_ADMIN_CATALOG })
public class ToolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToolServlet() {
		dbController = new DBController();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Catalog> toolList = dbController.getAllTools();
		request.setAttribute(StringUtils.LIST_TOOLS, toolList);
		List<Category> categories = dbController.getAllCategories();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_CATALOG).forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}

}
