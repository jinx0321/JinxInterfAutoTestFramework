package AutoTest.DataProvider;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestInfo {
	  
    public TestInfo() {
    		
    	}
        //案例id
    	String id;
    	//数据格式
    	String format;
    	//案例信息
        Map<String,String> TestInfo=new LinkedHashMap<String,String>();
        //头信息
        Map<String,String> HeaderInfo=new LinkedHashMap<String, String>();
        //cookie信息
        Map<String,String> CookieInfo=new LinkedHashMap<String, String>();
        //数据准备
        List<Map<String,String>> PreInfo=new LinkedList<Map<String,String>>();
        //发送数据
        Map<String,String> SendData=new LinkedHashMap<String, String>();
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		public String getFormat() {
			return format;
		}
		public void setFormat(String format) {
			this.format = format;
		}
		public Map<String, String> getTestInfo() {
			return TestInfo;
		}
		public void setTestInfo(Map<String, String> testInfo) {
			TestInfo = testInfo;
		}
		public Map<String, String> getHeaderInfo() {
			return HeaderInfo;
		}
		public void setHeaderInfo(Map<String, String> headerInfo) {
			HeaderInfo = headerInfo;
		}
		public Map<String, String> getCookieInfo() {
			return CookieInfo;
		}
		public void setCookieInfo(Map<String, String> cookieInfo) {
			CookieInfo = cookieInfo;
		}
		
		public List<Map<String, String>> getPreInfo() {
			return PreInfo;
		}
		public void setPreInfo(List<Map<String, String>> preInfo) {
			PreInfo = preInfo;
		}
		public Map<String, String> getSendData() {
			return SendData;
		}
		public void setSendData(Map<String, String> sendData) {
			SendData = sendData;
		}
		
        
    
}
