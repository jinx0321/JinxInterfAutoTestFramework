package com.mock.URLDealService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.mock.Data.CacheData;
import com.mock.Data.UrlData;

@Component
public class ViewDeal {
	
	@Autowired
	UrlUtils UrlUtils;
	
	public String QueryUrlData() {
		return 	JSON.toJSONString(CacheData.RootData.getUrldata());
		
	}

	
	
	public String UpdateData(String url,String data, String is_forward, String forward_addr) {
		System.out.println(url+"-"+data);
		List<UrlData> list=	CacheData.RootData.getUrldata();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getUrl().equals(url)) {
				list.get(i).setData(data);
				list.get(i).setForward_Addr(forward_addr);
				list.get(i).setIs_Forward(is_forward);
				try {
					CacheData.XmlUtils.UpdateXml(CacheData.RootData);
				}catch (Exception e) {
					return "{\"info\":\""+e.getMessage()+"\",\"flag\":\"fail\"}";
				}
			    
				return "{\"info\":\"更新成功\",\"flag\":\"success\"}";
			}
			
		}

		
		return "{\"info\":\"更新失败,Url找不到\",\"flag\":\"fail\"}";
		
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
				return "{\"info\":\""+e.getMessage()+"\",\"flag\":\"fail\"}";
			}
		}else {
			return "{\"info\":\"删除失败,Url找不到\",\"flag\":\"fail\"}";
		}
		return "{\"info\":\"删除成功\",\"flag\":\"success\"}";
	}



	public String AddData(String url, String data,String is_forward,String forward_addr) {
		if(!UrlUtils.is_Url(url)) {
			return "{\"info\":\"Url不合法,请输入/xxx/xxx/xxx格式\",\"flag\":\"fail\"}";
		}
		
		for(UrlData ud:CacheData.RootData.getUrldata()) {
			if(ud.getUrl().equals(url)) {
				return "{\"info\":\"Url已存在\",\"flag\":\"fail\"}";
			}
			
		}
		UrlData ud=new UrlData();
		ud.setUrl(url);
		ud.setData(data);
		ud.setIs_Forward(is_forward);
		ud.setForward_Addr(forward_addr);
		CacheData.RootData.getUrldata().add(ud);
		try {
			CacheData.XmlUtils.UpdateXml(CacheData.RootData);
		}catch (Exception e) {
			return "{\"info\":\""+e.getMessage()+"\",\"flag\":\"fail\"}";
		}
		
		return "{\"info\":\"新增成功\",\"flag\":\"success\"}";
	}

}
