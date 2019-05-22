package com.qian.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qian.util.PathUtils;

/**
 * @author Lu Kongwen
 * @version Create time：2016-6-7 下午2:50:23
 * @Description
 */
public class LogFilter extends OncePerRequestFilter {

	private static Logger log = Logger.getLogger(LogFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();

		String url = request.getRequestURI();

		String parameters = mapper.writeValueAsString(request.getParameterMap());
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("url = {").append(url).append("}");
		sb.append(",").append("parameters = {").append(parameters).append("}");
		
		log.info(sb);

		filterChain.doFilter(request, response);
	}

}
