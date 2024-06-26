package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DBController;
import model.UserModel;
import utils.StringUtils;
import utils.StringValidation;

/**
 * Name: rasik kayastha id:22067323
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_REGISTER })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	private final DBController dbController;

	public RegisterUserServlet() {
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
		String firstName = request.getParameter(StringUtils.FIRST_NAME);
		String lastName = request.getParameter(StringUtils.LAST_NAME);
		String dobString = request.getParameter(StringUtils.BIRTHDAY);
		LocalDate dob = LocalDate.parse(request.getParameter(StringUtils.BIRTHDAY));
		String gender = request.getParameter(StringUtils.GENDER);
		String email = request.getParameter(StringUtils.EMAIL);
		String username = request.getParameter(StringUtils.USERNAME);
		String password = request.getParameter(StringUtils.PASSWORD);
		String userType = "normal";
		Part imagePart = request.getPart("pic");

		// Validate Name
		if (!StringValidation.containsOnlyLetters(firstName) || !StringValidation.containsOnlyLetters(lastName)) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, "Names should contain only letters!");
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return; // Stop processing the request
		}
		// Validate email
		if (!StringValidation.isValidEmail(email)) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, "Invalid email address!");
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return; // Stop processing the request
		}
		// Validate date of birth (dob)
		if (!StringValidation.isValidDate(dobString)) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, "Invalid date of birth or date is in the future!");
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return; // Stop processing the request
		}

		// Check if the username already exists
		boolean usernameExists = dbController.checkUsernameIfExists(username);
		if (usernameExists) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, "Username already exists!");
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return; // Stop processing the request
		}

		// Check if the email already exists
		boolean emailExists = dbController.checkEmailIfExists(email);
		if (emailExists) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, "Email already exists!");
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return; // Stop processing the request
		}
		UserModel user = new UserModel(firstName, lastName, username, dob, email, gender, userType, password,
				imagePart);
		String savePath = StringUtils.IMAGE_DIR_SAVE_PATH_USER;
		String fileName = user.getImageUrlFromPart();
		if (fileName != null && !fileName.isEmpty()) { // Check if fileName is not null
			imagePart.write(savePath + fileName);
		}
		int result = dbController.registerUser(user);
		if (result == 1) {
			request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);

			response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN + "?success=true");
		} else if (result == 0) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_REGISTER);
		} else {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_REGISTER);
		}
	}

}
