package AutoTest.Base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import AutoTest.DataAction.TestExecAction;
import AutoTest.DataProvider.DataCache;
import AutoTest.DataProvider.DataParser;
import AutoTest.Dict.ActionPrepare;
import AutoTest.SuitInfo.HttpSuit.HttpDataParser;

public abstract class BaseFlow<T extends TestInfo,K extends TestExecAction<T>>{
	final private String _tail = "_al";
	final private String _filetype = ".xlsx";
    private K k;
	
	String current_TestId = "";
	// ��������before��afterʶ����
	int current_testlist_no = -1;

	List<T> TestInfoList = new LinkedList<T>();
	

	/**
	 * ��ʼ����ʱ�����ݳ�ʼ��������
	 */
	{
		DataParser dp = DataCache.DataParser;
		try {
			String filedir = this.getClass().getResource("").getPath() + this.getClass().getSimpleName() + _tail+ _filetype;
			TestInfoList = dp.parser(filedir);
			if (TestInfoList.size() > 0) {
				// ��ʼ����һ��
				current_TestId = TestInfoList.get(0).getId();
				current_testlist_no = 0;
				
			System.out.println(this.getClass().getName()+"���ݳ�ʼ���ɹ�");
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(this.getClass().getName()+"���ݳ�ʼ��ʧ��");
		}

	}
	
	/**
	 * 
	 */
	@BeforeClass
	public void beforeclass() {
		System.out.println("");
		System.out.println("");
		System.out.println("<------------------"+"ִ�в�����:"+this.getClass().getName()+" Start--------------------------->");
		System.out.println("");
		System.out.println("");
	}
	
	
	@AfterClass
	public void alterclass() {
		System.out.println("");
		System.out.println("");
		System.out.println("<------------------"+"������:"+this.getClass().getName()+" END--------------------------->");
		System.out.println("");
		System.out.println("");
	}

	@BeforeMethod
	public void beforemethod() {
		
		
	   //��ȡ��ǰ�����Ĳ���׼������
       List<Map<String,String>> datepre=TestInfoList.get(current_testlist_no).getPreInfo();
       for(int i=0;i<datepre.size();i++) {
    	   if(datepre.get(i).get("Point").equals(ActionPrepare.before)) {
    		   System.out.println("ִ��beforepoint:"+datepre.get(i).get("DbInfo"));
    	   }
       }
	}

	@AfterMethod
	public void aftermethod() {
		 //��ȡ��ǰ�����Ĳ���׼������
	       List<Map<String,String>> datepre=TestInfoList.get(current_testlist_no).getPreInfo();
	       for(int i=0;i<datepre.size();i++) {
	    	   if(datepre.get(i).get("Point").equals(ActionPrepare.after)) {
	    		   System.out.println("ִ��afterpoint:"+datepre.get(i).get("DbInfo"));
	    	   }
	       }
	       
	   	System.out.println("<<<<<<<<<<<<<\n\n");
	   	
      if((this.current_testlist_no)==this.TestInfoList.size()) {
			
		}else if((this.current_testlist_no+1)<this.TestInfoList.size()) {
			this.setCurrent_TestId(this.TestInfoList.get(this.current_testlist_no+1).getId());
			this.setCurrent_testlist_no(this.current_testlist_no+1);
		}
	}

	public Object[][] returnExcelData() {
		Object[][] object=new Object[TestInfoList.size()][1];
		for(int i=0;i<TestInfoList.size();i++) {
			object[i][0]=TestInfoList.get(i);
		}
		return object;
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

	public List<T> getTestInfoList() {
		return TestInfoList;
	}

	public void setTestInfoList(List<T> TestInfoList) {
		this.TestInfoList = TestInfoList;
	}
	
	public Object CaseExec(T ti,K exec,Object o) {
	
	   return exec.CaseAcceptAc(this, ti,o);
		

	}

}
