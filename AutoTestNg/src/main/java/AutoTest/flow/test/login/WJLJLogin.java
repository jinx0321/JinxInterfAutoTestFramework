package AutoTest.flow.test.login;



import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class WJLJLogin {
	@Test
	public void test() throws Throwable {
		//1583292602381
		//System.out.println(new Date().getTime());
		Check_Login("jinx0321","jinx@0321","comp");
		/*
		String json="{\"is_success\":true,\"error_no\":\"0\",\"error_msg\":\"\",\"data\":[{\"is_success\":\"true\",\"error_no\":\"\",\"error_msg\":\"\",\"user_info\":[{\"account\":\"jinx0321\",\"user_code\":\"\",\"name\":\"jinx\",\"user_type\":\"admin\",\"status\":\"\",\"parent_user_name\":\"\",\"utoken\":\"5iooigygpseobyydbzfqdy4u6u7jk34umhguatplb4woublcf7ab5ewyslqplnbagqhy5vj6jc5rs\"}]}]}";
		JSONObject obj=new JSONObject(json);
		JSONArray data= obj.getJSONArray("data");
		JSONArray user_info=data.getJSONObject(0).getJSONArray("user_info");
		String utoken=user_info.getJSONObject(0).getString("utoken");
		System.out.println(utoken);
		*/
	}

	public void test2() throws Exception {
		Map<String,String> logininfo=new HashMap<String, String>();
		   HttpClient httpClient=new DefaultHttpClient();
		   URI url=new URI("http://www.wjljtest.5ibazhuayu.com.cn/api/member/add_comp");
	       HttpPost post=new HttpPost(url);
	       List<NameValuePair> params = new ArrayList<NameValuePair>();  
	       Map<String,String> map=new HashMap<String, String>();
	       map.put("userAccount", "jxhqy01");
	       map.put("userName", "jxhqy01");
	       map.put("loginPwd", "123456");
	       map.put("uscCode", "911302255089864134");
	       if(map!=null){  
	           for (Entry<String, String> entry : map.entrySet()) {  
	               params.add( new BasicNameValuePair(entry.getKey(),entry.getValue()) );  
	           }         
	       }
	       post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
	      // post.addHeader("Cookie", "_hslsid2_=\"X2hzbHNpZDJfMTU4MjUxMzM2NzQwMTE5eHRwOXMwZXM4YmVzbTcxaXU1ZTBxaGU=\"; _f_ux_=pfijsz6t2z5jcsvmuwgdd4vaizxncfwszs525frvob33ohy6ztoikpzocmcyp5bsv6mslp2trxjlpcdtw7abyqp26ih3c65donzbwavaww2fofuu23ob4c2obez6vp3u; _hssid2_=4F729A15C3BCD463848794274EACDC8DD9AAB3613D94AE00658311D961446901D76E3719DF6FAC86C7790D861470B4C46996D0062CD2487C331E31A386B85B2153987554BE6433A5196193FC02B43119C16F270BD75E8F468EE416CABA8333EFE70C5409350E3096CD72EC5A6A2B10D7570141AD8285DE2251660A413D0A9931FE9F73B8567249192212D61D33C79230; _hslsid_=\"X2hzbHNpZF8xNTgyNTE0MDEzNjM3M2U3bmk4YnR3ZDNpcGIxdWd4amMwc2toZw==\"; _hssid_=4F729A15C3BCD463848794274EACDC8DD9AAB3613D94AE00658311D9614469019F96750C8DD00D769474511B7276B41ED7D3455306D6B5CD5488F004F4ECC5BB020C946B1356F7005DA012353A7BBD4C15ADDDEDECCD523B035BFCD81BDD27A3C2A037CE829303EE7C11F928670FB2821A8F485B535CAD84ACCC98287E3E347E2CAE4E3B7F031DE25E3D9DE9D7CB0C19");
	       CloseableHttpResponse httpResponse;
	       httpResponse = (CloseableHttpResponse) httpClient.execute(post);
	       HttpEntity entity = httpResponse.getEntity(); 
	       Header[] headers=httpResponse.getAllHeaders();
	       System.out.println(EntityUtils.toString(entity));
	       httpResponse.close(); 
	}
	
	public static Map<String,String> Check_Login(String useraccount,String password,String type) throws Exception {
		  System.out.println("µÇÂ¼ÕËºÅÎª:"+useraccount);
		   Map<String,String> logininfo=new HashMap<String, String>();
		   HttpClient httpClient=new DefaultHttpClient();
		   URI url=new URI("http://www.wjljtest.5ibazhuayu.com.cn/api/member/login");
	       HttpPost post=new HttpPost(url);
	       List<NameValuePair> params = new ArrayList<NameValuePair>();  
	       Map<String,String> map=new HashMap<String, String>();
	       map.put("account", useraccount);
	       map.put("password", token.Encode(password));
	       map.put("check_code", "");
	       map.put("login_ip", "115.236.91.15");
	       map.put("biz_type", type);
	      
	       if(map!=null){  
	           for (Entry<String, String> entry : map.entrySet()) {  
	               params.add( new BasicNameValuePair(entry.getKey(),entry.getValue()) );  
	           }         
	       }
	       post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
	      // post.addHeader("Cookie", "_hslsid2_=\"X2hzbHNpZDJfMTU4MjUxMzM2NzQwMTE5eHRwOXMwZXM4YmVzbTcxaXU1ZTBxaGU=\"; _f_ux_=pfijsz6t2z5jcsvmuwgdd4vaizxncfwszs525frvob33ohy6ztoikpzocmcyp5bsv6mslp2trxjlpcdtw7abyqp26ih3c65donzbwavaww2fofuu23ob4c2obez6vp3u; _hssid2_=4F729A15C3BCD463848794274EACDC8DD9AAB3613D94AE00658311D961446901D76E3719DF6FAC86C7790D861470B4C46996D0062CD2487C331E31A386B85B2153987554BE6433A5196193FC02B43119C16F270BD75E8F468EE416CABA8333EFE70C5409350E3096CD72EC5A6A2B10D7570141AD8285DE2251660A413D0A9931FE9F73B8567249192212D61D33C79230; _hslsid_=\"X2hzbHNpZF8xNTgyNTE0MDEzNjM3M2U3bmk4YnR3ZDNpcGIxdWd4amMwc2toZw==\"; _hssid_=4F729A15C3BCD463848794274EACDC8DD9AAB3613D94AE00658311D9614469019F96750C8DD00D769474511B7276B41ED7D3455306D6B5CD5488F004F4ECC5BB020C946B1356F7005DA012353A7BBD4C15ADDDEDECCD523B035BFCD81BDD27A3C2A037CE829303EE7C11F928670FB2821A8F485B535CAD84ACCC98287E3E347E2CAE4E3B7F031DE25E3D9DE9D7CB0C19");
	       CloseableHttpResponse httpResponse;
	       httpResponse = (CloseableHttpResponse) httpClient.execute(post);
	       HttpEntity entity = httpResponse.getEntity(); 
	       Header[] headers=httpResponse.getAllHeaders();
	       String resultjson=EntityUtils.toString(entity);
	      System.out.println("µÇÂ¼·µ»ØÐÅÏ¢:"+resultjson);
	   	JSONObject obj=new JSONObject(resultjson);
		JSONArray data= obj.getJSONArray("data");
		JSONArray user_info=data.getJSONObject(0).getJSONArray("user_info");
		String utoken=user_info.getJSONObject(0).getString("utoken");
		logininfo.put("utoken", utoken);
	       httpResponse.close();  
	       return logininfo;
	  }
	
	
	
}
