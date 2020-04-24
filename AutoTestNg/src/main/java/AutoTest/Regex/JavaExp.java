package AutoTest.Regex;

import java.util.Stack;
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
	 * 判断是否是java表达式
	 * @param var
	 */
	public void is_JavaExp(String var) {
		
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
	 * @param var2
	 */
	private void JavaMethodParser(String var2){
		String start="";
		StringBuffer sb=new StringBuffer(var2);
		String var=sb.toString();
		Matcher startmatcher=first_pattern.matcher(var);
		if(startmatcher.find()) {
			start=startmatcher.group();
		}
		var=var.replaceAll(RegexUtils.makeQueryStringAllRegExp(start), "");
		char[] ends=var.toCharArray();
	    //解析方法中。。。。。。。。。。。
		System.out.println(var);
	}
	//@Test
	public void test2() {
		String x="{from Java(name=com.xxx.yyy({from Sheet(name=\"案例数据\",value=\"E,2\")},\"123\",123))}";
		System.out.println(is_JavaExpStart(x));
		System.out.println(is_JavaExpEnd(x));
	}
	
	@Test
	public void test() {
		String x="{from Java(name=com.xxx.yyy({from Sheet(name=\"案例数据\",value=\"E,2\")},\"123\",123))}";
		
		JavaMethodParser(x);
	}
}
