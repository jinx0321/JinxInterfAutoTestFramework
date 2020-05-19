package AutoTest.SuitInfo.HttpSuit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpUtils {
	 private static CloseableHttpClient httpClient;
	  
	  static {
	    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	    cm.setMaxTotal(200);
	    cm.setDefaultMaxPerRoute(20);
	    cm.setDefaultMaxPerRoute(50);
	    httpClient = HttpClients.custom().setConnectionManager(cm).build();
	  }
	  
	  public static String post(Map<String,String> header,Map<String,String> cookie,Map<String,String> param,String url) {
	    
		CloseableHttpResponse response = null;
	    String result = "";
	    try {
	    	HttpPost post = new HttpPost(url);
	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
			if (header != null) {
				for (Entry<String, String> entry : header.entrySet()) {
					post.addHeader(entry.getKey(), entry.getValue());
				}
			}
			String cookiestr = "";
			if (cookie != null) {
				for (Entry<String, String> entry : cookie.entrySet()) {
					cookiestr = cookiestr + entry.getKey() + "=" + entry.getValue() + ";";

				}
			}
			post.addHeader("Cookie", cookiestr);
			if (param != null) {
				for (Entry<String, String> entry : param.entrySet()) {
					params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			
			 System.out.println("请求数据");
			 param.forEach((k,v)->{
	        	 System.out.println(k+":"+v);
	         });
			post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			response = (CloseableHttpResponse) httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			Header[] headers = response.getAllHeaders();
			String content = EntityUtils.toString(entity);
			result=content;
	   
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        if (null != response) response.close();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	    return result;
	  }
	  
	  public static String get(Map<String,String> header,Map<String,String> cookie,Map<String,String> param,String url) {
		    
			CloseableHttpResponse response = null;
		    String result = "";
		    try {
		    	HttpGet get = new HttpGet(url);
		    	List<NameValuePair> params = new ArrayList<NameValuePair>();
				if (header != null) {
					for (Entry<String, String> entry : header.entrySet()) {
						get.addHeader(entry.getKey(), entry.getValue());
					}
				}
				String cookiestr = "";
				if (cookie != null) {
					for (Entry<String, String> entry : cookie.entrySet()) {
						cookiestr = cookiestr + entry.getKey() + "=" + entry.getValue() + ";";

					}
				}
				get.addHeader("Cookie", cookiestr);
				if (param != null) {
					for (Entry<String, String> entry : param.entrySet()) {
						params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
					}
				}
				
				 System.out.println("请求数据");
				 param.forEach((k,v)->{
		        	 System.out.println(k+":"+v);
		         });
				response = (CloseableHttpResponse) httpClient.execute(get);
				HttpEntity entity = response.getEntity();
				Header[] headers = response.getAllHeaders();
				String content = EntityUtils.toString(entity);
				result=content;
		   
		    } catch (IOException e) {
		      e.printStackTrace();
		    } finally {
		      try {
		        if (null != response) response.close();
		      } catch (IOException e) {
		        e.printStackTrace();
		      }
		    }
		    return result;
		  }
	  
	  
}
