package AutoTest.Report.SpecialReport;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.json.JSONArray;
import org.json.JSONObject;

import AutoTest.Report.ReportResult;
import AutoTest.Report.ReportUtil;
import AutoTest.Report.TestResultCollection;

public class MyReport implements SpecialReport{

	@Override
	public VelocityContext returnContent(ReportResult reportresult) {
		
		ReportUtil ReportUtil=new ReportUtil();
		
		VelocityContext vcontent=new VelocityContext();
		vcontent.put("TOTAL", reportresult.getTOTAL());
		vcontent.put("SUCCESS", reportresult.getSUCCESS());
		vcontent.put("FAILED", reportresult.getFAILED());
		vcontent.put("SKIPPED", reportresult.getSKIPPED());
		vcontent.put("ERROR", reportresult.getERROR());
		vcontent.put("STARTTIME", reportresult.getSTARTTIME());
		vcontent.put("DURATION", reportresult.getDURATION());
		vcontent.put("results", reportresult.getCollections());
		vcontent.put("resultsjson", toJson(reportresult.getCollections()));
		
		vcontent.put("REPORTNAME", "MYREPORT");
		

		
		//½¥±äÉ«·¶Î§Öµ
		int max=0;
		int min=999999;
		
		if(reportresult.getSUCCESS()>max) {
			max=reportresult.getSUCCESS();
		}
		if(reportresult.getFAILED()>max) {
			max=reportresult.getFAILED();
		}
		if(reportresult.getSKIPPED()>max) {
			max=reportresult.getSKIPPED();
		}
		if(reportresult.getERROR()>max) {
			max=reportresult.getERROR();
		}
		
		if(reportresult.getSUCCESS()<min) {
			min=reportresult.getSUCCESS();
		}
		if(reportresult.getFAILED()<min) {
			min=reportresult.getFAILED();
		}
		if(reportresult.getSKIPPED()<min) {
			min=reportresult.getSKIPPED();
		}
		if(reportresult.getERROR()<min) {
			min=reportresult.getERROR();
		}
		
		
		vcontent.put("MAX", max);
		vcontent.put("MIN", min);
		
		
		vcontent.put("Pass_rate", ReportUtil.formatPercentage(reportresult.getSUCCESS(),reportresult.getTOTAL()));
		
		return vcontent;
	}


	@Override
	public String returnVmDir() {
		return "myreport.vm";
	}

	@Override
	public String returnReportDir() {
		return "myreport.html";
	}
	
	
	private String toJson(Map<String, TestResultCollection> reslut) {
		
		List<TestResultCollection> list=new LinkedList<TestResultCollection>();
		
		reslut.forEach((k,v)->{
			list.add(v);
		});
		
		JSONArray json=new JSONArray(list);
		
		return json.toString();
	}

}
