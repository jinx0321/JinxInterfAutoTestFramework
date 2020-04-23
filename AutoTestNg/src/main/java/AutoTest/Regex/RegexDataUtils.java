package AutoTest.Regex;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.Test;

import AutoTest.DataProvider.DataCache;
import AutoTest.Utils.RegexUtils;

/**
 * 表达式模型解析工具
 * @author jinxh29224
 *
 */
public class RegexDataUtils {

	/**
	 * 表达式预加载
	 */
	public void PreRegexLoad(RegexUpdateModel regexupdatemodel) {

		//如果表达式未被预加载
		if(regexupdatemodel.isIs_pre()==false) {
			//解析模型表达式
			regexupdatemodel.setContentexp(RegexInter.ReturnRegexs(regexupdatemodel.getContent()));
			
			//设置表达式预加载标志为true
		    regexupdatemodel.setIs_pre(true);		
		    
		    //对已经解析的表达式模型进行预加载
		    regexupdatemodel.getContentexp().forEach((k,v)->{
		    	PreRegexLoad((RegexUpdateModel)v);
		    });
		}
	}
	
	/**
	 * 表达式数据装载
	 * @param regexupdatemodel
	 */
	public synchronized void  RegexLoad(RegexUpdateModel regexupdatemodel) {
			 //遍历当前节点的表达式
             for(Entry<String, RegexUpdateModel> e:regexupdatemodel.getContentexp().entrySet()) {
        	       String key=(String) e.getKey();
        	       RegexUpdateModel value=(RegexUpdateModel) e.getValue();
        	       //如果最底下节点无子表达式,则解析表达式
        	       if(value.getContentexp().size()!=0) {
        	    	   RegexLoad(value);
        	       }else {
        	    	   //对最终表达式进行解析
        	    	   String aftercontent=RegexInter.ExpFilter(value.getContent(), DataCache.casedata);
        	    	   //更新当前节点的content和contentexp
        	    	   regexupdatemodel.setContent(regexupdatemodel.getContent().replaceAll(RegexUtils.makeQueryStringAllRegExp(key), aftercontent));
        	    	   regexupdatemodel.getContentexp().remove(key); 
        	       }
             }
		  
		
	}
	
	/**
	 * 更新表达式模型
	 * 对map进行深度遍历,将文本中的表达式进行变量替换
	 * 
	 */
	public void UpdateRegexModel(RegexUpdateModel regexdatamodel) {
		
	}
	
	/**
	 * 表达式是否解析完毕
	 * @return
	 */
	public boolean isRegexParserComp(RegexUpdateModel regexdatamodel) {
		return 	regexdatamodel.getContentexp().size()==0;
	}
	/**
	 * 更新当前表达式
	 */
	public void UpdateRegex(RegexUpdateModel regexdatamodel){
		if(regexdatamodel.isIs_over()) {
			
		}
		
	}
	
	@Test
	public void test() {
		String content="wqeqwe{{{{fromSheet(name=\"案例数据\",value=\"D,2\")}xcsds";
		String key="{{{{fromSheet(name=\"案例数据\",value=\"D,2\")}";
		System.out.println(content.replaceAll(RegexUtils.makeQueryStringAllRegExp(key), "123"));
		
	}


}
