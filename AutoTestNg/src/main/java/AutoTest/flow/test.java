package AutoTest.flow;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutoTest.Base.BaseFlow;

public class test extends BaseFlow{
	final String url="";
	
	@DataProvider(name="data")
	public Object[] returnData(){
	   return returnExcelData();	
	}
	
	
	@Test(dataProvider ="data")
	public void test(Map<String,String> testdata) {
		testdata.forEach((k,v)->{
			System.out.println(k+":"+v);
		});
		
		
	}

}
