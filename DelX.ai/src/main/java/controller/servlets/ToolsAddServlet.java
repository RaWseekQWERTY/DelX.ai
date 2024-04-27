package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DBController;
import model.Catalog;
import model.Category;
import utils.StringUtils;

/**
 * Servlet implementation class ToolsAddServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_ADD_TOOL })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
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
//		ArrayList<Catalog> tools = dbController.getAllTools();
//		request.setAttribute(StringUtils.LIST_TOOLS, tools);
//		System.out.println("Tool List size: " + tools.size());
		response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_ADMIN_CATALOG);
		// response.getWriter().append("Served at: ").append(request.getContextPath());
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
			if (result >= 1) {
				request.setAttribute(StringUtils.MESSAGE_SUCCESS, "Category Added Successfully");
				request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_DASH).forward(request, response);

			} else if (result == 0) {

			} else {

			}
		} else if (ops.trim().equals("addtools")) {
			// add tool
			String toolName = request.getParameter("toolName");
			String toolAuthor = request.getParameter("toolAuthor");
			String toolDesc = request.getParameter("desc");
			Part imagePart = request.getPart("toolpic");
			String categoryIdString = request.getParameter("catId");

			// Check if categoryIdString is "none"
			if (categoryIdString.equals("none")) {
				// Handle the case where no category is selected
				request.setAttribute(StringUtils.MESSAGE_ERROR, "Please select OR add a category.");
				request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_DASH).forward(request, response);
				return; // Stop further execution
			}
			int categoryID = Integer.parseInt(categoryIdString);
			// get category by id
			Category category = dbController.getCategoryById(categoryID);
			System.out.println("Category object: " + category);
			if (category == null) {
				request.setAttribute(StringUtils.MESSAGE_ERROR, "Please add a category first.");
				request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_DASH).forward(request, response);
				return; // Stop further execution
			}

			// creating catalog model object to hold tools information
			Catalog tools = new Catalog(toolName, toolDesc, toolAuthor, imagePart, category);
			String savePath = StringUtils.IMAGE_DIR_SAVE_PATH;
			String fileName = tools.getImageUrlFromPart();
			if (fileName != null && !fileName.isEmpty()) { // Check if fileName is not null
				imagePart.write(savePath + fileName);
			}
			System.out.println(tools);
			int result = dbController.addTools(tools);

			if (result >= 1) {
				request.setAttribute(StringUtils.MESSAGE_SUCCESS, "Tools Added Successfully");
				request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_DASH).forward(request, response);
			} else if (result == 0) {

			} else {

			}
		}
		// doGet(request, response);
	}

}
