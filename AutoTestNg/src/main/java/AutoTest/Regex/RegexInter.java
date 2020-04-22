package AutoTest.Regex;

import java.util.List;
import java.util.Map;

import AutoTest.DataProvider.DataCache;
import AutoTest.DataProvider.TestInfo;

/**
 * ���ʽ�ӿ�,�ṩ���ʽ����
 * @author jinxh29224
 *
 */
public class RegexInter{
	
	public static SheetExp SheetExp=new SheetExp();
	public static JavaExp JavaExp=new JavaExp();
	public static VarReplaceExp VarReplaceExp=new VarReplaceExp();
	
	public static String ExpFilter(String var,Map<String,String> currentline){
		    //����Ǳ����滻���ʽ
	       if(VarReplaceExp.is_VarReplace_Exp(var)) {
	    	   var=VarReplaceExp.return_Result(var, currentline);
	    	   return ExpFilter(var,currentline);
	    	   
	       }
	       //�����sheet���ʽ
	       else if(SheetExp.is_Sheet_Exp(var)) {
	    	   var=SheetExp.return_Result(var, DataCache.exceldata);
	    	   return ExpFilter(var, currentline);
	       }
	       else {
	    	   return var;
	       }
	}

	/**
	 * �����ı��еı��ʽ
	 * @return
	 */
	public static List<String> ReturnVarRegexs(String content){
		return VarReplaceExp.FindContentRegex(content);
	}
	
}
