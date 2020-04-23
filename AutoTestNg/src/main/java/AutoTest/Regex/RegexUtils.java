package AutoTest.Regex;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.Test;

import AutoTest.DataProvider.DataCache;

/**
 * ���ʽģ�ͽ�������
 * @author jinxh29224
 *
 */
public class RegexUtils {

	/**
	 * ���ʽԤ����
	 */
	public void PreRegexLoad(RegexUpdateModel regexupdatemodel) {

		//������ʽδ��Ԥ����
		if(regexupdatemodel.isIs_pre()==false) {
			//����ģ�ͱ��ʽ
			regexupdatemodel.setContentexp(RegexInter.ReturnRegexs(regexupdatemodel.getContent()));
			
			//���ñ��ʽԤ���ر�־Ϊtrue
		    regexupdatemodel.setIs_pre(true);		
		    
		    //���Ѿ������ı��ʽģ�ͽ���Ԥ����
		    regexupdatemodel.getContentexp().forEach((k,v)->{
		    	PreRegexLoad((RegexUpdateModel)v);
		    });
		}
	}
	
	/**
	 * ���ʽ����װ��
	 * @param regexupdatemodel
	 */
	public void RegexLoad(RegexUpdateModel regexupdatemodel) {
		
		   if(regexupdatemodel.getContentexp().size()!=0) {
             for(Entry<String, RegexUpdateModel> e:regexupdatemodel.getContentexp().entrySet()) {
        	       String key=(String) e.getKey();
        	       RegexUpdateModel value=(RegexUpdateModel) e.getValue();
        	       //�������½ڵ����ӱ��ʽ,��������ʽ
        	       if(value.getContentexp().size()==0) {
        	    	   value.setContent(RegexInter.ExpFilter(value.getContent(), DataCache.casedata));
        	       }
            }
		   }
		
	}
	
	/**
	 * ���±��ʽģ��
	 * ��map������ȱ���,���ı��еı��ʽ���б����滻
	 * 
	 */
	public void UpdateRegexModel(RegexUpdateModel regexdatamodel) {
		
	}
	
	/**
	 * ���ʽ�Ƿ�������
	 * @return
	 */
	public boolean isRegexParserComp(RegexUpdateModel regexdatamodel) {
		return 	regexdatamodel.getContentexp().size()==0;
	}
	/**
	 * ���µ�ǰ���ʽ
	 */
	public void UpdateRegex(RegexUpdateModel regexdatamodel){
		if(regexdatamodel.isIs_over()) {
			
		}
		
	}
	
	@Test
	public void test() {
		String content="123${123}232";
		String key="$";
		System.out.println(content.replaceAll(key.replaceAll("$", "\\$"), "123"));
		
	}

}
