package AutoTest.DataAction;

import AutoTest.Base.BaseFlow;
import AutoTest.DataProvider.TestInfo;
import AutoTest.flow.form;

public class TestExecAction {

	/**
	 * ����������
	 * @param form 
	 */
	public void CaseAcceptAc(BaseFlow baseflow,TestInfo ti) {
		System.out.println("��ǰִ�а���id:"+baseflow.getCurrent_TestId());
		System.out.println("������Ϣ:"+ti.getTestInfo().toString());
		System.out.println("header��Ϣ:"+ti.getHeaderInfo().toString());
		System.out.println("cookie��Ϣ:"+ti.getCookieInfo().toString());
		System.out.println("ִ������:"+ti.getSendData().toString());
		
		
		
	
		
		
		
	}
	
}
