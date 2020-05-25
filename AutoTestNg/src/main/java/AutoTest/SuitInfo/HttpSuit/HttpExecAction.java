package AutoTest.SuitInfo.HttpSuit;

import java.util.HashMap;
import java.util.Map;

import javax.imageio.IIOException;

import AutoTest.Base.BaseFlow;
import AutoTest.Base.CaseInfo;
import AutoTest.Base.CaseResult;
import AutoTest.DataAction.TestExecAction;

public class HttpExecAction extends TestExecAction<HttpTestInfo>{

	@Override
	public CaseResult CaseAcceptAc(BaseFlow baseflow, HttpTestInfo ti,Object url) {
		System.out.println("请求数据:"+ti.getSendData());
		return (CaseResult) send(ti.getTestInfo().get("Send-Type"),
				ti.getHeaderInfo(),
				ti.getCookieInfo(),
				ti.getSendData(),
				(String)url);
	}

	@Override
	public void ResultGather(BaseFlow baseflow,HttpTestInfo ti,CaseResult tr) {
		System.out.println("返回数据:"+tr.toString());
		ti.setMsg(tr);
	}
	

	public CaseResult send(String type,Map<String,String> header,Map<String,String> cookie,Map<String,String> param,String url) {
	   Map<String,String> msg=new HashMap<String, String>();
	   msg.put("url", url);
	   CaseResult tr=new CaseResult();
		try {
		switch (type.toLowerCase()) {
		   case "post":tr.setReutrnMsg(HttpUtils.post(header, cookie, param, url));
		   case "get":tr.setReutrnMsg(HttpUtils.get(header, cookie, param, url));
		}
		try {
			String x=null;
			x=x.substring(2);
		}catch(Exception e) {
			tr.setExceptionMsg(e.getMessage());
		}
	    }catch(Exception e) {
	    	tr.setExceptionMsg(e.getMessage());
	    }
		tr.setExtraData(msg);
		return tr;
		
	}

	
}
