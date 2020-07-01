package com.mock.Filter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

/**
 * 本地url过滤,对前端资源url过滤
 * @author jinxh29224
 *
 */
@Service
public class LocalFilter {
	public String forward="forward";
	public String redict="redict";
	public String release="release";
	
	public String commonfilter(HttpServletRequest request) {
		String uri=request.getRequestURI();
		
		//最先判断本地url
	    if(localurifilter(uri)) {
	    	return release;
	    }else {
	    	return forward;
	    }
		
	    /*
	    String method=request.getMethod();
	    Enumeration<String> headerenum=request.getHeaderNames();
	    Map<String,String> headers=new HashMap<String,String>();
	    while(headerenum.hasMoreElements()){
	    	String name=headerenum.nextElement();
	    	headers.put(name, request.getHeader(name));
	    }
	    Enumeration<String> paramenum=request.getParameterNames();
	    Map<String,String> params=new HashMap<String,String>();
	    while(paramenum.hasMoreElements()){
	    	String name=paramenum.nextElement();
	    	params.put(name, request.getParameter(name));
	    }
	 */
	   
	
		
	}
	
	/**
	 * 本地资源过滤
	 * @param path
	 * @return
	 */
	public boolean localurifilter(String path){
		 if(path.equals("/mock")) {
	        	return true; 
	        } else if(path.startsWith("/mock/")) {
	        	return true; 
	        }else if(path.startsWith("/mock_special")) {
	        	return true; 
	        }else if(path.startsWith("/layui/")) {
	        	return true; 
	        }else if(path.startsWith("/jquery-3.3.1/")){
	        	return true; 
	        }
	        else{
	        	return false; 
	        }
	}
	

	


}
