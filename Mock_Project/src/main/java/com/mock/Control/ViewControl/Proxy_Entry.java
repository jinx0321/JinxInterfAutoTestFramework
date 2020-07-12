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
import com.mock.Bean.Data.RootData;
import com.mock.Service.URLDealService.UrlDeal;
import com.mock.Service.URLDealService.CommonInter.CacheOp;
import com.mock.Service.ViewService.ProxyViewService;
import com.mock.Service.ViewService.ViewService;
import com.mock.Utils.ControlUtils.RequestUtils;
import com.mock.Utils.ControlUtils.UrlUtils;

@Controller
public class Proxy_Entry {
	@Autowired
	ViewService ViewDeal;
	@Autowired
	UrlDeal UrlDeal;
	@Autowired
	UrlUtils UrlUtils;
	@Autowired
	ProxyViewService ProxyViewService;
	@Autowired
	RequestUtils RequestUtils;
	
	@RequestMapping(value="/mock/upd_proxy",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String Update_Proxy_Data(HttpServletRequest request) throws IOException, IOException {

		return ProxyViewService.upd_Proxy(RequestUtils.toJsonObject(request));
	}
	@RequestMapping(value="/mock/del_proxy",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String Delete_Proxy_Data(HttpServletRequest request) throws IOException, IOException {
		return ProxyViewService.del_Proxy(RequestUtils.toJsonObject(request));
	}
	@RequestMapping(value="/mock/getproxy",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getProxy(HttpServletRequest request) {
		return ProxyViewService.GetProxy(request.getParameter("url"),request.getParameter("reqid"));
	}


}
