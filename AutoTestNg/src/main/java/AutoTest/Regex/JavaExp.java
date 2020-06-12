package AutoTest.Regex;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import AutoTest.Utils.RegexUtils;

/**
 * java���ʽ
 * @author jinxh29224
 *
 */
public class JavaExp {
	private final String start_regex="\\{[\\s]*[F,f][R,r][O,o][M,m][\\s]*[J,j][A,a][V,v][A,a][\\s]*\\([\\s]*[N,n][A,a][M,m][E,e][\\s]*=[\\s]*";
	private Pattern first_pattern=Pattern.compile(start_regex);
    
	
	/**
	 * ����ǰ׺,������׺,��������ʱ���ڲ���ǰ��β,����java��������
	 */
	private final String paramPrefix="JINX0321";
	private final String paramSuffix="1230XNIJ";
	/**
	 * �ж��Ƿ���java���ʽ
	 * @param var
	 */
	public boolean is_JavaExp(String var) {
		return is_JavaExpStart(var)&&is_JavaExpEnd(var);
	}

	
	/**
	 * java���ʽͷ��У��
	 * @return
	 */
	private boolean is_JavaExpStart(String var) {
		return first_pattern.matcher(var).find();
	}
	/**
	 * java���ʽβ��У��
	 * @return
	 */
	private boolean is_JavaExpEnd(String var) {
		//����һ������
		StringBuffer sb=new StringBuffer(var);
		return sb.toString().replaceAll(" ", "").endsWith(")}");
	}
	
	/**
	 * java��������
	 * 
	 */
	private final String methodregex="[N,n][A,a][M,m][E,e][\\s]*=[\\s]*[a-zA-Z\\.^(]+\\(";
	private final Pattern methodPattern=Pattern.compile(methodregex);
	private String JavaMethodParser(String var2) {
		String start="";
		StringBuffer sb=new StringBuffer(var2);
		String var=sb.toString();
		Matcher m=methodPattern.matcher(var);
		while(m.find()) {
			String re=m.group();
			return re.replaceAll(" ", "").substring(5, re.length()-1);
		}
		return var2;
	}
	/**
	 * java������������,���ر��ʽ����
	 * @param var2
	 */
	private List<String> JavaParamParserLocal(String var2){
		String start="";
		StringBuffer sb=new StringBuffer(var2);
		String var=sb.toString();
		String method=JavaMethodParser(var2);
		Matcher startmatcher=first_pattern.matcher(var);
		if(startmatcher.find()) {
			start=startmatcher.group();
		}
		var=var.replaceAll(RegexUtils.makeQueryStringAllRegExp(start), "");
		char[] ends=var.toCharArray();
	    //���������С���������������������
		int end1=0;
		int end2=0;
		for(int i=0;i<ends.length;i++) {
	          if(ends[i]==')') {
	        	  end1=i;
	          }
	          if(ends[i]=='}') {
	        	  end2=i;
	          }
		}
		var=var.substring(0, end1);
		var=var.replaceAll(method, "").trim();
		var=var.substring(1, var.length()-1).trim();
		ends=var.toCharArray();
		
		String num="1234567890";
		
		//��������ϵ
		List<Integer> localstore=new LinkedList<Integer>();
		//���������Ϣ
		List<Map<String,String>> parammap=new LinkedList<Map<String,String>>();
		
		//������ƥ�俪ʼ
		boolean Curly_braces=false;
		//˫����ƥ�俪ʼ
		boolean Double_quotation=false;
		//˫���ŵ�һ��ƥ������
		int Double_quotation_first=0;
		//����ƥ��
		boolean Num=false;
		
		for(int i=0;i<var.length();i++) {
			char cur=var.charAt(i);
			if(cur=='{') {
			  //�����ǰ�ַ��Ǵ�����
				if(!Curly_braces&&!Double_quotation&&!Num) {
					//�����ǰδ���������ƥ���˫����ƥ�������ƥ��
					Curly_braces=true;
					localstore.add(i);
				}
			}
			if(cur=='"') {
				//�����ǰ��˫����
				if(!Curly_braces&&!Double_quotation&&!Num) {
					//�����ǰδ���������ƥ���˫����ƥ�������ƥ��
					Double_quotation=true;
					Double_quotation_first=i;
					localstore.add(i);
				}
			}
			
			if(num.contains(String.valueOf(cur))) {
				//�����ǰ������
				if(!Curly_braces&&!Double_quotation&&!Num) {
					//�����ǰδ���������ƥ���˫����ƥ�������ƥ��
					Num=true;
					localstore.add(i);
				}
			}
			
			if(Curly_braces) {
			   //�����ǰ�Ǵ�����ƥ��ģʽ	
				if((cur=='}'&&nextchar(var,i)==',')||/*�����߽�*/(cur=='}'&&i==(var.length()-1))) {
					//ƥ�����
					localstore.add(i+1);
					Curly_braces=false;
				}
			}
			
			if(Double_quotation) {
				   //�����ǰ��˫����ƥ��ģʽ
				if(Double_quotation_first!=i) {
					//����ǵ�һ��˫��������
					if((cur=='"'&&prechar(var,i)!='\\'&&nextchar(var,i)==',')||(cur=='"'&&prechar(var,i)!='\\'&&i==(var.length()-1))) {
						localstore.add(i+1);
						Double_quotation=false;
					}
				}	
			 }
			
			if(Num) {
				//�����ǰ������ƥ��ģʽ
				if((nextchar(var,i)==',')||(i==(var.length()-1))) {
					localstore.add(i+1);
					Num=false;
				}
				
			}
			
		}
		
		List<String> params=new LinkedList<String>();
		
		for(int i=0;i<localstore.size();i=i+2) {
			String param=var.substring(Integer.valueOf(localstore.get(i)), Integer.valueOf(localstore.get(i+1)));
			params.add(param);
		}
		
		if(localstore.size()%2!=0) {
			System.out.println("java���ʽ���������쳣");
		}else {
		    return params;
		}
		return params;
	}
	
