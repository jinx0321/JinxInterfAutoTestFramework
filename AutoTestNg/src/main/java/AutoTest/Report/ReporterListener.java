package AutoTest.Report;

import java.util.List;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import AutoTest.Base.TestInfo;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
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
       //mock����
       int FAILED = 0;
       
       int ERROR = 0;
       int SKIPPED = 0;

       for (ISuite suite : suites) {
           Map<String, ISuiteResult> suiteResults = suite.getResults();
           //����suit���
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
       /* �������� */
       TOTAL = SUCCESS + FAILED + SKIPPED + ERROR;

       int max=0;
       int min=999999;
       
       if(SUCCESS>max) {
    	   max=SUCCESS;
       } if(FAILED>max) {
    	   max=FAILED;
       } if(SKIPPED>max) {
    	   max=SKIPPED;
       } if(ERROR>max) {
    	   max=ERROR;
       }
       if(SUCCESS<min) {
    	   min=SUCCESS;
       } if(FAILED<min) {
    	   min=FAILED;
       } if(SKIPPED<min) {
    	   min=SKIPPED;
       } if(ERROR<min) {
    	   min=ERROR;
       }
       
       
       //��������
       this.sort(list);
       //���������
       Map<String, TestResultCollection> collections = this.parse(list);
       VelocityContext context = new VelocityContext();

       //��ʱ��
       context.put("TOTAL", TOTAL);
       //�ɹ�������
       context.put("SUCCESS", SUCCESS);
       //ʧ�ܰ�����
       context.put("FAILED", FAILED);
       //��������
       context.put("ERROR", ERROR);
       //����������
       context.put("SKIPPED", SKIPPED);
       context.put("MAX", max);
       context.put("MIN", min);
       //��ʼʱ��
       context.put("startTime", ReportUtil.formatDate(startDate.getTime()));
       //����ʱ��
       context.put("DURATION", ReportUtil.formatDuration(endDate.getTime()-startDate.getTime()));
       //��������
       context.put("results", collections);

       write(context, outputDirectory);
   }


   private void write(VelocityContext context, String outputDirectory) {
	   outputDirectory=System.getProperties().get("user.dir")+"\\report";
	   if(!new File(outputDirectory).exists()) {
		   new File(outputDirectory).mkdir()	;
	   }
	   
       try {
           //д�ļ�
           VelocityEngine ve = new VelocityEngine();
           Properties p = new Properties();
           p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, outputDirectory);
           p.setProperty(Velocity.ENCODING_DEFAULT, "utf-8");
           p.setProperty(Velocity.INPUT_ENCODING, "utf-8");
           p.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
           ve.init(p);


           Template t = ve.getTemplate("report.vm");
           OutputStream out = new FileOutputStream(new File(outputDirectory+"/report.html"));
           BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
           // ת�����
           t.merge(context, writer);
           writer.flush();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   /**
    * �б�����
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
    * �����תlist
    * @param resultMap
    * @return
    */
   private LinkedList<ITestResult> listTestResult(IResultMap resultMap){
       Set<ITestResult> results = resultMap.getAllResults();
       return new LinkedList<ITestResult>(results);
   }

   
   /**
    * ����list�����
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
    	   //��ȡ����
           String className = t.getTestClass().getName();
           if (collectionMap.containsKey(className)) {
        	   
               TestResultCollection collection = collectionMap.get(className);
               collection.addTestResult(toTestResult(t));

           } else {
               TestResultCollection collection = new TestResultCollection();
               collection.addTestResult(toTestResult(t));
               collectionMap.put(className, collection);
           }
       }

       return collectionMap;
   }

   private TestResult toTestResult(ITestResult t) {
       TestResult testResult = new TestResult();
       Object[] params = t.getParameters();

       TestInfo ti=(TestInfo)params[0];
       
       testResult.setId(ti.getId());

       //��������
       testResult.setClassName(t.getTestClass().getName());
       //���Բ���
       testResult.setParams(ti.getSendData().toString());
       //������
       testResult.setTestName(ti.getTestname());
       testResult.setCaseName(ti.getTestname());
       //����״̬
       testResult.setStatus(t.getStatus());

       testResult.setThrowable(t.getThrowable());
       long duration = t.getEndMillis() - t.getStartMillis();
       testResult.setDuration(ReportUtil.formatDuration(duration));

       testResult.setOutput(Reporter.getOutput(t));

       
       return testResult;
   }
}

