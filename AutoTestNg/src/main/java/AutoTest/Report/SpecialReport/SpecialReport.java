package AutoTest.Report.SpecialReport;

import org.apache.velocity.VelocityContext;

import AutoTest.Report.ReportResult;

public interface SpecialReport {
	
	public VelocityContext returnContent(ReportResult reportresult);
	public String returnVmDir();
	public String returnReportDir();

}
