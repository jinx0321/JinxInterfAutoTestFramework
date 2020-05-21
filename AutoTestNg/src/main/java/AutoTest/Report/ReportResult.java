package AutoTest.Report;

import java.util.Map;

/**
 * 汇总到报表的结果
 * @author jinxh29224
 *
 */
public class ReportResult {
	
	//类总数
	private int TOTAL;
	//成功数
	private int SUCCESS;
	//失败数
	private int FAILED;
	//错误数
	private int ERROR;
	//跳过数
	private int SKIPPED;
	//开始时间
	private String STARTTIME;
	//花费时间
	private String DURATION;
	//结果集
	private Map<String, TestResultCollection> collections;
	public int getTOTAL() {
		return TOTAL;
	}
	public void setTOTAL(int tOTAL) {
		TOTAL = tOTAL;
	}
	public int getSUCCESS() {
		return SUCCESS;
	}
	public void setSUCCESS(int sUCCESS) {
		SUCCESS = sUCCESS;
	}
	public int getFAILED() {
		return FAILED;
	}
	public void setFAILED(int fAILED) {
		FAILED = fAILED;
	}
	public int getERROR() {
		return ERROR;
	}
	public void setERROR(int eRROR) {
		ERROR = eRROR;
	}
	public int getSKIPPED() {
		return SKIPPED;
	}
	public void setSKIPPED(int sKIPPED) {
		SKIPPED = sKIPPED;
	}
	public String getSTARTTIME() {
		return STARTTIME;
	}
	public void setSTARTTIME(String sTARTTIME) {
		STARTTIME = sTARTTIME;
	}
	public String getDURATION() {
		return DURATION;
	}
	public void setDURATION(String dURATION) {
		DURATION = dURATION;
	}
	public Map<String, TestResultCollection> getCollections() {
		return collections;
	}
	public void setCollections(Map<String, TestResultCollection> collections) {
		this.collections = collections;
	}
	
}
