package AutoTest.Regex;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import AutoTest.Utils.RegexUtils;

/**
 * java表达式
 * @author jinxh29224
 *
 */
public class JavaExp {
	private final String start_regex="\\{[\\s]*[F,f][R,r][O,o][M,m][\\s]*[J,j][A,a][V,v][A,a][\\s]*\\([\\s]*[N,n][A,a][M,m][E,e][\\s]*=[\\s]*";
	private Pattern first_pattern=Pattern.compile(start_regex);
    
	
	/**
	 * 参数前缀,参数后缀,参数解析时放在参数前和尾,便于java参数解析
	 */
	private final String paramPrefix="JINX0321";
	private final String paramSuffix="1230XNIJ";
	/**
	 * 判断是否是java表达式
	 * @param var
	 */
	public boolean is_JavaExp(String var) {
		return is_JavaExpStart(var)&&is_JavaExpEnd(var);
	}

	
	/**
	 * java表达式头部校验
	 * @return
	 */
	private boolean is_JavaExpStart(String var) {
		return first_pattern.matcher(var).find();
	}
	/**
	 * java表达式尾部校验
	 * @return
	 */
	private boolean is_JavaExpEnd(String var) {
		//打造一个镜像
		StringBuffer sb=new StringBuffer(var);
		return sb.toString().replaceAll(" ", "").endsWith(")}");
	}
	
	/**
	 * java方法解析
	 * 
	 */
	private final String methodregex="[N,n][A,a][M,m][E,e][\\s]*=[\\s]*[a-zA-Z\\.^(]+\\(";
	private final Pattern methodPattern=Pattern.compile(methodregex);
	private String JavaMethodParser(String var2) {
		String start="";
		StringBuffer sb=new StringBuffer(var2);
		String var=sb.toString();
		Matcher m=methodPattern.matcher(var);
		while(m.find()) {
			String re=m.group();
			return re.replaceAll(" ", "").substring(5, re.length()-1);
		}
		return var2;
	}
	/**
	 * java方法参数解析,返回表达式坐标
	 * @param var2
	 */
	private List<String> JavaParamParserLocal(String var2){
		String start="";
		StringBuffer sb=new StringBuffer(var2);
		String var=sb.toString();
		String method=JavaMethodParser(var2);
		Matcher startmatcher=first_pattern.matcher(var);
		if(startmatcher.find()) {
			start=startmatcher.group();
		}
		var=var.replaceAll(RegexUtils.makeQueryStringAllRegExp(start), "");
		char[] ends=var.toCharArray();
	    //解析方法中。。。。。。。。。。。
		int end1=0;
		int end2=0;
		for(int i=0;i<ends.length;i++) {
	          if(ends[i]==')') {
	        	  end1=i;
	          }
	          if(ends[i]=='}') {
	        	  end2=i;
	          }
		}
		var=var.substring(0, end1);
		var=var.replaceAll(method, "").trim();
		var=var.substring(1, var.length()-1).trim();
		ends=var.toCharArray();
		
		String num="1234567890";
		
		//储存坐标系
		List<Integer> localstore=new LinkedList<Integer>();
		//储存参数信息
		List<Map<String,String>> parammap=new LinkedList<Map<String,String>>();
		
		//大括号匹配开始
		boolean Curly_braces=false;
		//双引号匹配开始
		boolean Double_quotation=false;
		//双引号第一次匹配坐标
		int Double_quotation_first=0;
		//数字匹配
		boolean Num=false;
		
		for(int i=0;i<var.length();i++) {
			char cur=var.charAt(i);
			if(cur=='{') {
			  //如果当前字符是大括号
				if(!Curly_braces&&!Double_quotation&&!Num) {
					//如果当前未进入大括号匹配和双引号匹配和数字匹配
					Curly_braces=true;
					localstore.add(i);
				}
			}
			if(cur=='"') {
				//如果当前是双引号
				if(!Curly_braces&&!Double_quotation&&!Num) {
					//如果当前未进入大括号匹配和双引号匹配和数字匹配
					Double_quotation=true;
					Double_quotation_first=i;
					localstore.add(i);
				}
			}
			
			if(num.contains(String.valueOf(cur))) {
				//如果当前是数字
				if(!Curly_braces&&!Double_quotation&&!Num) {
					//如果当前未进入大括号匹配和双引号匹配和数字匹配
					Num=true;
					localstore.add(i);
				}
			}
			
			if(Curly_braces) {
			   //如果当前是大括号匹配模式	
				if((cur=='}'&&nextchar(var,i)==',')||/*条件边界*/(cur=='}'&&i==(var.length()-1))) {
					//匹配结束
					localstore.add(i+1);
					Curly_braces=false;
				}
			}
			
			if(Double_quotation) {
				   //如果当前是双引号匹配模式
				if(Double_quotation_first!=i) {
					//如果非第一次双引号坐标
					if((cur=='"'&&prechar(var,i)!='\\'&&nextchar(var,i)==',')||(cur=='"'&&prechar(var,i)!='\\'&&i==(var.length()-1))) {
						localstore.add(i+1);
						Double_quotation=false;
					}
				}	
			 }
			
			if(Num) {
				//如果当前是数字匹配模式
				if((nextchar(var,i)==',')||(i==(var.length()-1))) {
					localstore.add(i+1);
					Num=false;
				}
				
			}
			
		}
		
		List<String> params=new LinkedList<String>();
		
		for(int i=0;i<localstore.size();i=i+2) {
			String param=var.substring(Integer.valueOf(localstore.get(i)), Integer.valueOf(localstore.get(i+1)));
			params.add(param);
		}
		
		if(localstore.size()%2!=0) {
			System.out.println("java表达式参数解析异常");
		}else {
		    return params;
		}
		return params;
	}
	
