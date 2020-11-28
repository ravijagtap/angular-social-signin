package com.windmillsmartsolutions.filters;

import java.io.IOException;
import java.util.TimeZone;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.windmillsmartsolutions.utils.AuthEntryConstant;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCORSFilter implements Filter{

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	Environment env;
	
	@Autowired
	AuthEntryConstant appConstant;
	
	@Override
	public void init(FilterConfig fc) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpServletRequest request = (HttpServletRequest) req;
		
		TimeZone timeZone = RequestContextUtils.getTimeZone(request);
		logger.error("******************timeZone:" + timeZone);
		if(timeZone != null) {
			logger.error("******************timeZoneName:"+timeZone.getDisplayName());
			logger.error("******************timeZoneName:"+timeZone.getID());
			logger.error("******************DefaulttimeZoneName:"+timeZone.getDefault());
		}
		
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Max-Age", "10800");
		    response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, TIMEZONE, Authorization, credential, X-AUTH-TOKEN, PROVIDER");
			logger.error("request.getMethod()*******************" + request.getMethod());
			if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
				logger.error("5");
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				logger.error("6");
				chain.doFilter(req, resp);
			}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
