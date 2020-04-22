package AutoTest.flow;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutoTest.Base.BaseFlow;
import AutoTest.DataAction.TestExecAction;
import AutoTest.DataProvider.TestInfo;

public class form extends BaseFlow{
	final String url="";
	
	@DataProvider(name="data")
	public Object[] returnData(){
	   return returnExcelData();	
	}
	
	
	@Test(dataProvider ="data")
	public void test(TestInfo ti) throws Exception {
	
		TestExecAction.class.newInstance().CaseAcceptAc(this,ti);
		
		
	}

}
