package AutoTest.flow.test.sign;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;








public class GetSign {
	static String clientId="wjlj";
	static String secret="dsfda945klfd";
	
	public String get(String json) throws Exception {
        JSONObject jo=new JSONObject();
        jo.parse(json);
        
        Map<String,String> map=new HashMap<String, String>();
        jo.getInnerMap().forEach((k,v)->{
        	map.put(k, (String)v);
        });
	
		 String rawSign = ParamSignUtil.sign(map, secret);
         String signStr = new String(Base64.getEncoder().encode((clientId + "|" + rawSign).getBytes("utf-8")));
         
         return signStr;
	}
}
