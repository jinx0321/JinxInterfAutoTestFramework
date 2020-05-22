package AutoTest.Regex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import AutoTest.Base.CaseInfo;
import AutoTest.DataProvider.DataCache;

/**
 * ���ʽ�ӿ�,�ṩ���ʽ����
 * @author jinxh29224
 *
 */
public class RegexInter{
	
	public static SheetExp SheetExp=new SheetExp();
	public static JavaExp JavaExp=new JavaExp();
	public static VarReplaceExp VarReplaceExp=new VarReplaceExp();
	public static RegexDataUtils RegexDataUtils=new RegexDataUtils();
	
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
	 * ���ʽ����Ԥ����
	 * @param content
	 * @return
	 */
	public static String PreRegexDeal(String content) {
		if(JavaExp.is_JavaExp(content)) {
			return JavaExp.JavaParamUpdate(content);
		}
		
		return content;
	}
	
	
	public static String AfterRegexDeal(String content) {
		if(JavaExp.is_JavaExp(content)) {
			try {
				return JavaExp.JavaExec(content);
			} catch (Exception e) {
				System.out.println("Java���ʽ��������");
				e.printStackTrace();
			}
		}
		return content;
	}
	
	
	/**
	 * �����ı��еı��ʽ
	 * @return
	 */
	public static List<String> ReturnVarRegexs(String content){
		return VarReplaceExp.FindContentRegex(content);
	}
	
}
