package AutoTest.SuitInfo.HttpSuit;

import java.util.Map;

import AutoTest.Base.BaseFlow;
import AutoTest.Base.TestInfo;
import AutoTest.DataAction.TestExecAction;

public class HttpExecAction extends TestExecAction<HttpTestInfo>{

	@Override
	public Object CaseAcceptAc(BaseFlow baseflow, HttpTestInfo ti,Object url) {
		
		return send(ti.getTestInfo().get("Send-Type"),
				ti.getHeaderInfo(),
				ti.getCookieInfo(),
				ti.getSendData(),
				(String)url);
	}

	public Object send(String type,Map<String,String> header,Map<String,String> cookie,Map<String,String> param,String url) {
	    try {
		switch (type.toLowerCase()) {
		   case "post":return HttpUtils.post(header, cookie, param, url);
		   case "get":return HttpUtils.get(header, cookie, param, url);
		}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
		return "";
		
	}
	
}
