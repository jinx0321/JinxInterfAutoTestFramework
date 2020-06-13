package com.mock.URLDealService;

import org.springframework.stereotype.Service;

import com.mock.Data.CacheData;

@Service
public class UrlDeal {
	
	StringBuffer content;
	public String GetUrlData(String url) {
		 content=new StringBuffer();
		 content.append("{\"error_info\":\"Url不存在\"}");
		CacheData.XmlUtils.UpdateCacheData();
		if(CacheData.RootData.getUrldata()!=null) {
		CacheData.RootData.getUrldata().forEach(v->{
			if(v.getUrl().equals(url)) {
			 content=new StringBuffer();
			 content.append(v.getData());
			}
		});
		}
		return content.toString();
	};
	

}
