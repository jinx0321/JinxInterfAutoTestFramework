package com.mock.Service.URLDealService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.mock.Bean.Data.CacheData;
import com.mock.Bean.Data.RequestData;
import com.mock.Bean.Data.UrlData;
import com.mock.Service.URLDealService.CommonInter.DataUpdateImp;

@Component
public class ViewDeal {
	
	@Autowired
	UrlUtils UrlUtils;
	@Autowired
	DataUpdateImp updateutil;
	
	public String QueryUrlData() {
		return 	JSON.toJSONString(CacheData.RootData.getUrldata());
		
	}

	
	/**
	 * 更新urldata
	 * @param url
	 * @param data
	 * @param is_forward
	 * @param forward_addr
	 * @return
	 */
	public String UpdateData(String url,String data, String is_forward, String forward_addr) {
		System.out.println(url+"-"+data);
		List<UrlData> list=	CacheData.RootData.getUrldata();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getUrl().equals(url)) {
				list.get(i).setData(data);
				list.get(i).setForward_Addr(forward_addr);
				list.get(i).setIs_Forward(is_forward);
				try {
					updateutil.modUrldata(CacheData.RootData);
				}catch (Exception e) {
					return "{\"info\":\""+e.getMessage()+"\",\"flag\":\"fail\"}";
				}
			    
				return "{\"info\":\"更新成功\",\"flag\":\"success\"}";
			}
			
		}

		
		return "{\"info\":\"更新失败,Url找不到\",\"flag\":\"fail\"}";
		
	}



	/**
	 * 删除urldata
	 * @param url
	 * @return
	 */
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
				updateutil.delUrldata(CacheData.RootData);
			}catch (Exception e) {
				return "{\"info\":\""+e.getMessage()+"\",\"flag\":\"fail\"}";
			}
		}else {
			return "{\"info\":\"删除失败,Url找不到\",\"flag\":\"fail\"}";
		}
		return "{\"info\":\"删除成功\",\"flag\":\"success\"}";
	}



	/**
	 * 新增urldata
	 * @param url
	 * @param data
	 * @param is_forward
	 * @param forward_addr
	 * @return
	 */
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
		RequestData rd=new RequestData();
		rd.setData("{\"test\":\"test\"}");
        rd.setIs_Forward("false");
        rd.setParam("{\"param\":\"param\"}");
//        byte[] bytes=rd.getParam().getBytes();
//        String id="";
//        for(int i=0;i<bytes.length;i++) {
//        	id=id+String.valueOf(Byte.valueOf(bytes[i]).intValue());
//        }
        rd.setParamId(String.valueOf(rd.getParam().hashCode()));
        ud.getRequestData().add(rd);
		CacheData.RootData.getUrldata().add(ud);
		try {
			updateutil.addUrldata(CacheData.RootData);
		}catch (Exception e) {
			return "{\"info\":\""+e.getMessage()+"\",\"flag\":\"fail\"}";
		}
		
		return "{\"info\":\"新增成功\",\"flag\":\"success\"}";
	}
	
	
	
	public String ModRequestData(JSONObject requestdata) {
		
		RequestData rd =requestdata.getJSONObject("requestData").toJavaObject(RequestData.class);
		
		for(UrlData ud:CacheData.RootData.getUrldata()) {
			if(ud.getUrl().equals(requestdata.get("url"))) {
				List<RequestData> rdl=ud.getRequestData();
				for(RequestData rdata:rdl) {
					if(rdata.getParamId().equals(rd.getParamId())) {
						rdata.setData(rd.getData());
						rdata.setIs_Forward(rd.getIs_Forward());
						rdata.setParam(rd.getParam());
						updateutil.modreqdata(CacheData.RootData);
						return "{\"info\":\"更新完成\",\"flag\":\"success\"}";
					}
				}
				return "{\"info\":\"paramid不存在\",\"flag\":\"fail\"}";
			}
		}
		
		
		return "{\"info\":\"Url不存在\",\"flag\":\"fail\"}";
	}
	

    public UrlData GetUrlDataObject(String Url) {
    	for(UrlData ud:CacheData.RootData.getUrldata()) {
    		if(ud.getUrl().equals(Url)) {
    			return ud;
    		}
    	}
    	return new UrlData();
    }
	
    
    public String GetUrlDataJson(String Url) {
    	return JSON.toJSONString(GetUrlDataObject(Url));
    }
}
