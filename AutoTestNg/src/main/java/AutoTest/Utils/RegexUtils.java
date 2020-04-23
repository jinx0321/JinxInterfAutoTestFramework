package AutoTest.Utils;

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
}
