package com.mock.Control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mock.Service.URLDealService.UrlDeal;
import com.mock.Service.URLDealService.UrlUtils;
import com.mock.Service.URLDealService.ViewDeal;

@Controller
public class Special_Entry {
	
	@Autowired
	UrlDeal UrlDeal;
	@Autowired
	UrlUtils UrlUtils;
	@Autowired
	ViewDeal ViewDeal;
	
	@RequestMapping(value="/mock/mod_requestdata",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String Modify_Request_Data(HttpServletRequest request) throws IOException, IOException {
		
		return ViewDeal.ModRequestData(JSONObject.parseObject(request.getParameter("content")));


	}

}
