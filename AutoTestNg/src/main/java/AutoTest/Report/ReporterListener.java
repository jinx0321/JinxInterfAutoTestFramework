package AutoTest.Report;

import java.util.List;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import AutoTest.Base.CaseInfo;
import AutoTest.Report.SpecialReport.MyReport;
import AutoTest.Report.SpecialReport.SpecialReport;
import AutoTest.Report.SpecialReport.VelocityUtils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.util.*;

public class ReporterListener implements IReporter {

   @Override
   public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
       Map<String, Object> result = new HashMap<>();
       List<ITestResult> list = new LinkedList<>();
       Date startDate = new Date();
       Date endDate = new Date();

       int TOTAL = 0;
       int SUCCESS = 0;
       //mock数据
       int FAILED = 0;
       
       int ERROR = 0;
       int SKIPPED = 0;

       for (ISuite suite : suites) {
           Map<String, ISuiteResult> suiteResults = suite.getResults();
           //遍历suit结果
           for (ISuiteResult suiteResult : suiteResults.values()) {
               ITestContext testContext = suiteResult.getTestContext();

               startDate = startDate.getTime()>testContext.getStartDate().getTime()?testContext.getStartDate():startDate;

               if (endDate==null) {
                   endDate = testContext.getEndDate();
               } else {
                   endDate = endDate.getTime()<testContext.getEndDate().getTime()?testContext.getEndDate():endDate;
               }

               IResultMap passedTests = testContext.getPassedTests();
               IResultMap failedTests = testContext.getFailedTests();
               IResultMap skippedTests = testContext.getSkippedTests();
               IResultMap failedConfig = testContext.getFailedConfigurations();

               SUCCESS += passedTests.size();
               FAILED += failedTests.size();
               SKIPPED += skippedTests.size();
               ERROR += failedConfig.size();

               list.addAll(this.listTestResult(passedTests));
               list.addAll(this.listTestResult(failedTests));
               list.addAll(this.listTestResult(skippedTests));
               list.addAll(this.listTestResult(failedConfig));
           }
       }
       /* 计算总数 */
       TOTAL = SUCCESS + FAILED + SKIPPED + ERROR;

       //数据排列
       this.sort(list);
       //结果集解析
       Map<String, TestResultCollection> collections = this.parse(list);
       
       ReportResult reportresult=new ReportResult();
        
       reportresult.setTOTAL(TOTAL);
       reportresult.setSUCCESS(SUCCESS);
       reportresult.setERROR(ERROR);
       reportresult.setFAILED(FAILED);
       reportresult.setSKIPPED(SKIPPED);
       reportresult.setSTARTTIME(ReportUtil.formatDate(startDate.getTime()));
       reportresult.setDURATION(ReportUtil.formatDuration(endDate.getTime()-startDate.getTime()));
       reportresult.setCollections(collections);

       /**
        * 报表数据处理输出
        */
       SpecialReport specialreport=new MyReport();
       
       VelocityUtils.write(
    		   specialreport.returnContent(reportresult),
    		   outputDirectory,
    		   specialreport.returnVmDir(),
    		   specialreport.returnReportDir()
    		   );
   }


  

   /**
    * 列表排序
    * @param list
    */
   private void sort(List<ITestResult> list){
       Collections.sort(list, new Comparator<ITestResult>() {
           @Override
           public int compare(ITestResult r1, ITestResult r2) {
               if(r1.getStartMillis()>r2.getStartMillis()){
                   return 1;
               }else{
                   return -1;
               }
           }
       });
   }

   /**
    * 结果集转list
    * @param resultMap
    * @return
    */
   private LinkedList<ITestResult> listTestResult(IResultMap resultMap){
       Set<ITestResult> results = resultMap.getAllResults();
       return new LinkedList<ITestResult>(results);
   }

   
   /**
    * 解析list结果集
    * 
    * {
    *   classname :[{
    *                 testname
    *              },{
    *                 testname
    *              }]
    * 
    * 
    * }
    * @param list
    * @return
    */
   private Map<String, TestResultCollection> parse(List<ITestResult> list) {

       Map<String, TestResultCollection> collectionMap = new HashMap<>();

       for (ITestResult t: list) {
    	   //获取类名
           String className = t.getTestClass().getName().replaceAll("\\.", "_");
           if (collectionMap.containsKey(className)) {
               TestResultCollection collection = collectionMap.get(className);
               collection.addTestResult(toTestResult(t));

           } else {
               TestResultCollection collection = new TestResultCollection();
               collection.addTestResult(toTestResult(t));
               collection.setClassname(className);
               collectionMap.put(className, collection);
           }
       }

       return collectionMap;
   }

   private TestResult toTestResult(ITestResult t) {
       TestResult testResult = new TestResult();
       Object[] params = t.getParameters();

       CaseInfo ti=(CaseInfo)params[0];
       
       testResult.setId(ti.getId());

       //测试类名
       testResult.setClassName(t.getTestClass().getName());
       //测试参数
       testResult.setParams(ti.getSendData().toString());
       //测试名
       testResult.setTestName(ti.getTestname());
       //测试状态
       testResult.setStatus(t.getStatus());
       //请求信息|请求层面异常
       testResult.setOutput(ti.getMsg());
       
       
       //案例层面异常
       testResult.setThrowable(t.getThrowable());
       long duration = t.getEndMillis() - t.getStartMillis();
       testResult.setDuration(ReportUtil.formatDuration(duration));

  

       
       return testResult;
   }
   
   
   @Test
   public void test() {
	 String x="a.b.c";
	 System.out.println(x.replaceAll("\\.", "_"));

	 System.out.println(x.replaceAll("\\.", "_").replaceAll("_", "."));
   }
}

