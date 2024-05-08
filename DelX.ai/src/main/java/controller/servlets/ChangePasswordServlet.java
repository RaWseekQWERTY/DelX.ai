package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.PasswordEncryptionWithAes;
import model.UserModel;
import utils.StringUtils;
import utils.StringValidation;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_CHANGE_PASSWORD })
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePasswordServlet() {
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
		String gmail = request.getParameter(StringUtils.EMAIL);
		if (gmail != null && !gmail.isEmpty()) {
			doPut(request, response);

		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println("calling DOPUT");
			String gmail = req.getParameter(StringUtils.EMAIL);
			String username = req.getParameter(StringUtils.USERNAME);
			String encPass = req.getParameter(StringUtils.PASSWORD);
			String oldpassword = req.getParameter("oldPassword");
			String newPassword = req.getParameter("newPassword");
			System.out.println(gmail);
			System.out.println(oldpassword);
			System.out.println(newPassword);

			String usernameFromDB = dbController.getUsernameFromEmail(gmail);

			// Decrypt the password retrieved from the form using the username from the
			// database
			String decryptedPassword = PasswordEncryptionWithAes.decrypt(encPass, usernameFromDB);
			System.out.println(decryptedPassword);

			if (!oldpassword.equals(decryptedPassword)) {
				req.setAttribute(StringUtils.MESSAGE_ERROR, "Your old password seems incorrect");
				req.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(req, resp);
			} else {
				if (!StringValidation.isValidPassword(newPassword)) {
					req.setAttribute(StringUtils.MESSAGE_ERROR, "Password requires one upcaps,one digit and length 8");
					req.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(req, resp);
					return;
				}
				// Update password using username
				UserModel user = new UserModel();
				user.setUsername(username);
				user.setPassword(newPassword);
				int passwordUpdated = dbController.updateUserPassword(username, newPassword);

				if (passwordUpdated == 1) {
					System.out.println("password changed");
					req.setAttribute(StringUtils.MESSAGE_SUCCESS, "Password changed successful");
					req.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(req, resp);

				} else if (passwordUpdated == 0) {
					// Password update failed
					req.setAttribute(StringUtils.MESSAGE_ERROR, "Failed to update password");
					req.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(req, resp);
				} else {
					// Internal error
					req.setAttribute(StringUtils.MESSAGE_ERROR, "Internal error occurred");
					req.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(req, resp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute(StringUtils.MESSAGE_ERROR, "Something went wrong");
			req.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(req, resp);
		}

	}

}
