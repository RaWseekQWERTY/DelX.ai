package utils;

import java.io.File;

public class StringUtils {
	// Start: DB Connection
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/ai_planner";
	public static final String LOCALHOST_USERNAME = "root";
	public static final String LOCALHOST_PASSWORD = "";
	// End: DB Connection

	// Drive path
	public static final String IMAGE_DIR = "xampp\\tomcat\\webapps\\images\\";
	public static final String IMAGE_DIR_CATALOG = "Users\\HP\\eclipse-workspace\\DelX.ai\\src\\main\\webapp\\resources\\catalog\\";
	public static final String IMAGE_DIR_SAVE_PATH = "C:" + File.separator + IMAGE_DIR_CATALOG;
	public static final String IMAGE_DIR_USER = "Users\\HP\\eclipse-workspace\\DelX.ai\\src\\main\\webapp\\resources\\user\\";
	public static final String IMAGE_DIR_SAVE_PATH_USER = "C:" + File.separator + IMAGE_DIR_USER;
	// END driver path
	// Start: Queries
	public static final String QUERY_REGISTER_USER = "INSERT INTO user_detail ("
			+ "firstName, lastName, user_name, dob, gmail, gender, userType, password, avatar) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String QUERY_GETALL_USERS = "SELECT * FROM user_detail";

	// catalog and category
	public static final String QUERY_ADD_CATEGORY = "INSERT INTO category (" + "categoryTitle, categoryDesc)"
			+ "VALUES(?,?)";
	public static final String QUERY_GETALL_CATEGORY = "Select categoryID,categoryTitle,categoryDesc from category";
	public static final String QUERY_GET_CATEGORY_BY_ID = "SELECT * FROM category WHERE categoryId = ?";

	public static final String QUERY_GET_CATALOG_BY_ID = "SELECT * FROM catalog WHERE CatalogID = ?";
	public static final String QUERY_ADD_TOOLS = "INSERT INTO catalog ("
			+ "ToolName,ToolDesc,ToolAuthor,Toolimg,categoryID) " + "VALUES(?,?,?,?,?)";
	public static final String QUERY_DELETE_TOOLS = "DELETE FROM catalog WHERE CatalogID = ?";
	public static final String QUERY_UPDATE_TOOLS = "UPDATE catalog set ToolName=?,ToolDesc=?,ToolAuthor=?,Toolimg=?,categoryID=? "
			+ "WHERE CatalogID=?";

	// end catalog and category

	public static final String QUERY_GETALL_TOOLS = "Select * from catalog";

	public static final String QUERY_LOGIN_USER_CHECK = "SELECT * FROM user_detail WHERE user_name = ?";
	public static final String QUERY_USERNAME_CHECK = "SELECT * FROM user_detail WHERE user_name = ?";
	public static final String QUERY_EMAIL_CHECK = "SELECT * FROM user_detail WHERE gmail = ?";

	public static final String QUERY_USERDETAILS_BY_USERNAME = "SELECT * FROM user_detail WHERE user_name = ? AND password = ?";
	// End: Queries
	// Start: Parameter names
	public static final String USERNAME = "username";
	public static final String USER_NAME = "user_name";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String BIRTHDAY = "dob";
	public static final String GENDER = "gender";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String RETYPE_PASSWORD = "conformPass";
	// End: Parameter names

	// Start: Servlet Route
	public static final String SERVLET_URL_LOGIN = "/login";
	public static final String SERVLET_URL_REGISTER = "/registeruser";
	public static final String SERVLET_URL_LOGOUT = "/logout";
	public static final String SERVLET_URL_ADD_TOOL = "/addtool";
	public static final String SERVLET_URL_CATALOG = "/catalog";
	public static final String SERVLET_URL_MODIFY_TOOL = "/modifytools";
	public static final String SERVLET_URL_MODIFY_USER = "/modifyuser";
	public static final String SERVLET_URL_UPDATE_TOOL = "/updatetool";
	// End: Servlet Route

	// Start: Validation Messages
	// Register Page Messages
	public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
	public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";
	public static final String MESSAGE_ERROR_INCORRECT_DATA = "Please correct all the fields.";

	// Login Page Messages
	public static final String MESSAGE_NOT_FOUND = "404 Neither normal nor admin Not found";
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
	public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";

	// Other Messages
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";
	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";
	// End: Validation Messages

	public static final String PAGE_URL_HEADER = "/pages/header.jsp";
	public static final String URL_HOME = "/home.jsp";
	public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
	public static final String PAGE_URL_CATALOG = "/pages/cataloguser.jsp";
	public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
	public static final String PAGE_URL_PROFILE = "/pages/profile.jsp";
	public static final String PAGE_URL_ADMIN_DASH = "/pages/admin/admin.jsp";
	public static final String PAGE_URL_ADMIN_CATALOG = "/pages/admin/catalogDetails.jsp";
	public static final String PAGE_URL_ADMIN_CATALOG_EDIT = "/pages/admin/editCatalog.jsp";

	// Start: Normal Text
	public static final String SUCCESS = "success";
	public static final String TRUE = "true";
	public static final String USER = "user";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	public static final String SLASH = "/";
	public static final String DELETE_ID = "deleteId";
	public static final String UPDATE_ID = "updateId";
	// End: Normal Text

	public static final String LIST_TOOLS = "toolList";
	public static final String LIST_USERS = "userList";

}
