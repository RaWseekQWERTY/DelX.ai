package controller.servlets;

import java.io.IOException;

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
 * Servlet implementation class ModifyServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_MODIFY_TOOL })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class ModifyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyServlet() {
		this.dbController = new DBController();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
			String ToolIDstr = req.getParameter(StringUtils.DELETE_ID);
			int ToolID = Integer.parseInt(ToolIDstr);
			int result = dbController.deleteTool(ToolID);
			if (result == 1) {
				System.out.println("Deleted Successfully");
				req.setAttribute(StringUtils.MESSAGE_SUCCESS, "Deleted Successfully");
				req.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_CATALOG).forward(req, resp);

			} else {
				req.setAttribute(StringUtils.MESSAGE_ERROR, "No tools to Delete");
				req.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_CATALOG).forward(req, resp);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("calling duPut");
		try {
			int catalogID = Integer.parseInt(req.getParameter(StringUtils.UPDATE_ID));
			String toolName = req.getParameter("toolName");
			String toolAuthor = req.getParameter("toolAuthor");
			String toolDesc = req.getParameter("desc");
			Part imagePart = req.getPart("toolpic");

			String categoryIdString = req.getParameter("catId");
			int categoryID = Integer.parseInt(categoryIdString);
			// get category by id
			Category category = dbController.getCategoryById(categoryID);

			Catalog tool = new Catalog();
			tool.setCatalogID(catalogID);
			tool.setToolName(toolName);
			tool.setToolAuthor(toolAuthor);
			tool.setToolDesc(toolDesc);
			tool.setCategory(category);
			tool.setImageUrlFromPart(imagePart);
			String fileName = tool.getImageUrlFromPart();
			String savePath = StringUtils.IMAGE_DIR_SAVE_PATH;
			if (fileName != null && !fileName.isEmpty()) { // Check if fileName is not null
				imagePart.write(savePath + fileName);
			}

			boolean result = dbController.updateTool(tool);
			if (result) {
				System.out.println("updated success");
				req.setAttribute(StringUtils.MESSAGE_SUCCESS, "Updated Successfully");
				req.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_CATALOG).forward(req, resp);
			} else {
				req.setAttribute(StringUtils.MESSAGE_ERROR, "Something went wrong");
				req.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_CATALOG).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
