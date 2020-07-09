package com.mock.Filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.mock.Utils.ControlUtils.*;

/**
 * 本地url过滤,对前端资源url过滤
 * @author jinxh29224
 *
 */
@Service
public class LocalFilter {
	public String forward="forward";
	public String redirect="redirect";
	public String release="release";
	
	@Autowired
	RequestUtils RequestUtils;
	
	
	public String commonfilter(HttpServletRequest request) {
		
		
		String uri=request.getRequestURI();
		
		//最先判断本地url
	    if(localurifilter(uri)) {
	    	return release;
	    }else {
	    	return forward;
	    }
		
	}
	
	
	
	/**
	 * 判断是否是本地资源过滤
	 * @param path
	 * @return
	 */
	private boolean localurifilter(String path){
		 if(path.equals("/mock")) {
	        	return true; 
	        } else if(path.startsWith("/mock/")) {
	        	return true; 
	        }else if(path.startsWith("/mock_special")) {
	        	return true; 
	        }else if(path.startsWith("/mock_proxy")) {
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
