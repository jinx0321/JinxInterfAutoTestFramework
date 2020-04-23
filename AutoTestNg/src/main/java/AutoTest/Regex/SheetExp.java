package AutoTest.Regex;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;
import AutoTest.DataProvider.ExcelUtils;

/**
 * sheet表达式,可以定位到sheet页任意地方
 * @author jinxh29224
 *
 */
public class SheetExp {
    private final String regex="\\{[\\s]*[F,f][R,r][O,o][M,m][\\s]*[S,s][H,h][E,e][E,e][T,t][\\s]*\\([\\s]*[N,n][A,a][M,m][E,e][\\s]*=[\\s]*\\\"[\\s]*[^\\)^\\\"]*\\\"[\\s]*,[\\s]*[V,v][A,a][L,l][U,u][E,e][\\s]*=[\\s]*\\\"[\\s]*[^\\)^\\\"]*\\\"[\\s]*\\)[\\s]*\\}[\\s]*";
    private final String[] word= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private String[] row=new String[word.length*2];
 
    public SheetExp() {
    	for(int i=0;i<word.length;i++) {
    		row[i]=word[i];
    		row[i+word.length]="A"+word[i];
    	}
    }
    /**
     * 判断是否是sheet表达式
     * @param regex
     * @return
     */
    public boolean is_Sheet_Exp(String var) {
    	return Pattern.compile(regex).matcher(var).find();
    }
    
    /**
     * 对外暴露接口
     * @param regex
     * @param data
     * @return
     */
    public String return_Result(String regex,Map<String,List<List<String>>> data) {
    	if(is_Sheet_Exp(regex)) {
    	  String param=ParamReplace(data,regexParser(regex));
    	if(is_Sheet_Exp(param)) {
    		return return_Result(param,data);
    	}else {
    		return param;
    	}
    	}else {
    	return regex;
    	}
    	
    }
    
    /**
     * 表达式解析
     * @param regex
     * @return
     */
    private Map<String,String> regexParser(String regex){
    	String nameregex="[N,n][A,a][M,m][E,e][\\s]*=[\\s]*\\\"[\\s]*[^\\)^\\\"]*\\\"[\\s]*";
    	String valueregex="[V,v][A,a][L,l][U,u][E,e][\\s]*=[\\s]*\\\"[\\s]*[^\\)^\\\"]*\\\"[\\s]*";
    	String name="";
    	String value="";
    	
    	Pattern namep=Pattern.compile(nameregex);
    	Matcher namem=namep.matcher(regex);
    	Pattern valuep=Pattern.compile(valueregex);
    	Matcher valuem=valuep.matcher(regex);
    	while(namem.find()) {
    		name=namem.group().replaceAll(" ", "").split("=")[1].replaceAll("\"", "");
    	}
    	while(valuem.find()) {
    		value=valuem.group().replaceAll(" ", "").split("=")[1].replaceAll("\"", "");
    	}
    	
    	Map<String,String> location=new HashMap<String, String>();
    	location.put("name", name);
    	location.put("value", value);
    	return location;
    	
    }
    
    /**
     * 参数替换
     */
    private String ParamReplace(Map<String,List<List<String>>> data,Map<String,String> regex) {
    	  String str="";
    	  if(!data.containsKey(regex.get("name"))) {
    		  return str;
    	  }else{
    			  List<List<String>> row=data.get(regex.get("name"));
    			  int x=wordindex(regex.get("value").split(",")[0]);
    			  int y=Integer.valueOf(regex.get("value").split(",")[1]);
    			 
    			  if(x==-1) {
    				  return str;
    			  }
    			 
    			  //System.out.println(x+":"+y);
    			  //System.out.println(row.size());
    			  if(y>row.size()) {
    				  return str;
    			  }
    			  y=y-1;//y坐标修正 	
    			  for(/*y轴*/int i=0;i<row.size();i++) {
    				  if(i==y) {
    					  if(x>row.get(i).size()) {
    						  return str;
    					  }else {
    						  str=row.get(i).get(x);
    					  }
    					  
    				  }
    			  }
    		  } 
    	  
    		return str;
    }
    
    /**
     * 查询文本中的替换表达式
     * @param content
     * @return
     */
    public List<String> FindContentRegex(String content){
    	Pattern pattern=Pattern.compile(regex);
		Matcher m=pattern.matcher(content);
    	List<String> regexs=new LinkedList<String>();
    	while(m.find()) {
    	  regexs.add(m.group());
    	}
    	return regexs;
    }

    
    /**
     * 返回字母坐标
     * @return
     */
    private int wordindex(String word){
    	for(int i=0;i<row.length;i++) {
    		if(row[i].toLowerCase().equals(word.toLowerCase())) {
    			return i;
    		}
    	}
    	return -1;
    }
    @Test
     public void is_SheetExp() throws IOException, Exception{
    	//Map<String,String> regex=regexParser("{from Sheet(name=\"案例数据\",value=\"A,1\")}");
    	//System.out.println(ParamReplace(ExcelUtils.readExcel("D:\\gitproject\\JinxInterAutoTestFramework\\AutoTestNg\\src\\main\\java\\AutoTest\\flow\\test_al.xlsx"),regex));
    	//{fromSheet(name="案例数据",value="E,2")}
    	String regex="{from Sheet(name=\"案例数据\",value=\"D,2\")}";
    	System.out.println(is_Sheet_Exp(regex)); 
     }
	
}
