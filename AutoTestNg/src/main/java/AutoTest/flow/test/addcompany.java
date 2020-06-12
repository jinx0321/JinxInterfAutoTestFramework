package AutoTest.flow.test;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutoTest.Base.BaseFlow;
import AutoTest.Base.CaseInfo;
import AutoTest.DataAction.TestExecAction;
import AutoTest.SuitInfo.HttpSuit.HttpExecAction;
import AutoTest.SuitInfo.HttpSuit.HttpTestInfo;

public class addcompany extends BaseFlow<HttpTestInfo,HttpExecAction>{
	final String url="http://www.wjljtest.5ibazhuayu.com.cn/open_api/v1/register_company";
	
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
