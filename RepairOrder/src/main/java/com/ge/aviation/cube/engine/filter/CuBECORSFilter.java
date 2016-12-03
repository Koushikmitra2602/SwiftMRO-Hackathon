package com.ge.aviation.cube.engine.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CuBECORSFilter implements Filter {
	static final Logger log = Logger.getLogger(CuBECORSFilter.class.getName());

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		log.info("CORS FILTER");
		HttpServletRequest request = (HttpServletRequest) req;
		log.info("Request Header cube_ui_username::" + request.getHeader("cube_ui_username"));
		log.info("Request Header cube_ui_password::" + request.getHeader("cube_ui_password"));
		
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
																					// 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.
		
		
		HttpServletResponse response2 = (HttpServletResponse) res;
		log.info("Response Headers set");
		log.info(response2.getHeader("Access-Control-Allow-Origin"));
		log.info(response2.getHeader("Access-Control-Allow-Headers"));
		log.info(response2.getHeader("Access-Control-Allow-Methods"));
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}
}
