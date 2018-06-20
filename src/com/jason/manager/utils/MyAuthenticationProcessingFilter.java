package com.jason.manager.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationProcessingFilter extends HttpServlet implements
		Filter {

	private static final long serialVersionUID = 4505928147840765022L;

	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) servletRequest;
//		HttpServletResponse response = (HttpServletResponse) servletResponse;
//		String code = request.getParameter("j_code");
//		if ("".equals(code) || code == null) {
//			response.sendRedirect(request.getContextPath()+"/admin/login.jsp?error=5");
//		} else {
//			Cookie[] cookie = request.getCookies();
//			String codes = "";
//			for (int i = 0; cookie != null && i < cookie.length; i++) {
//				if (Const.MOSS_KEY_COOKIE_CHECKCODE.equals(cookie[i].getName())) {
//					codes = cookie[i].getValue();
//				}
//			}
//			if (!"".equals(codes) && codes != null) {
//				if (code.equalsIgnoreCase(codes)) {
//					filterChain.doFilter(request, response);
//				} else {
//					response.sendRedirect(request.getContextPath()+"/admin/login.jsp?error=5");
//				}
//			} else {
//				response.sendRedirect(request.getContextPath()+"/admin/login.jsp?error=1");
//			}
//		}
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		filterChain.doFilter(request, response);
	}

}
