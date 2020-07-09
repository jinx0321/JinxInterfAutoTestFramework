package com.mock.Filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mock.Cache.CacheData;
import com.mock.Dao.XmlUtils.XmlUtils;
import com.mock.Service.FilterService.JumpService;
import com.mock.Utils.ControlUtils.UrlUtils;
 
@Component
@WebFilter(urlPatterns = "/*", filterName = "common")
public class CommonFilter implements Filter {
	@Autowired
	UrlUtils UrlUtils;
	
	@Autowired
	LocalFilter LocalFilter;
	
	@Autowired
	JumpService JumpService;
	
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("过滤器启动");
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		    HttpServletRequest httpRequest = (HttpServletRequest)request;
	        HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper((HttpServletResponse) response);
	        String status=LocalFilter.commonfilter(httpRequest) ;
	       
	        if(status.equals(LocalFilter.release)) {
	    	   JumpService.release(request, response, chain);
	        }else if(status.equals(LocalFilter.forward)){
	    	   JumpService.forward(httpRequest, httpRequest, httpResponse);
	         }
	        return;
	    }
	
	@Override
	public void destroy() {
		System.out.println("过滤器销毁");
	}
 
}
