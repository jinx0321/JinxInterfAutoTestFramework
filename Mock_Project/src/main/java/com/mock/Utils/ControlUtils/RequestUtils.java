package com.mock.Utils.ControlUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;

@Component
public class RequestUtils {
	
	
	public JSONObject toJsonObject(HttpServletRequest request)   {
       Map<String,String[]> backupparam=backupParam(request);
		try {
			String content=getRequestPostStr(request);
			
			if(JSON.isValid(content)) {
				return JSONObject.parseObject(content);
			}else{
				Map<String,String[]> params=backupparam;
				JSONObject json=new JSONObject();
				params.forEach((k,v)->{
					if(v==null||v.length==0) {
						json.put(k,"");
					}else if(v.length==1) {
						json.put(k,v[0]);
					}else {
						JSONArray ja=new JSONArray();
					   for(int i=0;i<v.length;i++) {
						   ja.add(v[i]);
					   }
						json.put(k, ja);
					}
					
				});
				return json;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new JSONObject();
		}
    
	}
	
	/**
	 * 备份一份参数
	 * @param request
	 * @return
	 */
	private Map<String,String[]> backupParam(HttpServletRequest request){
		Map<String,String[]> copy=new LinkedHashMap<String, String[]>();
		request.getParameterMap().forEach((k,v)->{
			copy.put(k, v);
		});
		return copy;
	}
	
	/**
     * 描述:获取 post 请求的 byte[] 数组
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException
     */
    private  byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {
            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }
 
    /**
     * 描述:获取 post 请求内容
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException
     */
    private String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        if(buffer==null) {
        	 return "";
        }
        return new String(buffer, charEncoding);
    }

}
