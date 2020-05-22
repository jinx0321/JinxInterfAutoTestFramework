package AutoTest.DataProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.Test;

import AutoTest.Base.CaseInfo;
import AutoTest.Dict.Content_Type;
import AutoTest.Regex.RegexInter;
import AutoTest.Regex.RegexUpdateModel;
import AutoTest.Regex.RegexDataUtils;


public abstract class DataParser<T> {
	
	public abstract List<T> parser(String filedir);
	
	/**
	 * 案例信息解析
	 * @param data
	 * @return
	 */
	protected Map<String,String> TestinfoParser(Map<String,List<List<String>>> data){
		Map<String,String> testinfo=new HashMap<String, String>();

		List<List<String>> headerlist=data.get("案例信息");
		for(int i=1;i<headerlist.size();i++) {
			testinfo.put(headerlist.get(i).get(0),RegexParser(headerlist.get(i).get(1)));
		}
		
		return testinfo;

	}
	
	/**
	 * http头解析
	 * @return
	 */
	protected Map<String,String> HeaderParser(Map<String,List<List<String>>> data){
		Map<String,String> header=new HashMap<String, String>();

		List<List<String>> headerlist=data.get("header");
		for(int i=1;i<headerlist.size();i++) {
			header.put(headerlist.get(i).get(0),RegexParser(headerlist.get(i).get(1)));
		}
		
		return header;
	}
	/**
	 * k-v关系型数据解析
	 * @return
	 */
	protected Map<String,String> K_V_Parser(Map<String,List<List<String>>> data){
		Map<String,String> kvdata=new HashMap<String, String>();

		List<List<String>> kvdatalist=data.get("cookie");
		for(int i=1;i<kvdatalist.size();i++) {
			kvdata.put(kvdatalist.get(i).get(0),RegexParser(kvdatalist.get(i).get(1)));
		}
		
		return kvdata;
	}
	
	/**
	 * 发送数据解析
	 * @return
	 */
	protected Map<String,String> SendDataParser(Map<String,List<List<String>>> data){
		Map<String,String> senddata=new HashMap<String, String>();

		List<List<String>> senddatalist=data.get("senddata");
		for(int i=0;i<senddatalist.get(0).size();i++) {
			senddata.put(senddatalist.get(0).get(i), senddatalist.get(1).get(i));
		}
		
		return senddata;
	}
	

	/**
	 * 数据准备解析
	 * @return
	 */
	protected List<Map<String,String>> DataPreParser(Map<String,List<List<String>>> data){
		
		List<Map<String,String>> datapre=new LinkedList<Map<String,String>>();
		
		List<List<String>> prelist=data.get("数据准备");
		
		for(int i=1;i<prelist.size();i++) {
			Map<String,String> point=new LinkedHashMap<String, String>();
			
			//自动补全
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
	 * 案例信息解析
	 */
	protected List<Map<String,String>> CaseParser(Map<String,List<List<String>>> data) {
		
		List<Map<String,String>> cases=new LinkedList<Map<String,String>>();
		
		List<List<String>> caselist=data.get("案例数据");
		
	
		for(int i=1;i<caselist.size();i++) {
			
			Map<String,String> cm=new LinkedHashMap<String, String>();
			List<String> title=caselist.get(0);
			List<String> row=caselist.get(i);
			//基础数据

			cm.put(title.get(0), row.get(0));
			cm.put(title.get(1), row.get(1));
			//参数
			for(int j=2;j<title.size();j++) {
				String t=title.get(j);
				String r="";
				try {r=row.get(j).trim();}catch(Exception e) {r="";}/*自动补全*/
				cm.put(t, r);
			}
			cases.add(cm);
		}
		return cases;
		
	}
	
	/**
	 * 通用数据装载,包括表达式转换
	 * @param data
	 * @param caseinfo
	 * @return
	 */
	protected Map<String,String> GeneralDataLoad(Map<String,String> data,Map<String,String> caseinfo){
		
		Map<String,String> afterdata=new LinkedHashMap<String, String>();
		Map<String,String> caseparam=new HashMap<String, String>();
		for(Entry<String, String> e:caseinfo.entrySet()) {
			if(!e.getKey().trim().toLowerCase().equals("testid")||!e.getKey().trim().toLowerCase().equals("testname")) {
				caseparam.put(e.getKey(), e.getValue());
			}
		}
		DataCache.casedata=caseparam;
		
		for(Entry<String, String> e:data.entrySet()) {
			String key=e.getKey();
			String value=e.getValue();
			//把字符串抽成模型
			RegexUpdateModel RegexUpdateModel=new RegexUpdateModel(value);
			//模型解析
			RegexInter.RegexDataUtils.ParserRegex(RegexUpdateModel);
			//赋值
			afterdata.put(key,RegexUpdateModel.getContent());
		}
		
		return afterdata;
		
	}
	
	
	/**
	 * json中的存在的表达式解析
	 * 返回一个map{
	 *    key:var
	 *    value:var
	 * }
	 * @return
	 */
	protected Map<String,String> JsonDataParser(String content){
		return RegexInter.ReturnVarRegexs(content).stream()
				.collect(Collectors.toMap(x->x.toString(), x->x.toString()));
	}
	
	/**
	 * 数据准备装载
	 * @param formatdata
	 * @param caseinfo
	 * @return
	 */
	protected  List<Map<String,String>> PreDataLoad(List<Map<String,String>> datepre,Map<String,String> caseinfo){
	    List<Map<String,String>> dataepreafter=new LinkedList<Map<String,String>>();
	    for(int i=0;i<datepre.size();i++) {
	    	if(datepre.get(i).get("TestId").equals(caseinfo.get("TestId"))) {
	    		dataepreafter.add(datepre.get(i));
	    	}
	    }
		return dataepreafter;
	}
	/**
	 * 表达式解析器
	 */
	protected String RegexParser(String regex) {
		
		
		return regex;
	}
	
	

	@Test 
	public void test() throws Exception {
		parser("D:\\gitproject\\JinxInterAutoTestFramework\\AutoTestNg\\src\\main\\java\\AutoTest\\flow\\form_al.xlsx");
	
	
	}

}
