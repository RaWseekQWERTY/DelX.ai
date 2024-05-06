package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controller.database.DBController;
import model.Catalog;
import model.Category;
import model.PasswordEncryptionWithAes;
import model.UserModel;
import utils.StringUtils;
import utils.StringValidation;

/**
 * Servlet implementation class ModifyUserServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_MODIFY_USER })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyUserServlet() {
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
		System.out.println("calling doPost");

		String updateId = request.getParameter(StringUtils.UPDATE_ID);
		String deleteId = request.getParameter(StringUtils.DELETE_ID);
		String username = request.getParameter("userName");
		String userID = request.getParameter(StringUtils.USERID);
		System.out.println(updateId);
		if (updateId != null && !updateId.isEmpty()) {
			doPut(request, response);
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
		if (username != null && !username.isEmpty()) {
			resetPassword(request, response);
		}
		if (userID != null && !userID.isEmpty()) {
			changeRole(request, response);
		}

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			System.out.println("calling duDelete");
			String idDel = req.getParameter(StringUtils.DELETE_ID);
			int userID = Integer.parseInt(idDel);
			int result = dbController.deleteUser(userID);
			if (result == 1) {
				// 1. Clear existing cookies
				Cookie[] cookies = req.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						// Set max age to 0 to effectively delete the cookie
						cookie.setMaxAge(0);
						resp.addCookie(cookie);
					}
				}

				// 2. Invalidate user session (if it exists)
				HttpSession session = req.getSession(false);
				if (session != null) {
					session.invalidate();
				}

				// 3. Redirect to home page
				resp.sendRedirect(req.getContextPath() + StringUtils.URL_HOME);

			} else {

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("calling doput");
		try {
			int userID = Integer.parseInt(req.getParameter(StringUtils.UPDATE_ID));
			String firstName = req.getParameter(StringUtils.FIRST_NAME);
			String lastName = req.getParameter(StringUtils.LAST_NAME);
			String username = req.getParameter(StringUtils.USERNAME);
			String gender = req.getParameter(StringUtils.GENDER);
			Part imagePart = req.getPart("pic");

			boolean usernameExists = dbController.checkUsernameIfExists(username);
			if (usernameExists) {
				System.out.println("username exist");
				req.setAttribute(StringUtils.MESSAGE_ERROR, "Username already exists! Please choose another");
				req.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(req, resp);
				return; // Stop processing the request
			}

			UserModel user = new UserModel();
			user.setUserID(userID);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setUsername(username);
			user.setGender(gender);
			user.setImageUrlFromPart(imagePart);
			String fileName = user.getImageUrlFromPart();
			String savePath = StringUtils.IMAGE_DIR_SAVE_PATH;
			if (fileName != null && !fileName.isEmpty()) { // Check if fileName is not null
				imagePart.write(savePath + fileName);
			}

			boolean result = dbController.updateUser(user);
			if (result) {
				System.out.println("updated success");
				req.setAttribute(StringUtils.MESSAGE_SUCCESS, "Update Successful Please ReLogin to see changes");
				req.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(req, resp);
			} else {
				req.setAttribute(StringUtils.MESSAGE_ERROR, "Ensure your connectivity");
				req.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void resetPassword(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			System.out.println("calling resetPass");
			String username = req.getParameter("userName");
			String password = req.getParameter(StringUtils.PASSWORD);
			String retypepassword = req.getParameter(StringUtils.RETYPE_PASSWORD);

			boolean usernameExists = dbController.checkUsernameIfExists(username);
			if (!usernameExists) {
				req.setAttribute(StringUtils.MESSAGE_ERROR, "Username couldn't be found");
				req.getRequestDispatcher(StringUtils.PAGE_URL_FORGOT_PASS).forward(req, resp);
				return; // Stop processing the request
			}
			if (!password.equals(retypepassword)) {
				req.setAttribute(StringUtils.MESSAGE_ERROR, "Passwords do not match");
				req.getRequestDispatcher(StringUtils.PAGE_URL_FORGOT_PASS).forward(req, resp);
				return; // Stop processing the request
			}
			if (!StringValidation.isValidPassword(password)) {
				req.setAttribute(StringUtils.MESSAGE_ERROR, "One upcaps,one digit and length 8");
				req.getRequestDispatcher(StringUtils.PAGE_URL_FORGOT_PASS).forward(req, resp);
				return;
			}
			// Update password using username
			UserModel user = new UserModel();
			user.setUsername(username);
			user.setPassword(password);
			int passwordUpdated = dbController.updateUserPassword(username, password);

			if (passwordUpdated == 1) {
				System.out.println("password changed");
				req.setAttribute(StringUtils.MESSAGE_SUCCESS, "Password changed successful");
				req.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(req, resp);

			} else if (passwordUpdated == 0) {
				// Password update failed
				req.setAttribute(StringUtils.MESSAGE_ERROR, "Failed to update password");
				req.getRequestDispatcher(StringUtils.PAGE_URL_FORGOT_PASS).forward(req, resp);
			} else {
				// Internal error
				req.setAttribute(StringUtils.MESSAGE_ERROR, "Internal error occurred");
				req.getRequestDispatcher(StringUtils.PAGE_URL_FORGOT_PASS).forward(req, resp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void changeRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("call changerole");
		String userID = req.getParameter(StringUtils.USERID);
		String role = req.getParameter(StringUtils.ROLE);
		UserModel user = new UserModel();
		user.setUserID(Integer.parseInt(userID));
		user.setUserType(role);

		boolean result = dbController.updateUserRole(user);
		if (result) {
			System.out.println("updated success");
			req.setAttribute(StringUtils.MESSAGE_SUCCESS, "You have upgraded the User");
			req.getRequestDispatcher(StringUtils.PAGE_URL_USER_DETAILS).forward(req, resp);
		} else {

		}
		System.out.println(role);
	}

}
