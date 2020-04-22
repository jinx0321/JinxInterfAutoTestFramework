package AutoTest.Regex;

import java.util.Stack;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * java表达式解析
 *
 * 入参类型{
 *    表达式,
 *    字符串,
 *    数字
 *   
 *    }
 * @author jinxh29224
 *
 */
public class JavaExp {
	
	
	/**
	 * 判断是否是java表达式
	 * {from Java(name="com.xxx.xxx("123","456")")}
	 * @param regex
	 */
	public boolean is_Java_Exp(String exp) {
		String first_regex="\\{[\\s]*[F,f][R,r][O,o][M,m][\\s]*[J,j][A,a][V,v][A,a][^\\}]*\\}";
		
		//第一层校验
		if(Pattern.matches(first_regex,exp)) {
		     char[] exparray=exp.toCharArray();
		     //截取name中class
		     int start=0;
		     int end=0;
		     for(int i=0;i<exparray.length;i++){
		    	 if(exparray[i]=='(') {
		    		 start=i+1;
		    		 i=exparray.length;
		    	 }
		     }
		     for(int i=exparray.length-1;i>0;i--){
		    	 if(exparray[i]==')') {
		    		 end=i;
		    		 i=-1;
		    	 }
		     }
		     
		     String exp2=exp.substring(start,end);
		     //System.out.println(exp2);
		     String second_regex="[\\s]*[N,n][A,a][M,m][E,e][\\s]*=[\\s]*.*";
		     
		     //第二层校验
		     if(Pattern.matches(second_regex,exp2)) {
		    	 char[] exp2array=exp2.toCharArray();
		    	 
		    	 int start2=0;
		    	 for(int i=0;i<exp2array.length;i++){
			    	 if(exp2array[i]=='=') {
			    		 start2=i+1;
			    		 i=exp2array.length;
			    	 }
			     }
		    	 String exp3=exp2.substring(start2,exp2.length()).trim();
		    	// System.out.println(exp3);
		    
			    return true;
		     }
		}else {
			return false;
		}
		return false;
	}
	
	
	/**
	 * java表达式参数解析
	 * @param exp
	 */
	public void JavaParamParser(String exp) {
		  char[] exparray=exp.toCharArray();
		     //截取name中class
		     int start=0;
		     int end=0;
		     for(int i=0;i<exparray.length;i++){
		    	 if(exparray[i]=='(') {
		    		 start=i+1;
		    		 i=exparray.length;
		    	 }
		     }
		     for(int i=exparray.length-1;i>0;i--){
		    	 if(exparray[i]==')') {
		    		 end=i;
		    		 i=-1;
		    	 }
		     }
		     
		     String exp2=exp.substring(start,end);
		     
		     char[] exp2array=exp2.toCharArray();
	    	 
	    	 int start2=0;
	    	 for(int i=0;i<exp2array.length;i++){
		    	 if(exp2array[i]=='=') {
		    		 start2=i+1;
		    		 i=exp2array.length;
		    	 }
		     }
	    	 
	    	 String exp3=exp2.substring(start2,exp2.length()).trim();
	    	 
	    	 char[] exp3array=exp3.toCharArray();
	    	 
	    	 int start3=0;
	    	 int end3=0;
	    	 
	    	 for(int i=0;i<exp3array.length;i++){
			    	 if(exp3array[i]=='(') {
			    		 start3=i+1;
			    		 i=exp3array.length;
			    	 }
			     }
			     for(int i=exp3array.length-1;i>0;i--){
			    	 if(exp3array[i]==')') {
			    		 end3=i;
			    		 i=-1;
			    	 }
			     }
			     
			  String param=exp3.substring(start3,end3);
			  
			  System.out.println(exp);
			  System.out.println(exp2);
			  System.out.println(exp3);
			  System.out.println(param);
			  
			
		
			
	    	 
	}
	
	@Test
	public void test() {
		String content="{from Java(name=\"com.xxx.xxx(\"123\",\"456\")\")}";
		
		JavaParamParser(content);
	}
	
}
