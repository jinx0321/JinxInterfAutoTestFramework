package com.mock.Service.ViewService;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Env;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.mock.Bean.Data.EnvVar;
import com.mock.Bean.Data.RequestData;
import com.mock.Bean.Data.RootData;
import com.mock.Bean.Data.UrlData;
import com.mock.Service.URLDealService.CommonInter.CacheOpImpl_RootData;
import com.mock.Service.URLDealService.CommonInter.CacheOp;
import com.mock.Utils.ControlUtils.UrlUtils;
import com.mock.Bean.Log.Info;
import com.mock.Cache.CacheData;

@Service
public class ViewService {
	
	
	@Autowired
	UrlUtils UrlUtils;
	
	@Autowired
	@Qualifier("CacheOpImpl")
	CacheOp<RootData> CacheOp;
	
	@Autowired
	@Qualifier("CacheOpImpl_Env")
	CacheOp<EnvVar> CacheOp_Env;
	
	
	@Autowired
	Info Info;
	

	
	public String QueryUrlData() {
		return 	JSON.toJSONString(CacheOp.GetCache().getUrldata());
		
	}

	
	/**
	 * 更新urldata
	 * @param url
	 * @param data
	 * @param is_forward
	 * @param forward_addr
	 * @return
	 */
	public synchronized String UpdateData(JSONObject json) {
		System.out.println(json.getString("url")+"-"+json.getString("data"));
		List<UrlData> list=	CacheOp.GetCache().getUrldata();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getUrl().equals(json.getString("url"))) {
				list.get(i).setData(json.getString("data"));
				list.get(i).setForward_Addr(json.getString("forward_addr"));
				list.get(i).setIs_Forward(json.getString("is_forward"));
				try {
					CacheOp.UpdateDataDao();
				}catch (Exception e) {
					return Info.toJson(e.getMessage(), "fail");
				}
			    
				return Info.toJson("更新成功", "success");
			}
			
		}

		return Info.toJson("更新失败,Url找不到", "fail");
	
	}



	/**
	 * 删除urldata
	 * @param url
	 * @return
	 */
	public synchronized String DeleteData(String url) {
		UrlData ud = null;
		System.out.println(url);
		List<UrlData> list=	CacheOp.GetCache().getUrldata();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getUrl().equals(url)) {
				ud=list.get(i);
			}
		}
		if(ud!=null) {
			try {
				CacheOp.GetCache().getUrldata().remove(ud);
				CacheOp.UpdateDataDao();
			}catch (Exception e) {
				return Info.toJson(e.getMessage(), "fail");
			}
		}else {
			return Info.toJson("删除失败,Url找不到", "fail");
		}
		return Info.toJson("删除成功", "success");
	}



	/**
	 * 新增urldata
	 * @param url
	 * @param data
	 * @param is_forward
	 * @param forward_addr
	 * @return
	 */
	public synchronized String AddData(JSONObject json/*String url, String data,String is_forward,String forward_addr*/) {
		System.out.println(json.toJSONString());
		if(!UrlUtils.is_Url(json.getString("url"))) {
			return Info.toJson("Url不合法,请输入/xxx/xxx/xxx格式", "fail");
		}
		if(CacheOp.GetCache().getUrldata()!=null||CacheOp.GetCache().getUrldata().size()!=0) {
		for(UrlData ud:CacheOp.GetCache().getUrldata()) {
			if(ud.getUrl().equals(json.getString("url"))) {
				return Info.toJson("Url已存在", "fail");
			}
			
		}
		}
		UrlData ud=new UrlData();
		ud.setUrl(json.getString("url"));
		ud.setData(json.getString("data"));
		ud.setIs_Forward(json.getString("is_forward"));
		ud.setForward_Addr(json.getString("forward_addr"));
		CacheOp.GetCache().getUrldata().add(ud);
		try {
			CacheOp.UpdateDataDao();
		}catch (Exception e) {
			return Info.toJson(e.getMessage(), "fail");

		}
		return Info.toJson("新增成功", "success");
	}
	
	
	
	public synchronized String ModRequestData(JSONObject requestdata) {
		
		//默认不存在
		boolean is_flag=false;
		RequestData rd =requestdata.getJSONObject("requestData").toJavaObject(RequestData.class);
		
		for(UrlData ud:CacheOp.GetCache().getUrldata()) {
			if(ud.getUrl().equals(requestdata.get("url"))) {
				List<RequestData> rdl=ud.getRequestData();
				for(RequestData rdata:rdl) {
					if(rdata.getParamId().equals(rd.getParamId())) {
						rdata.setData(rd.getData());
						rdata.setIs_Forward(rd.getIs_Forward());
						rdata.setParam(rd.getParam());
						rdata.setIs_Disable(rd.getIs_Disable());
						is_flag=true;
					}
				}
				if(!is_flag) {
				ud.getRequestData().add(rd);
				}
				CacheOp.UpdateDataDao();
				return Info.toJson("更新完成", "success");
			}
		}
		return Info.toJson("Url不存在", "fail");
	}
	
	
	
	public synchronized String DelRequestData(JSONObject requestdata) {
		//是否找到参数
		boolean is_find_param=false;
		RequestData rd =requestdata.getJSONObject("requestData").toJavaObject(RequestData.class);
		System.out.println(rd);
		RequestData rddel = null;
		for(UrlData ud:CacheOp.GetCache().getUrldata()) {
			if(ud.getUrl().equals(requestdata.get("url"))) {
			    List<RequestData> rdl=ud.getRequestData();
			      for(RequestData rdata:rdl) {
				     if(rdata.getParamId().equals(rd.getParamId())) {
					     is_find_param=true;
					    rddel=rdata;
				      }
			    }
			  	if(is_find_param) {
			  		rdl.remove(rddel);
			  		CacheOp.UpdateDataDao();
			  		return Info.toJson("删除成功", "success");
				}else {
					return Info.toJson("参数不存在", "fail");
				}
			      
			}
		}
		return Info.toJson("Url不存在", "fail"); 
	}
	
	
	

    public UrlData GetUrlDataObject(String Url) {
    	for(UrlData ud:CacheOp.GetCache().getUrldata()) {
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
