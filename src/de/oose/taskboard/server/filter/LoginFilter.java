package de.oose.taskboard.server.filter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	
	private FilterConfig config;

	@Override
	public void destroy() {		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(true);
		String pageRequested = req.getRequestURL().toString();
		try {
			URI uri = new URI(pageRequested);
			String fragment = uri.getFragment();
			System.out.println(pageRequested + " " + fragment);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		filterChain.doFilter(req, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		config = arg0;
	}

}
