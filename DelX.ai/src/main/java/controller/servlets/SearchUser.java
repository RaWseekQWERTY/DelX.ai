package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.UserModel;
import utils.StringUtils;

/**
 * Servlet implementation class SearchUser
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_USER_SEARCH })
public class SearchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchUser() {
		dbController = new DBController();
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
		try {
			String search = request.getParameter("searchValue");
			ArrayList<UserModel> users = dbController.getUserBySearch(search);
			request.setAttribute(StringUtils.LIST_USERS, users);
			request.getRequestDispatcher(StringUtils.PAGE_URL_SEARCH_USER).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
