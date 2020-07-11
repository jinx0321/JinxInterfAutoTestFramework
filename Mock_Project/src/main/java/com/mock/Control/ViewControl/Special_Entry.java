package com.mock.Control.ViewControl;

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
import com.mock.Service.ViewService.ViewService;
import com.mock.Utils.ControlUtils.RequestUtils;
import com.mock.Utils.ControlUtils.UrlUtils;

@Controller
public class Special_Entry {
	
	@Autowired
	UrlDeal UrlDeal;
	@Autowired
	UrlUtils UrlUtils;
	@Autowired
	ViewService ViewDeal;
	@Autowired
	RequestUtils RequestUtils;
	
	@RequestMapping(value="/mock/mod_requestdata",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String Modify_Request_Data(HttpServletRequest request) throws IOException, IOException {
		
		return ViewDeal.ModRequestData(RequestUtils.toJsonObject(request));


	}
	@RequestMapping(value="/mock/del_requestdata",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String Delete_Request_Data(HttpServletRequest request) throws IOException, IOException {
		return ViewDeal.DelRequestData(RequestUtils.toJsonObject(request));
	}
	


}
