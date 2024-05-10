package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserModel;
import utils.StringUtils;

public class AuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();

		HttpSession session = req.getSession(false);
		UserModel user = (UserModel) (session != null ? session.getAttribute("username") : null);
		boolean isLoggedIn = user != null;

		// Allow access to CSS files, index page, categoryDetails, and registration
		// without authentication
		if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpg")
				|| uri.endsWith(".jpeg") || uri.endsWith(".webp") || uri.endsWith(StringUtils.URL_HOME)
				|| uri.endsWith(StringUtils.PAGE_URL_REGISTER) || uri.endsWith(StringUtils.SERVLET_URL_REGISTER)) {
			System.out.println("Allow:" + uri);
			chain.doFilter(req, res);
			return;
		}

		boolean isLogin = uri.endsWith(StringUtils.PAGE_URL_LOGIN) || uri.endsWith(StringUtils.SERVLET_URL_LOGIN);
		boolean isRegister = uri.endsWith(StringUtils.PAGE_URL_REGISTER)
				|| uri.endsWith(StringUtils.SERVLET_URL_REGISTER);
		boolean isLogout = uri.endsWith(StringUtils.SERVLET_URL_LOGOUT);
		boolean isAdmin = uri.endsWith(StringUtils.SERVLET_ADMIN);
		boolean isAdminCatalog = uri.endsWith(StringUtils.SERVLET_ADMIN_CATALOG);
		boolean isAdminCategory = uri.endsWith(StringUtils.SERVLET_ADMIN_CATEGORY);
		boolean isUserAdmin = uri.endsWith(StringUtils.SERVLET_USER_ADMIN);
		boolean isChangePassword = uri.endsWith(StringUtils.SERVLET_CHANGE_PASSWORD);
		boolean isAddTool = uri.endsWith(StringUtils.SERVLET_URL_ADD_TOOL);
		boolean isModifyTool = uri.endsWith(StringUtils.SERVLET_URL_MODIFY_TOOL);
		boolean isModifyUser = uri.endsWith(StringUtils.SERVLET_URL_MODIFY_USER);
		boolean isCatalog = uri.endsWith(StringUtils.SERVLET_URL_CATALOG);
		boolean isCatalogSearch = uri.endsWith(StringUtils.SERVLET_URL_CATALOG_SEARCH);
		boolean isUserCatalog = uri.endsWith(StringUtils.SERVLET_URL_USER_CATALOG);
		boolean isUserSearch = uri.endsWith(StringUtils.SERVLET_URL_USER_SEARCH);
		boolean isProfile = uri.endsWith(StringUtils.PAGE_URL_PROFILE);
		boolean isForgotPassword = uri.endsWith(StringUtils.PAGE_URL_PASSWORD);
		boolean isSearchCatalog = uri.endsWith(StringUtils.PAGE_URL_SEARCH_CATALOG);
		boolean isSearchUser = uri.endsWith(StringUtils.PAGE_URL_SEARCH_USER);
		boolean isAdminDash = uri.endsWith(StringUtils.PAGE_URL_ADMIN_DASH);
		boolean isAdminCatalogEdit = uri.endsWith(StringUtils.PAGE_URL_ADMIN_CATALOG_EDIT);
		boolean isProfileSection2 = uri.endsWith(StringUtils.PAGE_URL_PROFILE_SECTION2);
		boolean isCategory = uri.endsWith(StringUtils.PAGE_URL_CATEGORY);
		boolean isForgotPassServlet = uri.endsWith(StringUtils.SERVLET_CHANGE_PASSWORD);

		// Redirect logged-in users away from login and register pages/servlets
		if (isLoggedIn && (isLogin)) {
			res.sendRedirect(req.getContextPath() + StringUtils.URL_HOME);
			System.out.println("Redirecting logged-in user away from login or register page");
			return;
		}
		if (isLoggedIn && (isRegister)) {
			res.sendRedirect(req.getContextPath() + StringUtils.URL_HOME);
			System.out.println("Redirecting logged-in user away from login or register page");
			return;
		}
		if (!isLoggedIn && !(isLogin || isForgotPassword || isRegister || isProfile || isForgotPassword
				|| isForgotPassServlet || isModifyUser)) {
			res.sendRedirect(req.getContextPath() + StringUtils.PAGE_URL_LOGIN);
			System.out.println("Redirecting to login page");
		} else if (isLoggedIn && !(isLogout || isLogin || isAdmin || isAdminCatalog || isAdminCategory || isUserAdmin
				|| isChangePassword || isAddTool || isModifyTool || isModifyUser || isCatalog || isCatalogSearch
				|| isUserCatalog || isUserSearch || isProfile || isSearchCatalog || isSearchUser || isAdminDash
				|| isAdminCatalogEdit || isProfileSection2 || isCategory || isForgotPassword)) {
			System.out.println("Redirecting to home page");
			res.sendRedirect(req.getContextPath() + StringUtils.URL_HOME);
		} else {
			System.out.println("Allowing access to: " + uri);
			chain.doFilter(request, response);
		}

		if (isLoggedIn && user.getUserType().equals("normal")
				&& (isAdmin || isAdminCatalog || isAdminCategory || isUserAdmin || isAdminDash || isCategory)) {
			// Normal user trying to access admin-related URLs
			session.setAttribute(StringUtils.MESSAGE_ERROR, "You don't have admin privileges");
			res.sendRedirect(req.getContextPath() + StringUtils.PAGE_URL_LOGIN);

			return;
		}

	}

	@Override
	public void destroy() {
	}
}