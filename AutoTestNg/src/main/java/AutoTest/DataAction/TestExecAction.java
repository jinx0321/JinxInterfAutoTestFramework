package AutoTest.DataAction;

import AutoTest.Base.BaseFlow;
import AutoTest.DataProvider.TestInfo;
import AutoTest.flow.form;

public class TestExecAction {

	/**
	 * 案例接收器
	 * @param form 
	 */
	public void CaseAcceptAc(BaseFlow baseflow,TestInfo ti) {
		System.out.println("当前执行案例id:"+baseflow.getCurrent_TestId());
		//System.out.println("案例信息:"+ti.getTestInfo().toString());
		System.out.println("执行数据:"+ti.getSendData().toString());
		
		
		
	
		
		
		
	}
	
}
