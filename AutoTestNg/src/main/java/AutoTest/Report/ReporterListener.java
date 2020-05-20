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
       
       
       //数据排列
       this.sort(list);
       //结果集解析
       Map<String, TestResultCollection> collections = this.parse(list);
       VelocityContext context = new VelocityContext();

       //总时间
       context.put("TOTAL", TOTAL);
       //成功案例数
       context.put("SUCCESS", SUCCESS);
       //失败案例数
       context.put("FAILED", FAILED);
       //错误案例数
       context.put("ERROR", ERROR);
       //跳过案例数
       context.put("SKIPPED", SKIPPED);
       context.put("MAX", max);
       context.put("MIN", min);
       //开始时间
       context.put("startTime", ReportUtil.formatDate(startDate.getTime()));
       //经过时间
       context.put("DURATION", ReportUtil.formatDuration(endDate.getTime()-startDate.getTime()));
       //案例集合
       context.put("results", collections);

       write(context, outputDirectory);
   }


   private void write(VelocityContext context, String outputDirectory) {
	   outputDirectory=System.getProperties().get("user.dir")+"\\report";
	   if(!new File(outputDirectory).exists()) {
		   new File(outputDirectory).mkdir()	;
	   }
	   
       try {
           //写文件
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
           // 转换输出
           t.merge(context, writer);
           writer.flush();
       } catch (IOException e) {
           e.printStackTrace();
       }
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

       //测试类名
       testResult.setClassName(t.getTestClass().getName());
       //测试参数
       testResult.setParams(ti.getSendData().toString());
       //测试名
       testResult.setTestName(ti.getTestname());
       testResult.setCaseName(ti.getTestname());
       //测试状态
       testResult.setStatus(t.getStatus());

       testResult.setThrowable(t.getThrowable());
       long duration = t.getEndMillis() - t.getStartMillis();
       testResult.setDuration(ReportUtil.formatDuration(duration));

       testResult.setOutput(Reporter.getOutput(t));

       
       return testResult;
   }
}

