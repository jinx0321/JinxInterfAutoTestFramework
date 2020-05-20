package AutoTest.SuitInfo.HttpSuit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import AutoTest.DataProvider.DataCache;
import AutoTest.DataProvider.DataParser;
import AutoTest.DataProvider.ExcelUtils;
public class HttpDataParser extends DataParser<HttpTestInfo>{

	@Override
	public List<HttpTestInfo> parser(String filedir) {
		Map<String,List<List<String>>> data=new HashMap<String, List<List<String>>>();

		try {
			data=ExcelUtils.readExcel(filedir);
		}
		catch(Exception e) {
			System.out.println("excel解析错误");
			e.printStackTrace();
		}
		DataCache.exceldata=data;
		//案例信息解析
	     Map<String,String> testinfo=new HashMap<String, String>();
		try {
			testinfo=TestinfoParser(data);
		}catch(Exception e) {
			System.out.println("案例信息解析错误");
			e.printStackTrace();
		}
		//头信息解析
		Map<String,String> header=new HashMap<String, String>();
		try {
			header=HeaderParser(data);
		}catch(Exception e) {
			System.out.println("header解析错误");
			e.printStackTrace();
		}
		//缓存解析
		Map<String,String> cookie=new HashMap<String, String>();
		try {
			cookie=K_V_Parser(data);
		}catch(Exception e) {
			System.out.println("cookie解析错误");
			e.printStackTrace();
		}
		//发送数据解析
		Map<String,String> senddatas=new HashMap<String, String>();
		try {
			senddatas=SendDataParser(data);
		}catch(Exception e) {
			System.out.println("发送数据解析错误");
			e.printStackTrace();
		}
		
		//数据准备解析
		List<Map<String,String>> datapre=new LinkedList<Map<String,String>>();
		try {
			datapre=DataPreParser(data);
		}catch(Exception e) {
			System.out.println("数据准备解析错误");
			e.printStackTrace();
		}
		//案例数据解析
		List<Map<String,String>> caseinfos=new LinkedList<Map<String,String>>();
		try {
			caseinfos=CaseParser(data);
		}catch(Exception e) {
			System.out.println("案例数据解析错误");
			e.printStackTrace();
		}
		
		
		List<HttpTestInfo> til=new LinkedList<HttpTestInfo>();
		//实例化案例信息
	    for(int i=0;i<caseinfos.size();i++) {
	    	HttpTestInfo ti=new HttpTestInfo();
	         ti.setId(caseinfos.get(i).get("TestId"));
	         ti.setTestname(caseinfos.get(i).get("TestName"));
	         ti.setTestInfo(testinfo);
	         ti.setHeaderInfo(GeneralDataLoad(header,caseinfos.get(i)));
	         ti.setCookieInfo(GeneralDataLoad(cookie,caseinfos.get(i)));
	         ti.setPreInfo(PreDataLoad(datapre,caseinfos.get(i)));
	         ti.setSendData(GeneralDataLoad(senddatas,caseinfos.get(i)));
	         til.add(ti);
	    }
	    return til;
	}

}
