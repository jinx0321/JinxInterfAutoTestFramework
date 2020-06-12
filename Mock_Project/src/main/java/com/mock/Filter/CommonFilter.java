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

import com.mock.Data.CacheData;
import com.mock.URLDealService.UrlUtils;
import com.mock.Utils.XmlUtils.XmlUtils;
 
@Component
@WebFilter(urlPatterns = "/*", filterName = "common")
public class CommonFilter implements Filter {
	@Autowired
	UrlUtils UrlUtils;
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("过滤器启动");
	}
 
	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		 HttpServletRequest httpRequest = (HttpServletRequest)request;
	        HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper((HttpServletResponse) response);
	        System.out.println(httpRequest.getRequestURI());
	        String path=httpRequest.getRequestURI();
	        
	        if(path.startsWith("/mock")) {
	        	chain.doFilter(request, response);  
	        }else if(path.startsWith("/layui/")) {
	        	chain.doFilter(request, response);  
	        }else if(path.startsWith("/jquery-3.3.1/")){
	        	chain.doFilter(request, response);  
	        }
	        else{
	          httpRequest.getRequestDispatcher("/common?data="+UrlUtils.UrlParserBefore(path)).forward(request,response);
	        }
	
            //chain.doFilter(request,response);	        
	        return;
	    }
	
	@Override
	public void destroy() {
		System.out.println("过滤器销毁");
	}
 
}