	/**
	 * ��sheet���ʽ����˫����,���ڲ���ʶ��
	 * @param var
	 */
	public String JavaParamUpdate(String var) {
		
		List<String> params=JavaParamParserLocal(var);
		
		for(int i=0;i<params.size();i++) {
			String e=params.get(i);
			//��sheet���ʽ����
			if(RegexInter.SheetExp.is_Sheet_Exp(e)) {
				e=RegexUtils.makeQueryStringAllRegExp(e);
				var=var.replaceAll(e, "\""+e+"\"");
			}
		}

		return var;
	}
	//����
	private String comma="6a9f1074916d4fd1b446fe5402111a3e";
	//˫����
	private String Dq="40768bb779c248bb9fa6f59823371fc8";
	/**
	 * �����ַ�����,��Ҫ�Ǵ������ֵ�е�{},",,
	 * @param content
	 */
	public String SpecialDealBefore(String content){
		return content.replace(",", comma).replace("\"", Dq);
	}
	public String SpecialDealAfter(String content){
		return content.replace(comma, ",").replace(Dq, "\"");
	}
	
	
	/**
	 * java���ʽִ��
	 * @param var
	 * @return
	 * @throws Exception 
	 * @throws  
	 */
	public String JavaExec(String var) throws Exception{
		String cm=JavaMethodParser(var);
		List<String> params=JavaParamParserLocal(var);
		
		List<Object> dealparams=new LinkedList<Object>();
		
		params.forEach(e->{
			//������ַ���
			if(e.startsWith("\"")&&e.endsWith("\"")) {
				dealparams.add(SpecialDealAfter(String.valueOf(e.substring(1, e.length()-1))));
			}
			//���������
			else{
				if(e.contains(".")) {
					dealparams.add(Double.valueOf(e));
				}else {
					dealparams.add(Integer.valueOf(e));
				}
			}
			
		});
		//dealparams.forEach(e->{
			//System.out.println(e.getClass().getName());
		//});
		String[] cms=cm.split("\\.");
		String method=cms[cms.length-1];
		String clazz="";
		for(int i=0;i<cms.length-1;i++) {
			clazz=clazz+cms[i]+".";
		}
		clazz=clazz.substring(0, clazz.length()-1);
		Method[] methods=Class.forName(clazz).getMethods();
		for(int i=0;i<methods.length;i++) {
			if(methods[i].getName().toLowerCase().equals(method.toLowerCase())) {
				return (String) methods[i].invoke(Class.forName(clazz).newInstance(), dealparams.toArray());
			    //return var;
			}
		}
		
		return var;
	}
	
	
	/**
	 * ��һ���ַ���ʲô,�հ׷�����
	 * @return
	 */
	private Character nextchar(String var,int index) {
		if(index<=var.length()-2) {
		   index++;
	       char next=var.charAt(index);
	       while(next==' '&&index<=var.length()-2) {
	    	   index++;
	    	   next=var.charAt(index);
	       }
	       if(next==' ') {
	    	   return '-';
	       }
	       return next;
		}
		return '-';
	}
	/**
	 * ��һ���ַ���ʲô
	 * @return
	 */
	private Character prechar(String var,int index) {
		if(index<=var.length()-1&&index>0) {
		   index--;
	       char next=var.charAt(index);
	       while(next==' '&&index<=var.length()-1&&index>0) {
	    	   index--;
	    	   next=var.charAt(index);
	       }
	       if(next==' ') {
	    	   return '-';
	       }
	       return next;
		}
		return '-';
	}
	
	/**
	 * ������Ϣ
	 * @author jinxh29224
	 *
	 */
	class paraminfo{
		//�ڼ�������
		private int local;
		//����ԭ��
		private String paramsource;
		//��������
		private String paramtype;
		//��������
		private String paramvalue;
		public String getParamvalue() {
			return paramvalue;
		}
		public void setParamvalue(String paramvalue) {
			this.paramvalue = paramvalue;
		}
		public int getLocal() {
			return local;
		}
		public void setLocal(int local) {
			this.local = local;
		}
		public String getParamsource() {
			return paramsource;
		}
		public void setParamsource(String paramsource) {
			this.paramsource = paramsource;
		}
		public String getParamtype() {
			return paramtype;
		}
		public void setParamtype(String paramtype) {
			this.paramtype = paramtype;
		}
		
		
	}
	
	//@Test
	public void test2() {
		String x="{from Java(name=com.xxx.yyy({from Sheet(name=\"��������\",value=\"E,2\")},\"123\",123))}";
		System.out.println(is_JavaExpStart(x));
		System.out.println(is_JavaExpEnd(x));
	}
	
	@Test
	public void test() throws Exception {
		//String x="{from Java(name=AutoTest.Base.TestMethod.TestMethod.test(\"111222333\",\"123\",12,123.2))}";
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		//System.out.println(nextchar("123 ",2));
		//System.out.println(prechar(" 1 234",1));
		////System.out.println(' '=="12 34".charAt(1));
	//System.out.println(JavaMethodParser(x));
	}
}
