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

		boolean isLogin = uri.endsWith(StringUtils.PAGE_URL_LOGIN) || uri.endsWith(StringUtils.SERVLET_URL_LOGIN);
		boolean isRegister = uri.endsWith(StringUtils.PAGE_URL_REGISTER)
				|| uri.endsWith(StringUtils.SERVLET_URL_REGISTER);
		boolean isHome = uri.endsWith("/home.jsp"); 
		boolean isCatalog = uri.endsWith("/pages/cataloguser.jsp"); 

		HttpSession session = req.getSession(false);
		UserModel user = (UserModel) (session != null ? session.getAttribute("user") : null);
		boolean isLoggedIn = user != null;

		if (isLogin || isRegister || isHome || isCatalog || !isLoggedIn) {
			chain.doFilter(request, response); // Allow access
		} else {
			// If the user is not logged in and trying to access restricted page, redirect
			// to login
			res.sendRedirect(req.getContextPath() + StringUtils.PAGE_URL_LOGIN);
		}
	}

	@Override
	public void destroy() {
	}
}