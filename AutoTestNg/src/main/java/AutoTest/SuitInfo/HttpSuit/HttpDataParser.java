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
			System.out.println("excel��������");
			e.printStackTrace();
		}
		DataCache.exceldata=data;
		//������Ϣ����
	     Map<String,String> testinfo=new HashMap<String, String>();
		try {
			testinfo=TestinfoParser(data);
		}catch(Exception e) {
			System.out.println("������Ϣ��������");
			e.printStackTrace();
		}
		//ͷ��Ϣ����
		Map<String,String> header=new HashMap<String, String>();
		try {
			header=HeaderParser(data);
		}catch(Exception e) {
			System.out.println("header��������");
			e.printStackTrace();
		}
		//�������
		Map<String,String> cookie=new HashMap<String, String>();
		try {
			cookie=K_V_Parser(data);
		}catch(Exception e) {
			System.out.println("cookie��������");
			e.printStackTrace();
		}
		//�������ݽ���
		Map<String,String> senddatas=new HashMap<String, String>();
		try {
			senddatas=SendDataParser(data);
		}catch(Exception e) {
			System.out.println("�������ݽ�������");
			e.printStackTrace();
		}
		
		//����׼������
		List<Map<String,String>> datapre=new LinkedList<Map<String,String>>();
		try {
			datapre=DataPreParser(data);
		}catch(Exception e) {
			System.out.println("����׼����������");
			e.printStackTrace();
		}
		//�������ݽ���
		List<Map<String,String>> caseinfos=new LinkedList<Map<String,String>>();
		try {
			caseinfos=CaseParser(data);
		}catch(Exception e) {
			System.out.println("�������ݽ�������");
			e.printStackTrace();
		}
		
		
		List<HttpTestInfo> til=new LinkedList<HttpTestInfo>();
		//ʵ����������Ϣ
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
