package AutoTest.Base;

import java.util.Map;

public class CaseResult {
	
	private String ReutrnMsg;
	private String ExceptionMsg;
	private Map<String,String> extraData;
	public String getReutrnMsg() {
		return ReutrnMsg;
	}
	public void setReutrnMsg(String reutrnMsg) {
		ReutrnMsg = reutrnMsg;
	}
	public String getExceptionMsg() {
		return ExceptionMsg;
	}
	public void setExceptionMsg(String exceptionMsg) {
		ExceptionMsg = exceptionMsg;
	}
	public Map<String, String> getExtraData() {
		return extraData;
	}
	public void setExtraData(Map<String, String> extraData) {
		this.extraData = extraData;
	}
	@Override
	public String toString() {
		return "TestResult [ReutrnMsg=" + ReutrnMsg + ", ExceptionMsg=" + ExceptionMsg + "]";
	}
	
	

}
