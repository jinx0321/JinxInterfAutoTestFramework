package AutoTest.Regex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet.P;
import org.junit.Test;

public class VarReplaceExp {
	  private final String regex="[\\s]*\\$[\\s]*\\{[\\s]*[a-z,A-Z,0-9]+[\\s]*\\}[\\s]*";
	  
	  
	    /**
	     * 判断是否是变量替换表达式
	     * @param regex
	     * @return
	     */
	    public boolean is_VarReplace_Exp(String regex) {
	    	return Pattern.matches(this.regex, regex);
	    }
	    
	    /**
	     * 变量替换
	     */
	    public String return_Result(String regex,Map<String,String> currentline){
	    	return currentline.containsKey(RegexParser(regex))==true?currentline.get(RegexParser(regex)):"";
	    }
	    
	    /**
	     * 表达式解析
	     * @return
	     */
	    private String RegexParser(String regex){
	    	return regex.replaceAll(" ", "").substring(2, regex.replaceAll(" ", "").length()-1).trim();
	    }
	    
	    
	    /**
	     * 查询文本中的替换表达式
	     * @param content
	     * @return
	     */
	    public List<String> FindContentRegex(String content){
	    	Pattern p=Pattern.compile(regex);
	    	Matcher m=p.matcher(content);
	    	List<String> regexs=new LinkedList<String>();
	    	while(m.find()) {
	    	  regexs.add(m.group().replaceAll(" ", ""));
	    	}
	    	return regexs;
	    }


	    @Test
	    public void testr() {
	    	System.out.println(FindContentRegex("\r\n" + 
	    			" \"is_success\": true,\r\n" + 
	    			" \"error_no\": \"0\",\r\n" + 
	    			" \"error_msg\": \"\",\r\n" + 
	    			" \"data\": [\r\n" + 
	    			"  {\r\n" + 
	    			"   \"loaned_code\": \" ${ xyz } \",\r\n" + 
	    			"   \"product_biz_type\": \"CommissionLoan\"\r\n" + 
	    			" }\r\n" + 
	    			" ]\r\n" + 
	    			"}\r\n" + 
	    			""));
	    }
	    
	
}
