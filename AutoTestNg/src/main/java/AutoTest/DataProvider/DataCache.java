package AutoTest.DataProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import AutoTest.Base.CaseInfo;
import AutoTest.DataAction.TestExecAction;
import AutoTest.SuitInfo.HttpSuit.HttpDataParser;

/**
 * ����Ϊ���ݻ���ʹ��,��������ʱ�ĵ�����
 * @author jinxh29224
 *
 */
public class DataCache {
	
	//������Ϊȫ�������ı��ʽ
	public static Map<String,List<List<String>>> exceldata=new HashMap<String, List<List<String>>>();

	//������Ϊ��ǰ���ݽ������ʽ
	public static Map<String,String> casedata=new HashMap<String, String>();
	
	//��ǰ���ݲɼ���
	public static HttpDataParser DataParser=new HttpDataParser();


}
