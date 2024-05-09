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
import model.UserModel;
import utils.StringUtils;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_ADMIN })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		this.dbController = new DBController();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Catalog> toolList = dbController.getAllTools();
		request.setAttribute(StringUtils.LIST_TOOLS, toolList);
		List<Category> categories = dbController.getAllCategories();
		request.setAttribute(StringUtils.LIST_CATEGORY, categories);
		List<UserModel> user = dbController.getAllUsers();
		request.setAttribute(StringUtils.LIST_USERS, user);
		request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_DASH).forward(request, response);
	}


}
