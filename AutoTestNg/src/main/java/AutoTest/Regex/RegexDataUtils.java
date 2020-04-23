package AutoTest.Regex;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.Test;

import AutoTest.DataProvider.DataCache;
import AutoTest.Utils.RegexUtils;

/**
 * ���ʽģ�ͽ�������
 * @author jinxh29224
 *
 */
public class RegexDataUtils {

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
	public synchronized void  RegexLoad(RegexUpdateModel regexupdatemodel) {
			 //������ǰ�ڵ�ı��ʽ
             for(Entry<String, RegexUpdateModel> e:regexupdatemodel.getContentexp().entrySet()) {
        	       String key=(String) e.getKey();
        	       RegexUpdateModel value=(RegexUpdateModel) e.getValue();
        	       //�������½ڵ����ӱ��ʽ,��������ʽ
        	       if(value.getContentexp().size()!=0) {
        	    	   RegexLoad(value);
        	       }else {
        	    	   //�����ձ��ʽ���н���
        	    	   String aftercontent=RegexInter.ExpFilter(value.getContent(), DataCache.casedata);
        	    	   //���µ�ǰ�ڵ��content��contentexp
        	    	   regexupdatemodel.setContent(regexupdatemodel.getContent().replaceAll(RegexUtils.makeQueryStringAllRegExp(key), aftercontent));
        	    	   regexupdatemodel.getContentexp().remove(key); 
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
		String content="wqeqwe{{{{fromSheet(name=\"��������\",value=\"D,2\")}xcsds";
		String key="{{{{fromSheet(name=\"��������\",value=\"D,2\")}";
		System.out.println(content.replaceAll(RegexUtils.makeQueryStringAllRegExp(key), "123"));
		
	}


}
