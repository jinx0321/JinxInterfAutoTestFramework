package AutoTest.Base;

import java.util.HashMap;
import java.util.Map;

public class BaseFlow {
	final private String _tail="_al"; 
	final private String _filetype=".xlsx"; 
	public  Object[] returnExcelData() {
   	 
    	System.out.println(this.getClass().getResource("").getPath()+_tail+_filetype);
		
   	    Object[] o=new Object[2];
		Map<String,String> m1=new HashMap<String, String>();
		m1.put("q", "1");
		o[0]=m1;
		Map<String,String> m2=new HashMap<String, String>();
		m2.put("w", "2");
		o[1]=m2;
		return o;	
    }
}
