package com.mock.Service.URLDealService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.mock.Dao.XmlUtils.*;
import com.mock.Service.URLDealService.CommonInter.CacheOp;
import com.mock.Service.URLDealService.CommonInter.CacheOpImpl_RootData;
import com.mock.Bean.Data.RootData;
import com.mock.Cache.CacheData;

@Service
public class UrlDeal {
	@Autowired
	@Qualifier("CacheOpImpl")
	CacheOp<RootData> CacheOp;
	
	StringBuffer content;
	public String GetUrlData(String url) {
		 content=new StringBuffer();
		 content.append("{\"error_info\":\"Url不存在\"}");
		 CacheOp.CacheDataLoad();
		if(CacheOp.GetCache().getUrldata()!=null) {
			CacheOp.GetCache().getUrldata().forEach(v->{
			if(v.getUrl().equals(url)) {
			 content=new StringBuffer();
			 content.append(v.getData());
			}
		});
		}
		return content.toString();
	};
	

	/**
	 * 匹配请求参数
	 */
	public void regexReqParam(String param,HttpServletRequest request) {
		
	}
	
}
