package com.mock.Bean.Log;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class Info {
	private String info="";
	private String flag="";
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String toJson() {
		return "{\"info\":\""+this.info+"\",\"flag\":\""+this.flag+"\"}";
		
	}
	public String toJson(String info,String flag) {
		return "{\"info\":\""+info+"\",\"flag\":\""+flag+"\"}";
		
	}
	

}
