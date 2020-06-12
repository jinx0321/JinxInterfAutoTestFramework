package com.mock.URLDealService;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.mock.Data.CacheData;
import com.mock.Data.UrlData;

@Component
public class ViewDeal {
	
	public String QueryUrlData() {
		return 	JSON.toJSONString(CacheData.RootData.getUrldata());
		
	}

	
	
	public String UpdateData(String url,String data) {
		System.out.println(url+"-"+data);
		List<UrlData> list=	CacheData.RootData.getUrldata();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getUrl().equals(url)) {
				list.get(i).setData(data);
				try {
					CacheData.XmlUtils.UpdateXml(CacheData.RootData);
				}catch (Exception e) {
					return "{\"info\":\""+e.getMessage()+"\"}";
				}
			    
				return "{\"info\":\"更新成功\"}";
			}
			
		}

		
		return "{\"info\":\"更新失败,Url找不到\"}";
		
	}



	public String DeleteData(String url) {
		UrlData ud = null;
		System.out.println(url);
		List<UrlData> list=	CacheData.RootData.getUrldata();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getUrl().equals(url)) {
				ud=list.get(i);
			}
		}
		if(ud!=null) {
			try {
				CacheData.RootData.getUrldata().remove(ud);
				CacheData.XmlUtils.UpdateXml(CacheData.RootData);
			}catch (Exception e) {
				return "{\"info\":\""+e.getMessage()+"\"}";
			}
		}else {
			return "{\"info\":\"删除失败,Url找不到\"}";
		}
		return "{\"info\":\"删除成功\"}";
	}

}
