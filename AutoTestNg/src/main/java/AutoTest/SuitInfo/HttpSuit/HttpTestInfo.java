package AutoTest.SuitInfo.HttpSuit;

import java.util.LinkedHashMap;
import java.util.Map;

import AutoTest.Base.CaseInfo;

public class HttpTestInfo extends CaseInfo{
	 //cookie–≈œ¢
    Map<String,String> CookieInfo=new LinkedHashMap<String, String>();
    
    public Map<String, String> getCookieInfo() {
		return CookieInfo;
	}
	public void setCookieInfo(Map<String, String> cookieInfo) {
		CookieInfo = cookieInfo;
	}
	
	
}
