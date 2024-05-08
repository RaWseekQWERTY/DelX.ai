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

		// Cast request and response objects to HttpServletRequest and
		// HttpServletResponse for type safety
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// Get the requested URI
		String uri = req.getRequestURI();

		if (uri.endsWith(".css") || uri.endsWith(".png") || uri.endsWith(".jpg")) {
			chain.doFilter(request, response);
			return;
		}
		// Separate flags for login, login/logout servlets, and register page/servlet
		// for better readability
		/*
		 * boolean isLogin = uri.endsWith(StringUtils.PAGE_URL_LOGIN); boolean
		 * isLoginServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGIN); boolean
		 * isLogoutServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGOUT); boolean
		 * isHomePage = uri.endsWith("/index.jsp"); // Adjust this according to your
		 * actual home page URL boolean isCatalogPage = uri.endsWith("/catalog.jsp"); //
		 * Adjust this according to your actual catalog page URL
		 * 
		 * boolean isRegisterPage = uri.endsWith(StringUtils.PAGE_URL_REGISTER); boolean
		 * isRegisterServlet = uri.endsWith(StringUtils.SERVLET_URL_REGISTER);
		 * 
		 * // URLs specific to pages that require login boolean isProfilePage =
		 * uri.endsWith(StringUtils.PAGE_URL_PROFILE); boolean isAdminDash =
		 * uri.endsWith(StringUtils.PAGE_URL_ADMIN_DASH); boolean isAdminCatalog =
		 * uri.endsWith(StringUtils.PAGE_URL_ADMIN_CATALOG); boolean isAdminCatalogEdit
		 * = uri.endsWith(StringUtils.PAGE_URL_ADMIN_CATALOG_EDIT); boolean isSearchUser
		 * = uri.endsWith(StringUtils.PAGE_URL_SEARCH_USER); boolean isUserDetails =
		 * uri.endsWith(StringUtils.PAGE_URL_USER_DETAILS); boolean isForgotPass =
		 * uri.endsWith(StringUtils.PAGE_URL_FORGOT_PASS); boolean isProfileSection2 =
		 * uri.endsWith(StringUtils.PAGE_URL_PROFILE_SECTION2); boolean isSearchCatalog
		 * = uri.endsWith(StringUtils.PAGE_URL_SEARCH_CATALOG);
		 * 
		 * // Check if a session exists and if the user attribute is set to determine
		 * login // status HttpSession session = req.getSession(false); // Don't create
		 * a new session if one doesn't exist UserModel user = (UserModel) (session !=
		 * null ? session.getAttribute("user") : null); boolean isLoggedIn = user !=
		 * null;
		 * 
		 * // Allow access to home page and catalog page even if the user is not logged
		 * in if ((isHomePage || isCatalogPage) && !isLogin && !isLogoutServlet) {
		 * chain.doFilter(request, response); return; }
		 * 
		 * // Redirect to login if user is not logged in and trying to access a
		 * protected // resource if (!isLoggedIn && !(isHomePage || isLogin ||
		 * isLoginServlet || isRegisterPage || isRegisterServlet)) {
		 * req.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request,
		 * response); return; } else if (isLoggedIn) { // If the user is logged in,
		 * check if they are trying to access restricted pages if
		 * (!user.getUserType().equals("admin")) { // Deny access to admin pages for
		 * normal users if (isAdminDash || isAdminCatalog || isAdminCatalogEdit ||
		 * isSearchUser || isUserDetails) {
		 * session.setAttribute(StringUtils.MESSAGE_ERROR,
		 * "You don't have access to this page");
		 * req.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request,
		 * response); return; } }
		 * 
		 * // Deny access to profile and other restricted pages for guests if (!isLogin
		 * && !isLogoutServlet && !isProfilePage && !isForgotPass && !isProfileSection2
		 * && !isSearchCatalog) {
		 * req.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request,
		 * response); return; } }
		 * 
		 * // Allow access to the requested resource if user is logged in or accessing
		 * // unprotected resources chain.doFilter(request, response);
		 */

	}

	@Override
	public void destroy() {
	}
}