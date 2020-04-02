package AutoTest.DataProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.junit.Test;

import com.sun.jersey.api.ParamException.HeaderParamException;

import AutoTest.Dict.Content_Type;


public class DataParser {
	
	public List<TestInfo> parser(String filedir) throws Exception {
		Map<String,List<List<String>>> data=new HashMap<String, List<List<String>>>();
		
		String dataformat="";

		try {
			data=ExcelUtils.readExcel(filedir);
		}
		catch(Exception e) {
			System.out.println("excel��������");
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
			cookie=CookieParser(data);
		}catch(Exception e) {
			System.out.println("cookie��������");
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
		//�������ݽ���
		Map<String,String> senddata=new HashMap<String, String>();
		try {
		if(data.containsKey("json")) {
			senddata=DataFormatParser("json",data);
			dataformat=Content_Type.json;
		}else if(data.containsKey("form")) {
			senddata=DataFormatParser("form",data);
			dataformat=Content_Type.form;
		}
		}catch(Exception e) {
			System.out.println("�������ݸ�ʽ��������");
			e.printStackTrace();
		}
		
		if(caseinfos.size()==0) {
			throw new Exception("����������Ϊ0,�����쳣");
		}
		
		List<TestInfo> til=new LinkedList<TestInfo>();
		//ʵ����������Ϣ
	    for(int i=0;i<caseinfos.size();i++) {
	         TestInfo ti=new TestInfo();
	         ti.setId(caseinfos.get(i).get("TestId"));
	         ti.setFormat(dataformat);
	         ti.setHeaderInfo(header);
	         ti.setCookieInfo(cookie);
	         ti.setPreInfo(PreDataLoad(datapre,caseinfos.get(i)));
	         ti.setSendData(DataLoad(senddata,caseinfos.get(i)));
	         til.add(ti);
	    }
	    return til;
	}
	
	/**
	 * ������Ϣ����
	 * @param data
	 * @return
	 */
	private Map<String,String> TestinfoParser(Map<String,List<List<String>>> data){
		
		return null;
	}
	
	/**
	 * httpͷ����
	 * @return
	 */
	private Map<String,String> HeaderParser(Map<String,List<List<String>>> data){
		Map<String,String> header=new HashMap<String, String>();

		List<List<String>> headerlist=data.get("header");
		for(int i=1;i<headerlist.size();i++) {
			header.put(headerlist.get(i).get(0),RegexParser(headerlist.get(i).get(1)));
		}
		
		return header;
	}
	/**
	 * httpcookie����
	 * @return
	 */
	private Map<String,String> CookieParser(Map<String,List<List<String>>> data){
		Map<String,String> cookie=new HashMap<String, String>();

		List<List<String>> cookielist=data.get("cookie");
		for(int i=1;i<cookielist.size();i++) {
			cookie.put(cookielist.get(i).get(0),RegexParser(cookielist.get(i).get(1)));
		}
		
		return cookie;
	}
	
	/**
	 * ����׼������
	 * @return
	 */
	private List<Map<String,String>> DataPreParser(Map<String,List<List<String>>> data){
		
		List<Map<String,String>> datapre=new LinkedList<Map<String,String>>();
		
		List<List<String>> prelist=data.get("����׼��");
		
		for(int i=1;i<prelist.size();i++) {
			Map<String,String> point=new LinkedHashMap<String, String>();
			
			//�Զ���ȫ
			if(prelist.get(0).size()!=prelist.get(i).size()) {
				for(int j=prelist.get(i).size();j<prelist.get(0).size();j++) {
					prelist.get(i).add("");
				}
			}
			point.put(prelist.get(0).get(0), prelist.get(i).get(0));
			point.put(prelist.get(0).get(1), prelist.get(i).get(1));
			point.put(prelist.get(0).get(2), prelist.get(i).get(2));
			point.put(prelist.get(0).get(3), prelist.get(i).get(3));
			datapre.add(point);	
		}
		
		return datapre;
	}	
	
	/**
	 * ������Ϣ����
	 */
	private List<Map<String,String>> CaseParser(Map<String,List<List<String>>> data) {
		
		List<Map<String,String>> cases=new LinkedList<Map<String,String>>();
		
		List<List<String>> caselist=data.get("��������");
		
		
		for(int i=1;i<caselist.size();i++) {
			
			Map<String,String> cm=new LinkedHashMap<String, String>();
			List<String> title=caselist.get(0);
			List<String> row=caselist.get(i);
			//��������
			cm.put(title.get(0), row.get(0));
			cm.put(title.get(1), row.get(1));
			//����
			for(int j=2;j<title.size();j++) {
				String t=title.get(j);
				String r="";
				try {r=row.get(j).trim();}catch(Exception e) {r="";}/*�Զ���ȫ*/
				cm.put(t, r);
			}
			cases.add(cm);
		}
		return cases;
		
	}
	
	/**
	 * �������ݸ�ʽ����
	 * @param type
	 * @param data
	 * @return
	 */
	private Map<String,String> DataFormatParser(String type,Map<String,List<List<String>>> data){
		Map<String,String> senddata=new HashMap<String, String>();
		List<List<String>> datalist=data.get(type);
        List<String> keylist=datalist.get(0);
	    
	
	    for(int i=0;i<keylist.size();i++) {
	    	if(datalist.size()==1) {
	    		senddata.put(keylist.get(i), "");
	    	}else {
	    		List<String> valuelist=datalist.get(1);
	    		senddata.put(keylist.get(i), valuelist.get(i));
	    	}
	    }
		return senddata;
	}
	
	/*����ת��*/
	private Map<String,String> DataLoad(Map<String,String> formatdata,Map<String,String> caseinfo){
		String regex="\\$\\{[^\\}]*\\}";
		Map<String,String> caseparam=new HashMap<String, String>();
		Map<String,String> afterdata=new LinkedHashMap<String, String>();
		for(Entry<String, String> e:caseinfo.entrySet()) {
			if(!e.getKey().trim().toLowerCase().equals("testid")||!e.getKey().trim().toLowerCase().equals("testname")) {
				caseparam.put(e.getKey(), e.getValue());
			}
		}
		for(Entry<String, String> e:formatdata.entrySet()) {
			String key=e.getKey();
			String value=e.getValue();
		    if(Pattern.matches(regex, value)) {
		    	value=value.substring(2, value.length()-1).trim();

		    	if(caseparam.containsKey(value)) {
		    		value=caseparam.get(value);
		    		afterdata.put(key, value);
		    	}else {
		    		afterdata.put(key, "");
		    	}
		    	
		    }else {
		    	afterdata.put(key, value);
		    }
		    
		}
		
		return afterdata;
	}
	
	/**
	 * ����׼��װ��
	 * @param formatdata
	 * @param caseinfo
	 * @return
	 */
	private  List<Map<String,String>> PreDataLoad(List<Map<String,String>> datepre,Map<String,String> caseinfo){
	    List<Map<String,String>> dataepreafter=new LinkedList<Map<String,String>>();
	    
	    for(int i=0;i<datepre.size();i++) {
	    	if(datepre.get(i).get("TestId").equals(caseinfo.get("TestId"))) {
	    		dataepreafter.add(datepre.get(i));
	    	}
	    	
	    }
		return dataepreafter;
		
	}
	/**
	 * ����ʽ������
	 */
	private String RegexParser(String regex) {
		
		
		return regex;
	}
	
	@Test
	public void test() throws Exception {
		parser("D:\\gitproject\\JinxInterAutoTestFramework\\AutoTestNg\\src\\main\\java\\AutoTest\\flow\\test_al.xlsx");
	
	
	}

}