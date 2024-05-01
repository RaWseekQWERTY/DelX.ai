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
import model.UserModel;
import utils.StringUtils;

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
		System.out.println(updateId);
		System.out.println(deleteId);

		if (updateId != null && !updateId.isEmpty()) {
			doPut(request, response);
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
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
		try {
			int userID = Integer.parseInt(req.getParameter(StringUtils.UPDATE_ID));
			String firstName = req.getParameter(StringUtils.FIRST_NAME);
			String lastName = req.getParameter(StringUtils.LAST_NAME);
			String username = req.getParameter(StringUtils.USERNAME);
			String gender = req.getParameter(StringUtils.GENDER);
			Part imagePart = req.getPart("pic");

			boolean usernameExists = dbController.checkUsernameIfExists(username);
			if (usernameExists) {
				req.setAttribute(StringUtils.MESSAGE_ERROR, "Username already exists! Please choose another");
				req.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(req, resp);
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

			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
