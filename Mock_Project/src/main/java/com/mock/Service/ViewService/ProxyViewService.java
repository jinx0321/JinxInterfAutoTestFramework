package com.mock.Service.ViewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.mock.Bean.Data.EnvVar;
import com.mock.Bean.Data.RequestData;
import com.mock.Bean.Data.RootData;
import com.mock.Bean.Data.UrlData;
import com.mock.Bean.Log.Info;
import com.mock.Bean.Proxy.Proxy;
import com.mock.Service.URLDealService.CommonInter.CacheOp;

@Service
public class ProxyViewService {

	@Autowired
	@Qualifier("CacheOpImpl")
	CacheOp<RootData> CacheOp;
	
	@Autowired
	@Qualifier("CacheOpImpl_Env")
	CacheOp<EnvVar> CacheOp_Env;
	
	@Autowired
	Info Info;
	
	
	public String del_Proxy(JSONObject proxy) {
		System.out.println("proxy删除"+proxy.toJSONString());
		String url=proxy.getString("url");
		String reqid=proxy.getString("reqid");
		//如果url为空
		if(isNull(url)) {
			return EnvProxyDel(proxy);
		}else {
			if(isNull(reqid)) {
				return UrlProxyDel(proxy);
			}else{
				return ReqProxyDel(proxy);
			}	
		}
	}
	
	private String EnvProxyDel(JSONObject proxy) {
		boolean is_exist=false;
	      Proxy pro = null;
		for(Proxy pr:CacheOp_Env.GetCache().getProxylist()) {
			if(pr.getId().equals(proxy.get("id"))) {
				pro=pr;
				is_exist=true;
			}
		}
		if(is_exist) {
			CacheOp_Env.GetCache().getProxylist().remove(pro);
			return Info.toJson("删除成功", "success");
		}else {
			return Info.toJson("Proxy不存在", "fail");
			
		}
	}
	
	private String UrlProxyDel(JSONObject proxy) {
		// TODO Auto-generated method stub
		return null;
	}

	private String ReqProxyDel(JSONObject proxy) {
		// TODO Auto-generated method stub
		return null;
	}


	

	public String upd_Proxy(JSONObject proxy) {
		System.out.println("proxy新增修改"+proxy.toJSONString());
		String url=proxy.getString("url");
		String reqid=proxy.getString("reqid");
		//如果url为空
		if(isNull(url)) {
			return EnvProxyUpd(proxy);
		}else {
			if(isNull(reqid)) {
				return UrlProxyUpd(proxy);
			}else{
				return ReqProxyUpd(proxy);
			}	
		}
	}
	
	//全局代理新增
	private String EnvProxyUpd(JSONObject proxy) {
		boolean is_exist=false;
		for(Proxy pr:CacheOp_Env.GetCache().getProxylist()) {
			if(pr.getId().equals(proxy.get("id"))) {
				pr.setIp(proxy.getString("ip"));
				pr.setPort(proxy.getString("port"));
				pr.setIs_proxy(proxy.getString("is_proxy"));
				pr.setDns(proxy.getString("dns"));
				is_exist=true;
				return Info.toJson("更新成功", "success");
			}
		}
		if(!is_exist) {
			//新增
			Proxy pr=new Proxy();
			pr.setId(proxy.getString("id"));
			pr.setIp(proxy.getString("ip"));
			pr.setPort(proxy.getString("port"));
			pr.setIs_proxy(proxy.getString("is_proxy"));
			pr.setDns(proxy.getString("dns"));
			CacheOp_Env.GetCache().getProxylist().add(pr);
			return Info.toJson("新增成功", "success");
		}
		
		return Info.toJson("更新失败", "fail");
	}
	//url代理新增
    private String UrlProxyUpd(JSONObject proxy) {
		for(UrlData ud:CacheOp.GetCache().getUrldata()) {
			if(ud.getUrl().equals(proxy.get("url"))) {
				//如果是空,则是新增
				if(ud.getProxy()==null) {
					Proxy pr=new Proxy();
					pr.setId(proxy.getString("id"));
					pr.setIp(proxy.getString("ip"));
					pr.setPort(proxy.getString("port"));
					pr.setIs_proxy(proxy.getString("is_proxy"));
					pr.setDns(proxy.getString("dns"));
					ud.setProxy(pr);
					return Info.toJson("新增成功", "success");
				}
				//否则是修改
				else {
					if(ud.getProxy().getId().equals(proxy.get("id"))) {
						ud.getProxy().setIp(proxy.getString("ip"));
						ud.getProxy().setPort(proxy.getString("port"));
						ud.getProxy().setIs_proxy(proxy.getString("is_proxy"));
						ud.getProxy().setDns(proxy.getString("dns"));
						return Info.toJson("修改成功", "fail");
					}else {
						return Info.toJson("该url不存在该proxy", "fail");
					}
				}
			}
		}
          return Info.toJson("url找不到", "fail");
	}
    //请求参数代理新增
    private String ReqProxyUpd(JSONObject proxy) {
    	
    	for(UrlData ud:CacheOp.GetCache().getUrldata()) {
			if(ud.getUrl().equals(proxy.get("url"))) {
			    for(RequestData rd:ud.getRequestData()) {
			    	if(rd.getId().equals(proxy.get("reqid"))) {
			    		//如果是空,则是新增
						if(rd.getProxy()==null) {
							Proxy pr=new Proxy();
							pr.setId(proxy.getString("id"));
							pr.setIp(proxy.getString("ip"));
							pr.setPort(proxy.getString("port"));
							pr.setIs_proxy(proxy.getString("is_proxy"));
							pr.setDns(proxy.getString("dns"));
							rd.setProxy(pr);
							return Info.toJson("新增成功", "success");
						}else {
							if(rd.getProxy().getId().equals(proxy.get("id"))) {
								rd.getProxy().setIp(proxy.getString("ip"));
								rd.getProxy().setPort(proxy.getString("port"));
								rd.getProxy().setIs_proxy(proxy.getString("is_proxy"));
								rd.getProxy().setDns(proxy.getString("dns"));
								return Info.toJson("修改成功", "success");
							}else {
								return Info.toJson("该请求参数不存在该proxy", "fail");
							}
							
						}
			    	}
			    }
				
			}
    	}
    	
    	return Info.toJson("请求参数找不到", "fail");
	}
	
	
	private boolean isNull(String data) {
		if(data!=null&&!data.equals("")) {
			return false;
		}
		return true;
	}
}
