package com.mock.Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mock.Bean.Data.CacheData;
import com.mock.Bean.Data.RootData;
import com.mock.Bean.Data.UrlData;
import com.mock.Dao.XmlUtils.XmlUtils;
import com.mock.Service.URLDealService.UrlDeal;
import com.mock.Service.URLDealService.UrlUtils;
import com.mock.Service.URLDealService.ViewDeal;
import com.mock.Utils.ControlUtils.*;
/**
 * 公共入口
 * @author jinxh29224
 *
 */
@Controller
public class Mock_Entry {
	
	@Autowired
	UrlDeal UrlDeal;
	@Autowired
	UrlUtils UrlUtils;
	@Autowired
	ViewDeal ViewDeal;
	@Autowired
	RequestUtils RequestUtils;
	
	
	@RequestMapping(value="/mock/data",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String common(HttpServletRequest request) {
		return UrlDeal.GetUrlData(UrlUtils.UrlParserAfter(request.getParameter("data")));
	}
	
	
	@RequestMapping("/mock")
	public String ui() {
			  return "mock_ui";
		  
	}
	
	//跳转到子页面
	@RequestMapping("/mock_special")
	public String mock_special(HttpServletRequest request,Model model) {
	     model.addAttribute("urldata",ViewDeal.GetUrlDataJson(UrlUtils.UrlParserAfter(request.getParameter("data"))));
		 return "mock_special";
		  
	}
	
	@RequestMapping(value="/mock/query_data",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String query_data(Model model) {
		return ViewDeal.QueryUrlData();
	}
	
	@RequestMapping(value="/mock/update_data",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update_data(HttpServletRequest request) {
        return ViewDeal.UpdateData(RequestUtils.toJsonObject(request));
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
        		request.getParameter("data"),request.getParameter("is_forward"),request.getParameter("forward_addr"));
	}
	
}
