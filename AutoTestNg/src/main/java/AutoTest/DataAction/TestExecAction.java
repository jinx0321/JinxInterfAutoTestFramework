package AutoTest.DataAction;

import AutoTest.Base.BaseFlow;
import AutoTest.Base.TestInfo;

public abstract class TestExecAction<T extends TestInfo> {
	

	public abstract Object CaseAcceptAc(BaseFlow baseflow,T ti,/*¶îÍâÊı¾İ*/Object o);
	
	
	
}
