package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.Category;
import utils.StringUtils;

/**
 * Servlet implementation class ToolsAddServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_ADD_TOOL })
public class ToolsAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToolsAddServlet() {
		this.dbController = new DBController();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// fetching data
		String ops = request.getParameter("operations");
		if (ops.trim().equals("addcategory")) {
			// add category
			String title = request.getParameter("catTitle");
			String description = request.getParameter("desc");

			Category category = new Category(title, description);
			int result = dbController.addCategory(category);
			System.out.println(result);
			if (result>1) {
				request.setAttribute(StringUtils.MESSAGE_SUCCESS, "Category Added Successfully");
				request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_DASH).forward(request, response);

			} else if (result == 0) {

			} else {

			}
		} else if (ops.trim().equals("addtools")) {
			// add tool
		}

		doGet(request, response);
	}

}
