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
//		if (uri.endsWith(StringUtils.PAGE_URL_CATALOG) || uri.endsWith("/")) {
//			request.getRequestDispatcher(StringUtils.SERVLET_URL_USER_CATALOG).forward(request, response);
////	        res.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_HOME);
//			return;
//		}

	}

	@Override
	public void destroy() {
	}
}