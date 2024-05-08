package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DBController;
import model.LoginModel;
import model.UserModel;
import utils.StringUtils;

/**
 * This Servlet class handles login requests for a AI subscription Planner. It
 * retrieves username and password from the login form submission, validates
 * them against a database using a `DBController`, and redirects the user
 * accordingly based on the login result.
 *
 * @author [Rasik Kayastha, rasikkayasthav@gmail.com]
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_LOGIN })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		this.dbController = new DBController();

	}

	/**
	 * Handles HTTP POST requests for login.
	 *
	 * @param request  The HttpServletRequest object containing login form data.
	 * @param response The HttpServletResponse object for sending responses.
	 * @throws ServletException if a servlet-specific error occurs.
	 * @throws IOException      if an I/O error occurs.
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
		// Extract username and password from the request parameters
		String userName = request.getParameter(StringUtils.USERNAME);
		String password = request.getParameter(StringUtils.PASSWORD);
		System.out.println("Username: " + userName);
		// Create a LoginModel object to hold user credentials
		LoginModel loginModel = new LoginModel(userName, password);

		// Call DBController to validate login credentials
		int loginResult = dbController.getUserLoginInfo(loginModel);
		System.out.println("Login Result: " + loginResult);

		// Handle login results with appropriate messages and redirects
		if (loginResult == 1) {
			// Login successful, retrieve user details
			UserModel user = dbController.getUserByUsername(userName);

			if (user != null) {
				// Login successful
				HttpSession userSession = request.getSession();
				userSession.setAttribute(StringUtils.USERNAME, user);
				userSession.setAttribute("userType", user.getUserType()); // Set user type in session
				userSession.setMaxInactiveInterval(30 * 30);
				Cookie userCookie = new Cookie("user", userName);
				userCookie.setMaxAge(30 * 60);
				response.addCookie(userCookie);

				if ("admin".equals(user.getUserType())) {
					// Redirect admin to admin.jsp
					response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_ADMIN);
				} else if ("normal".equals(user.getUserType())) {
					// Redirect normal user to home.jsp
					response.sendRedirect(request.getContextPath() + StringUtils.URL_HOME);
				} else {
					// Invalid user type, redirect to login page
					request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
					request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
				}
			} else {
				// User not found, redirect to login page with error
				System.out.println("User details not found");
				request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_LOGIN);
				request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
			}
		} else if (loginResult == 0) {
			System.out.println("Username or password mismatch");
			// Username or password mismatch
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_LOGIN);
			request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
		} else if (loginResult == -1) {
			// Username not found
			System.out.println("Username not found");
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_CREATE_ACCOUNT);
			request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
		} else {
			System.out.println("Internal server error");
			// Internal server error
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
		}
	}
}
