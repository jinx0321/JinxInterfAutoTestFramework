package com.mock.Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.mock.URLDealService.UrlDeal;
import com.mock.URLDealService.UrlUtils;
import com.mock.URLDealService.ViewDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mock.Data.CacheData;
import com.mock.Data.RootData;
import com.mock.Data.UrlData;
import com.mock.Utils.XmlUtils.XmlUtils;
/**
 * 公共入口
 * @author jinxh29224
 *
 */
@Controller
public class Common_Entry {
	
	@Autowired
	UrlDeal UrlDeal;
	@Autowired
	UrlUtils UrlUtils;
	
	@RequestMapping(value="/common",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String common(HttpServletRequest request) {
		return UrlDeal.GetUrlData(UrlUtils.UrlParserAfter(request.getParameter("data")));
	}
	
	@Autowired
	ViewDeal ViewDeal;
	
	@RequestMapping("/mock")
	public String ui(Model model) {
		return "ui";
	}
	
	@RequestMapping(value="/mock/query_data",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String query_data(Model model) {
		return ViewDeal.QueryUrlData();
	}
	
	@RequestMapping(value="/mock/update_data",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update_data(HttpServletRequest request) {
        return ViewDeal.UpdateData(request.getParameter("url"),
        		request.getParameter("data"));
	}
	
	
	@RequestMapping(value="/mock/delete_data",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete_data(HttpServletRequest request) {
        return ViewDeal.DeleteData(request.getParameter("url"));
	}
	@RequestMapping(value="/mock/add_data",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add_data(HttpServletRequest request) {
		return ViewDeal.AddData(request.getParameter("url"),
        		request.getParameter("data"));
	}
	
}
