package AutoTest.Report;

import java.util.Map;

/**
 * ���ܵ�����Ľ��
 * @author jinxh29224
 *
 */
public class ReportResult {
	
	//������
	private int TOTAL;
	//�ɹ���
	private int SUCCESS;
	//ʧ����
	private int FAILED;
	//������
	private int ERROR;
	//������
	private int SKIPPED;
	//��ʼʱ��
	private String STARTTIME;
	//����ʱ��
	private String DURATION;
	//�����
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
