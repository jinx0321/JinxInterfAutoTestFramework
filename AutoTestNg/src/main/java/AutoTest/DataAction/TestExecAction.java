package AutoTest.DataAction;

import AutoTest.Base.BaseFlow;
import AutoTest.Base.CaseInfo;
import AutoTest.Base.CaseResult;

public abstract class TestExecAction<T extends CaseInfo> {
	

	//案例执行
	public abstract CaseResult CaseAcceptAc(BaseFlow baseflow,T ti,/*额外数据*/Object o);
	
	//结果收集
	public abstract void ResultGather(BaseFlow baseflow,T ti,/*返回数据*/CaseResult o);
	
	
	
}
