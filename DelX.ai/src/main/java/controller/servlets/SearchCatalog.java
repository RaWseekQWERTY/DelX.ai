package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.Catalog;
import model.UserModel;
import utils.StringUtils;

/**
 * Servlet implementation class SearchCatalog
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_CATALOG_SEARCH })
public class SearchCatalog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchCatalog() {
		dbController = new DBController();
		// TODO Auto-generated constructor stub
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
			String search = request.getParameter(StringUtils.SEARCH);
			ArrayList<Catalog> tool = dbController.getCatalogBySearch(search);
			request.setAttribute(StringUtils.LIST_TOOLS, tool);
			request.getRequestDispatcher(StringUtils.PAGE_URL_SEARCH_CATALOG).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
