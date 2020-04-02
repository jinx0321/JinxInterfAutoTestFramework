package AutoTest.DataAction;

import AutoTest.Base.BaseFlow;
import AutoTest.DataProvider.TestInfo;
import AutoTest.flow.test;

public class TestExecAction {

	/**
	 * 案例接收器
	 * @param test 
	 */
	public void CaseAcceptAc(BaseFlow baseflow,TestInfo ti) {
		System.out.println("当前执行案例id:"+baseflow.getCurrent_TestId());
		System.out.println("执行数据:"+ti.getSendData().toString());
		
		
		
	
		
		
		
	}
	
}
