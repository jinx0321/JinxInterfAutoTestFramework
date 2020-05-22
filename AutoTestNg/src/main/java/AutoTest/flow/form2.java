package AutoTest.flow;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutoTest.Base.BaseFlow;
import AutoTest.Base.CaseInfo;
import AutoTest.DataAction.TestExecAction;
import AutoTest.SuitInfo.HttpSuit.HttpExecAction;
import AutoTest.SuitInfo.HttpSuit.HttpTestInfo;

public class form2 extends BaseFlow<HttpTestInfo,HttpExecAction>{
	final String url="http://localhost:9090/json";
	
	@DataProvider(name="data")
	public Object[][] returnData(){
	   return returnExcelData();	
	}
	
	@Test(dataProvider ="data")
	public void test(HttpTestInfo ti) throws Exception {
		HttpExecAction httpexecaction=new HttpExecAction();
		CaseExec(ti,httpexecaction,url);
		CaseResultDeal(ti,httpexecaction);
		
	}

}
