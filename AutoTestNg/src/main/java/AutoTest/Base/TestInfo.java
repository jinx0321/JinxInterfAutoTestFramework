package AutoTest.Base;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestInfo {
	  
    public TestInfo() {
    		
    	}
        //����id
    	String id;
    	//������
    	String testname;
    	//������Ϣ
        Map<String,String> TestInfo=new LinkedHashMap<String,String>();
        //ͷ��Ϣ
        Map<String,String> HeaderInfo=new LinkedHashMap<String, String>();
        //����׼��
        List<Map<String,String>> PreInfo=new LinkedList<Map<String,String>>();
        //��������
        Map<String,String> SendData=new LinkedHashMap<String, String>();
      
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTestname() {
			return testname;
		}
		public void setTestname(String testname) {
			this.testname = testname;
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
		@Override
		public String toString() {
			return "TestInfo [id=" + id + ", TestInfo=" + TestInfo + ", HeaderInfo=" + HeaderInfo + ", PreInfo="
					+ PreInfo + ", SendData=" + SendData + "]";
		}
		
        
    
}
