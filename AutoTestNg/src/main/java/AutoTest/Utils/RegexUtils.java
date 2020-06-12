package AutoTest.Utils;

import java.util.UUID;

import org.junit.Test;

public class RegexUtils {
	/**
     * ת�����������ַ� ��$()*+.[]?\^{}
     * \\��Ҫ��һ���滻������replace�����滻ʱ�����߼�bug
     */
    public static String makeQueryStringAllRegExp(String str) {
     
        return str.replace("\\", "\\\\").replace("*", "\\*")
                .replace("+", "\\+").replace("|", "\\|")
                .replace("{", "\\{").replace("}", "\\}")
                .replace("(", "\\(").replace(")", "\\)")
                .replace("^", "\\^").replace("$", "\\$")
                .replace("[", "\\[").replace("]", "\\]")
                .replace("?", "\\?").replace(",", "\\,")
                .replace(".", "\\.").replace("&", "\\&");
    }
    
    @Test
    public void test() {
    	System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }
}
