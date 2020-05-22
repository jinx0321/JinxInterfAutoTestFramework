package AutoTest.SuitInfo.HttpSuit;

import java.util.HashMap;
import java.util.Map;

import AutoTest.Base.BaseFlow;
import AutoTest.Base.CaseInfo;
import AutoTest.Base.CaseResult;
import AutoTest.DataAction.TestExecAction;

public class HttpExecAction extends TestExecAction<HttpTestInfo>{

	@Override
	public CaseResult CaseAcceptAc(BaseFlow baseflow, HttpTestInfo ti,Object url) {
		System.out.println("«Î«Û ˝æ›:"+ti.getSendData());
		return (CaseResult) send(ti.getTestInfo().get("Send-Type"),
				ti.getHeaderInfo(),
				ti.getCookieInfo(),
				ti.getSendData(),
				(String)url);
	}

	@Override
	public void ResultGather(BaseFlow baseflow,HttpTestInfo ti,CaseResult tr) {
		System.out.println("∑µªÿ ˝æ›:"+tr.toString());
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
	    }catch(Exception e) {
	    	tr.setExceptionMsg(e.getMessage());
	    	
	    }
		tr.setExceptionMsg("123≤‚ ‘“Ïsdfqwfasfasfaf≥£≤‚ ‘“Ï≥£≤‚ ‘“Ï≥£≤‚ ‘“Ï≥£≤‚ ‘“Ï≥£≤‚ ‘“Ï≥£≤‚ ‘“Ï≥£");
		tr.setExtraData(msg);
		return tr;
		
	}

	
}
