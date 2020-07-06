package com.mock.Utils.ControlUtils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Component
public class RequestUtils {
	
	
	public JSONObject toJsonObject(HttpServletRequest request) {
		Enumeration<String> paramenum=request.getParameterNames();
		Enumeration<String> headerenum=request.getHeaderNames();
	    if(request.getHeader("Content-Type").contains("application/json")) {
	    	return JSON.parseObject(request.getParameter("content"));
	    }
		List<String> names=new ArrayList<String>(); 
		if(paramenum.hasMoreElements()){
			names.add(paramenum.nextElement());
		}
		JSONObject json=new JSONObject();
		names.forEach(n->{
			json.put(n, request.getParameter(n));
		});
		return json;
	}

}
