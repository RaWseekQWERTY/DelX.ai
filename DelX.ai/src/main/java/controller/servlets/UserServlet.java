package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.UserModel;
import utils.StringUtils;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_USER_ADMIN })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
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
		List<UserModel> user = dbController.getAllUsers();
		request.setAttribute(StringUtils.LIST_USERS, user);
		request.getRequestDispatcher(StringUtils.PAGE_URL_USER_DETAILS).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
