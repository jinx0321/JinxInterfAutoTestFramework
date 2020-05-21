package AutoTest.Report;

import org.testng.ITestResult;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
 
public class TestResultCollection {
	
	private String uid;
	
	private String classid;
	
	public String getClassid() {
		return classid;
	}



	public void setClassid(String classid) {
		this.classid = classid;
	}

	private String classname;
 
    private int totalSize = 0;
 
    private int successSize = 0;
 
    private int failedSize = 0;
 
    private int errorSize = 0;
 
    private int skippedSize = 0;
 
    private List<TestResult> resultList;
    
    public TestResultCollection() 
    {
    	
    	this.uid=UUID.randomUUID().toString().replaceAll("-", "");
    }
 
    
 
    public void addTestResult(TestResult result) {
        if (resultList == null) {
            resultList = new LinkedList<>();
        }
        resultList.add(result);
 
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                failedSize+=1;
                break;
            case ITestResult.SUCCESS:
                successSize+=1;
                break;
            case ITestResult.SKIP:
                skippedSize+=1;
                break;
        }
 
        totalSize+=1;
    }
 
    /*===============================[getter && setter]=================================*/
 
    public int getTotalSize() {
        return totalSize;
    }
 
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
 
    public int getSuccessSize() {
        return successSize;
    }
 
    public void setSuccessSize(int successSize) {
        this.successSize = successSize;
    }
 
    public int getFailedSize() {
        return failedSize;
    }
 
    public void setFailedSize(int failedSize) {
        this.failedSize = failedSize;
    }
 
    public int getErrorSize() {
        return errorSize;
    }
 
    public void setErrorSize(int errorSize) {
        this.errorSize = errorSize;
    }
 
    public int getSkippedSize() {
        return skippedSize;
    }
 
    public void setSkippedSize(int skippedSize) {
        this.skippedSize = skippedSize;
    }
 
    public List<TestResult> getResultList() {
        return resultList;
    }
 
    public void setResultList(List<TestResult> resultList) {
        this.resultList = resultList;
    }

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classid=classname;
		this.classname = classname.replaceAll("_", ".");
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
    
}