	/**
	 * 把sheet表达式加上双引号,便于参数识别
	 * @param var
	 */
	public String JavaParamUpdate(String var) {
		
		List<String> params=JavaParamParserLocal(var);
		
		for(int i=0;i<params.size();i++) {
			String e=params.get(i);
			//对sheet表达式更新
			if(RegexInter.SheetExp.is_Sheet_Exp(e)) {
				e=RegexUtils.makeQueryStringAllRegExp(e);
				var=var.replaceAll(e, "\""+e+"\"");
			}
		}

		return var;
	}
	//逗号
	private String comma="6a9f1074916d4fd1b446fe5402111a3e";
	//双引号
	private String Dq="40768bb779c248bb9fa6f59823371fc8";
	/**
	 * 特殊字符处理,主要是处理变量值中的{},",,
	 * @param content
	 */
	public String SpecialDealBefore(String content){
		return content.replace(",", comma).replace("\"", Dq);
	}
	public String SpecialDealAfter(String content){
		return content.replace(comma, ",").replace(Dq, "\"");
	}
	
	
	/**
	 * java表达式执行
	 * @param var
	 * @return
	 * @throws Exception 
	 * @throws  
	 */
	public String JavaExec(String var) throws Exception{
		String cm=JavaMethodParser(var);
		List<String> params=JavaParamParserLocal(var);
		
		List<Object> dealparams=new LinkedList<Object>();
		
		params.forEach(e->{
			//如果是字符串
			if(e.startsWith("\"")&&e.endsWith("\"")) {
				dealparams.add(SpecialDealAfter(String.valueOf(e.substring(1, e.length()-1))));
			}
			//如果是数字
			else{
				if(e.contains(".")) {
					dealparams.add(Double.valueOf(e));
				}else {
					dealparams.add(Integer.valueOf(e));
				}
			}
			
		});
		//dealparams.forEach(e->{
			//System.out.println(e.getClass().getName());
		//});
		String[] cms=cm.split("\\.");
		String method=cms[cms.length-1];
		String clazz="";
		for(int i=0;i<cms.length-1;i++) {
			clazz=clazz+cms[i]+".";
		}
		clazz=clazz.substring(0, clazz.length()-1);
		Method[] methods=Class.forName(clazz).getMethods();
		for(int i=0;i<methods.length;i++) {
			if(methods[i].getName().toLowerCase().equals(method.toLowerCase())) {
				return (String) methods[i].invoke(Class.forName(clazz).newInstance(), dealparams.toArray());
			    //return var;
			}
		}
		
		return var;
	}
	
	
	/**
	 * 下一个字符是什么,空白符除外
	 * @return
	 */
	private Character nextchar(String var,int index) {
		if(index<=var.length()-2) {
		   index++;
	       char next=var.charAt(index);
	       while(next==' '&&index<=var.length()-2) {
	    	   index++;
	    	   next=var.charAt(index);
	       }
	       if(next==' ') {
	    	   return '-';
	       }
	       return next;
		}
		return '-';
	}
	/**
	 * 上一个字符是什么
	 * @return
	 */
	private Character prechar(String var,int index) {
		if(index<=var.length()-1&&index>0) {
		   index--;
	       char next=var.charAt(index);
	       while(next==' '&&index<=var.length()-1&&index>0) {
	    	   index--;
	    	   next=var.charAt(index);
	       }
	       if(next==' ') {
	    	   return '-';
	       }
	       return next;
		}
		return '-';
	}
	
	/**
	 * 参数信息
	 * @author jinxh29224
	 *
	 */
	class paraminfo{
		//第几个参数
		private int local;
		//参数原型
		private String paramsource;
		//参数类型
		private String paramtype;
		//参数类型
		private String paramvalue;
		public String getParamvalue() {
			return paramvalue;
		}
		public void setParamvalue(String paramvalue) {
			this.paramvalue = paramvalue;
		}
		public int getLocal() {
			return local;
		}
		public void setLocal(int local) {
			this.local = local;
		}
		public String getParamsource() {
			return paramsource;
		}
		public void setParamsource(String paramsource) {
			this.paramsource = paramsource;
		}
		public String getParamtype() {
			return paramtype;
		}
		public void setParamtype(String paramtype) {
			this.paramtype = paramtype;
		}
		
		
	}
	
	//@Test
	public void test2() {
		String x="{from Java(name=com.xxx.yyy({from Sheet(name=\"案例数据\",value=\"E,2\")},\"123\",123))}";
		System.out.println(is_JavaExpStart(x));
		System.out.println(is_JavaExpEnd(x));
	}
	
	@Test
	public void test() throws Exception {
		//String x="{from Java(name=AutoTest.Base.TestMethod.TestMethod.test(\"111222333\",\"123\",12,123.2))}";
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		//System.out.println(nextchar("123 ",2));
		//System.out.println(prechar(" 1 234",1));
		////System.out.println(' '=="12 34".charAt(1));
	//System.out.println(JavaMethodParser(x));
	}
}
