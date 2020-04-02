package AutoTest.Regex;

import java.util.Stack;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * java表达式解析
 * @author jinxh29224
 *
 */
public class JavaExp {

	/**
	 * 判断是否是java表达式
	 * @param regex
	 */
	public boolean is_Java_Exp(String exp) {
		Stack<String> stack=new Stack<String>();
		
  return false;
		

		
	}
	
	@Test
	public void test() {
		String content="{from Java(name=\"com.xxx.xxx(\"123\",\"456\")\")}";
		
		System.out.println(is_Java_Exp(content));
	}
	
}
