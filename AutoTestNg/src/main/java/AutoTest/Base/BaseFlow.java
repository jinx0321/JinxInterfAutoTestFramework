package AutoTest.Base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import AutoTest.DataProvider.DataParser;
import AutoTest.DataProvider.TestInfo;
import AutoTest.Dict.ActionPrepare;

public class BaseFlow {
	final private String _tail = "_al";
	final private String _filetype = ".xlsx";

	String current_TestId = "";
	// ��������before��afterʶ����
	int current_testlist_no = -1;

	List<TestInfo> TestInfoList = new LinkedList<TestInfo>();

	{
		DataParser dp = new DataParser();
		try {
			String filedir = this.getClass().getResource("").getPath() + this.getClass().getSimpleName() + _tail+ _filetype;
			System.out.println("��ȡ�����ļ�:" + filedir);
			TestInfoList = dp.parser(filedir);
			if (TestInfoList.size() > 0) {

				// ��ʼ����һ��
				current_TestId = TestInfoList.get(0).getId();
				current_testlist_no = 0;
			}
			
			System.out.println("-------------------------------------------------\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@BeforeMethod
	public void before() {
	   //��ȡ��ǰ�����Ĳ���׼������
       List<Map<String,String>> datepre=TestInfoList.get(current_testlist_no).getPreInfo();
       for(int i=0;i<datepre.size();i++) {
    	   if(datepre.get(i).get("Point").equals(ActionPrepare.before)) {
    		   System.out.println("ִ��beforepoint:"+datepre.get(i).get("DbInfo"));
    	   }
       }
	}

	@AfterMethod
	public void after() {
		 //��ȡ��ǰ�����Ĳ���׼������
	       List<Map<String,String>> datepre=TestInfoList.get(current_testlist_no).getPreInfo();
	       for(int i=0;i<datepre.size();i++) {
	    	   if(datepre.get(i).get("Point").equals(ActionPrepare.after)) {
	    		   System.out.println("ִ��afterpoint:"+datepre.get(i).get("DbInfo"));
	    	   }
	       }
	       
	   	System.out.println("-------------------------------------------------\n\n");
	   	
      if((this.current_testlist_no)==this.TestInfoList.size()) {
			
		}else if((this.current_testlist_no+1)<this.TestInfoList.size()) {
			this.setCurrent_TestId(this.TestInfoList.get(this.current_testlist_no+1).getId());
			this.setCurrent_testlist_no(this.current_testlist_no+1);
			
		}
		
	}

	public Object[] returnExcelData() {
		return TestInfoList.toArray();
	}

	public String getCurrent_TestId() {
		return current_TestId;
	}

	public void setCurrent_TestId(String current_TestId) {
		this.current_TestId = current_TestId;
	}

	public int getCurrent_testlist_no() {
		return current_testlist_no;
	}

	public void setCurrent_testlist_no(int current_testlist_no) {
		this.current_testlist_no = current_testlist_no;
	}

	public List<TestInfo> getTestInfoList() {
		return TestInfoList;
	}

	public void setTestInfoList(List<TestInfo> TestInfoList) {
		this.TestInfoList = TestInfoList;
	}

}