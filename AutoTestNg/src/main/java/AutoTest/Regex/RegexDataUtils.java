package AutoTest.Regex;

import java.util.LinkedList;
import java.util.List;
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
	 * @param regexupdatemodel
	 */
	private void PreRegexUpdate(RegexUpdateModel regexupdatemodel) {
		regexupdatemodel.setContent(RegexInter.PreRegexDeal(regexupdatemodel.getContent()));
	}
	
	/**
	 * ���ʽ�����
	 * @param regexupdatemodel
	 */
	private void AfterRegexLoad(RegexUpdateModel regexupdatemodel) {
		regexupdatemodel.setContent(RegexInter.AfterRegexDeal(regexupdatemodel.getContent()));
	}
	
	/**
	 * ���ʽԤ����
	 */
	private void PreRegexLoad(RegexUpdateModel regexupdatemodel) {

		//������ʽδ��Ԥ����
		if(regexupdatemodel.isIs_pre()==false) {
			//����ģ�ͱ��ʽ
			regexupdatemodel.setContentexp(RegexInter.ReturnRegexs(regexupdatemodel.getContent()));
			
			PreRegexUpdate(regexupdatemodel);
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
	private void RegexLoad(RegexUpdateModel regexupdatemodel) {
		List<Object> removelsit=new LinkedList<Object>();
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
        	    	   removelsit.add(key);
        	       }
             }
             removelsit.forEach(e->{
            	 regexupdatemodel.getContentexp().remove(e); 
             });
             if(regexupdatemodel.getContentexp().size()==0) {
            	 regexupdatemodel.setIs_over(true);
             }
	}
	
	/**
	 * �������ʽ
	 * @param regexupdatemodel
	 */
	public void ParserRegex(RegexUpdateModel regexupdatemodel) {
		//���ʽԤװ��
		PreRegexLoad(regexupdatemodel);
		while(true){
			if(regexupdatemodel.getContentexp().size()>0) {
				RegexLoad(regexupdatemodel);
			}else {
				break;
			}
		}
		
		//���ʽ��װ��
		AfterRegexLoad(regexupdatemodel);
			

		
		
	}
	


	
	@Test
	public void test() {
		String content="wqeqwe{{{{fromSheet(name=\"��������\",value=\"D,2\")}xcsds";
		String key="{{{{fromSheet(name=\"��������\",value=\"D,2\")}";
		System.out.println(content.replaceAll(RegexUtils.makeQueryStringAllRegExp(key), "123"));
		
	}


}
