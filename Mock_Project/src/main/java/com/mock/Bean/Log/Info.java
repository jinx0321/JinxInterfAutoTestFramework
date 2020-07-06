package com.mock.Bean.Log;

import com.alibaba.fastjson.JSONObject;

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
