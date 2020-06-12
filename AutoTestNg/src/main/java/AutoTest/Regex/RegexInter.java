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
 * ���ʽ�ӿ�,�ṩ���ʽ����
 * @author jinxh29224
 *
 */
public class RegexInter{
	
	public static SheetExp SheetExp=new SheetExp();
	public static JavaExp JavaExp=new JavaExp();
	public static VarReplaceExp VarReplaceExp=new VarReplaceExp();
	public static RegexDataUtils RegexDataUtils=new RegexDataUtils();
	
	/**
	 * ��ȡ���ʽ����
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
	 * ��ȡ���ʽֵ,sheet��var
	 * @param var
	 * @param currentline
	 * @return
	 */
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
	 * �����ı��еı��ʽ�б�
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
	 * ���ʽ����Ԥ����,java
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
	 * ���ʽ�������,java
	 * @param content
	 * @return
	 */
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
