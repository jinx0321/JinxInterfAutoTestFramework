package AutoTest.Regex;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.Test;



import AutoTest.DataProvider.DataCache;
import AutoTest.Dict.Regex_Type;
import AutoTest.Utils.RegexUtils;

/**
 * 表达式模型解析工具
 * @author jinxh29224
 *
 */
public class RegexDataUtils {

	/**
	 * 表达式预更新
	 * @param regexupdatemodel
	 */
	private void PreRegexUpdate(RegexUpdateModel regexupdatemodel) {
		regexupdatemodel.setContent(RegexInter.PreRegexDeal(regexupdatemodel.getContent()));
	}
	
	/**
	 * 表达式后加载
	 * @param regexupdatemodel
	 */
	private void AfterRegexLoad(RegexUpdateModel regexupdatemodel) {
		regexupdatemodel.setContent(RegexInter.AfterRegexDeal(regexupdatemodel.getContent()));
	}
	
	/**
	 * 表达式预加载
	 */
	private void PreRegexLoad(RegexUpdateModel regexupdatemodel) {
		//如果表达式未被预加载
		if(regexupdatemodel.isIs_pre()==false) {
			//解析模型表达式
			regexupdatemodel.setContentexp(RegexInter.ReturnRegexs(regexupdatemodel.getContent()));
			
			//解析表达式类型
			regexupdatemodel.setRegexType(RegexInter.ReturnExpType(regexupdatemodel.getContent()));
			
			PreRegexUpdate(regexupdatemodel);
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
	private void RegexLoad(RegexUpdateModel regexupdatemodel) {
		List<Object> removelsit=new LinkedList<Object>();
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
        	    	  // regexupdatemodel.setContent(regexupdatemodel.getContent().replaceAll(RegexUtils.makeQueryStringAllRegExp(key), RegexUtils.makeQueryStringAllRegExp(aftercontent)));
        	    	   SetRegexContent(regexupdatemodel,key,aftercontent);
        	    	   removelsit.add(key);
        	       }
             }
             removelsit.forEach(e->{
            	 regexupdatemodel.getContentexp().remove(e); 
             });
             if(regexupdatemodel.getContentexp().size()==0) {
            	 regexupdatemodel.setIs_over(true);
             }
	}
	
	/**
	 * 解析表达式
	 * @param regexupdatemodel
	 */
	public void ParserRegex(RegexUpdateModel regexupdatemodel) {
		//表达式预装载
		PreRegexLoad(regexupdatemodel);
		//System.out.println(regexupdatemodel.getContentexp());
		while(true){
			if(regexupdatemodel.getContentexp().size()>0) {
				RegexLoad(regexupdatemodel);
			}else {
				break;
			}
		}
		//表达式后装载
		AfterRegexLoad(regexupdatemodel);
	}
	
	
	/**
	 * 设置文本值
	 * @param regexupdatemodel
	 */
	public void SetRegexContent(RegexUpdateModel regexupdatemodel,String var,String content){
		if(regexupdatemodel.getRegexType().equals(Regex_Type.Java_Regex)) {
		regexupdatemodel.setContent(regexupdatemodel.getContent().replaceAll(RegexUtils.makeQueryStringAllRegExp(var), RegexUtils.makeQueryStringAllRegExp(RegexInter.JavaExp.SpecialDealBefore(content))));
		}else {
			regexupdatemodel.setContent(regexupdatemodel.getContent().replaceAll(RegexUtils.makeQueryStringAllRegExp(var), RegexUtils.makeQueryStringAllRegExp(content)));
				
		}
		}
		
	


	
	@Test
	public void test() {
		String content="wqeqwe{{{{fromSheet(name=\"案例数据\",value=\"D,2\")}xcsds";
		String key="{{{{fromSheet(name=\"案例数据\",value=\"D,2\")}";
		System.out.println(content.replaceAll(RegexUtils.makeQueryStringAllRegExp(key), "123"));
		
	}


}
