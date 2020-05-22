package AutoTest.Report;

import java.util.List;
import java.util.UUID;

import AutoTest.Base.CaseResult;

public class TestResult {
 
	private String uid;
	private String id; //≤‚ ‘id
    private String testName; //≤‚ ‘√˚
    private String className; //≤‚ ‘¿‡√˚
    private String caseName;
	private String params; //≤‚ ‘”√≤Œ ˝
    private String description; //≤‚ ‘√Ë ˆ
    private CaseResult output; //Reporter Output
    private Throwable throwable; //≤‚ ‘“Ï≥£‘≠“Ú
    private String throwableTrace;
    private int status; //◊¥Ã¨
    private String duration;
 
    private boolean success;
 
    public TestResult() {
    	this.uid=UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    
    public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    public String getTestName() {
        return testName;
    }
 
    public void setTestName(String testName) {
        this.testName = testName;
    }
 
    public String getClassName() {
        return className;
    }
 
    public void setClassName(String className) {
        this.className = className;
    }
 
    public String getCaseName() {
        return caseName;
    }
 
    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }
 
    public String getParams() {
        return params;
    }
 
    public void setParams(String params) {
        this.params = params;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public CaseResult getOutput() {
        return output;
    }
 
    public void setOutput(CaseResult output) {
        this.output = output;
    }
 
    public Throwable getThrowable() {
        return throwable;
    }
 
    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;

    }
 
    public int getStatus() {
        return status;
    }
 
    public void setStatus(int status) {
        this.status = status;
    }
 
    public String getDuration() {
        return duration;
    }
 
    public void setDuration(String duration) {
        this.duration = duration;
    }
 
    public boolean isSuccess() {
        return success;
    }
 
    public void setSuccess(boolean success) {
        this.success = success;
    }
 
    public String getThrowableTrace() {
        return throwableTrace;
    }
 
    public void setThrowableTrace(String throwableTrace) {
        this.throwableTrace = throwableTrace;
    }

	@Override
	public String toString() {
		return "TestResult [id=" + id + ", testName=" + testName + ", className=" + className + ", caseName=" + caseName
				+ ", params=" + params + ", description=" + description + ", output=" + output + ", throwable="
				+ throwable + ", throwableTrace=" + throwableTrace + ", status=" + status + ", duration=" + duration
				+ ", success=" + success + "]";
	}
    
    
    
}
