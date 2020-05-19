package AutoTest.flow;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutoTest.Base.BaseFlow;
import AutoTest.Base.TestInfo;
import AutoTest.DataAction.TestExecAction;

public class json extends BaseFlow{
	final String url="";
	
	@DataProvider(name="data")
	public Object[][] returnData(){
	   return returnExcelData();	
	}
	
	
	@Test(dataProvider ="data")
	public void test(TestInfo ti) throws Exception {
		
	}

}
