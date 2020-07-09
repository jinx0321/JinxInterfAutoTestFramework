package com.mock.Service.FilterService;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mock.Bean.Data.RootData;
import com.mock.Bean.Data.UrlData;
import com.mock.Filter.*;
import com.mock.Service.URLDealService.CommonInter.CacheOp;
import com.mock.Service.URLDealService.CommonInter.CacheOpImpl_RootData;

/**
 * 判断当前url是什么服务
 * @author jinxh29224
 *
 */
@Service
public class JudgeService{
	
	@Autowired
	@Qualifier("CacheOpImpl")
	CacheOp<RootData> CacheOp;
	

	/**
	 * false is forward
	 * true is redirect
	 * @param request
	 * @return
	 */
	private boolean forward_or_redirect(HttpServletRequest request){
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
		    
			String uri=request.getRequestURI();
			
		    for(UrlData ud:CacheOp.GetCache().getUrldata()) {
		    	if(ud.equals(uri)){
		    		String x=ud.getRequestData().size()!=0?"":"";
		    	}
		    }
		    
		
		return false;
	}
	
}
