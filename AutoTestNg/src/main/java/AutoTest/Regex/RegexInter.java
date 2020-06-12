package AutoTest.Regex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import AutoTest.Base.CaseInfo;
import AutoTest.DataProvider.DataCache;
import AutoTest.Dict.Regex_Type;

/**
 * 表达式接口,提供表达式工具
 * @author jinxh29224
 *
 */
public class RegexInter{
	
	public static SheetExp SheetExp=new SheetExp();
	public static JavaExp JavaExp=new JavaExp();
	public static VarReplaceExp VarReplaceExp=new VarReplaceExp();
	public static RegexDataUtils RegexDataUtils=new RegexDataUtils();
	
	/**
	 * 获取表达式类型
	 * @param var
	 * @return
	 */
	public static String ReturnExpType(String var) {
	    if(VarReplaceExp.is_VarReplace_Exp(var)) {
			 return Regex_Type.Var_Regex;
		 }
	    else if(SheetExp.is_Sheet_Exp(var)) {
	    	 return Regex_Type.Sheet_Regex;
	    }
	    else if(JavaExp.is_JavaExp(var)) {
	    	 return Regex_Type.Java_Regex;
	    }
	         return Regex_Type.Unknow_Regex;
	}
	/**
	 * 获取表达式值,sheet和var
	 * @param var
	 * @param currentline
	 * @return
	 */
	public static String ExpFilter(String var,Map<String,String> currentline){
		    //如果是变量替换表达式
	       if(VarReplaceExp.is_VarReplace_Exp(var)) {
	    	   var=VarReplaceExp.return_Result(var, currentline);
	    	   return ExpFilter(var,currentline);  
	       }
	       //如果是sheet表达式
	       else if(SheetExp.is_Sheet_Exp(var)) {
	    	   var=SheetExp.return_Result(var, DataCache.exceldata);
	    	   return ExpFilter(var, currentline);
	       }
	       else {
	    	   return var;
	       }
	}
	

	/**
	 * 返回文本中的表达式列表
	 * @param content
	 * @return
	 */
	public static Map<Object,Object> ReturnRegexs(String content){
		if(VarReplaceExp.is_VarReplace_Exp(content)) {
		return VarReplaceExp.FindContentRegex(content)
					            .stream().collect(
					              Collectors.toMap(k->k.toString(),
					            		  k->new RegexUpdateModel(VarReplaceExp.return_Result(k,DataCache.casedata))));
					            
		}else if(SheetExp.is_Sheet_Exp(content)) {
			return SheetExp.FindContentRegex(content) 
					.stream().collect(
		              Collectors.toMap(k->k.toString(),
		            		  k->new RegexUpdateModel(SheetExp.return_Result(k,DataCache.exceldata))));
		}
		else {
			return new HashMap<Object, Object>();
		}
		
	}
	
	/**
	 * 表达式自身预处理,java
	 * @param content
	 * @return
	 */
	public static String PreRegexDeal(String content) {
		if(JavaExp.is_JavaExp(content)) {
			return JavaExp.JavaParamUpdate(content);
		}
		
		return content;
	}
	
	/**
	 * 表达式自身后处理,java
	 * @param content
	 * @return
	 */
	public static String AfterRegexDeal(String content) {
		if(JavaExp.is_JavaExp(content)) {
			try {
				return JavaExp.JavaExec(content);
			} catch (Exception e) {
				System.out.println("Java表达式解析出错");
				e.printStackTrace();
			}
		}
		return content;
	}
	
	
	/**
	 * 返回文本中的表达式
	 * @return
	 */
	public static List<String> ReturnVarRegexs(String content){
		return VarReplaceExp.FindContentRegex(content);
	}
	
}
