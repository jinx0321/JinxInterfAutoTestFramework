package AutoTest.DataAction;

import AutoTest.Base.BaseFlow;
import AutoTest.Base.CaseInfo;
import AutoTest.Base.CaseResult;

public abstract class TestExecAction<T extends CaseInfo> {
	

	//����ִ��
	public abstract CaseResult CaseAcceptAc(BaseFlow baseflow,T ti,/*��������*/Object o);
	
	//����ռ�
	public abstract void ResultGather(BaseFlow baseflow,T ti,/*��������*/CaseResult o);
	
	
	
}
