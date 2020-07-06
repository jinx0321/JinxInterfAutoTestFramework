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
import com.mock.Service.ViewService.ViewDeal;
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

}
