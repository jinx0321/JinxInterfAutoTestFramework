package AutoTest.Regex;

import java.util.List;
import java.util.Map;

import AutoTest.DataProvider.DataCache;
import AutoTest.DataProvider.TestInfo;

/**
 * 表达式接口,提供表达式工具
 * @author jinxh29224
 *
 */
public class RegexInter{
	
	public static SheetExp SheetExp=new SheetExp();
	public static JavaExp JavaExp=new JavaExp();
	public static VarReplaceExp VarReplaceExp=new VarReplaceExp();
	
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
	 * 返回文本中的表达式
	 * @return
	 */
	public static List<String> ReturnVarRegexs(String content){
		return VarReplaceExp.FindContentRegex(content);
	}
	
}
