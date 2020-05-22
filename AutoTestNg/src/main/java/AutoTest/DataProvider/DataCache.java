package AutoTest.DataProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import AutoTest.Base.CaseInfo;
import AutoTest.DataAction.TestExecAction;
import AutoTest.SuitInfo.HttpSuit.HttpDataParser;

/**
 * 可作为数据缓存使用,来缓存暂时的的数据
 * @author jinxh29224
 *
 */
public class DataCache {
	
	//作用域为全局搜索的表达式
	public static Map<String,List<List<String>>> exceldata=new HashMap<String, List<List<String>>>();

	//作用域为当前数据解析表达式
	public static Map<String,String> casedata=new HashMap<String, String>();
	
	//当前数据采集器
	public static HttpDataParser DataParser=new HttpDataParser();


}
