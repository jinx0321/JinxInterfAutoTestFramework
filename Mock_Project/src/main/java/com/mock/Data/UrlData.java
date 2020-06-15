	package com.mock.Data;

import javax.xml.bind.annotation.XmlElement;

public class UrlData {
	
	private String Url;
	private String Data;
	private String Is_Forward;
	private String Forward_Addr;
	
	
	@XmlElement(name="Is_Forward")
	public String getIs_Forward() {
		return Is_Forward;
	}
	public void setIs_Forward(String is_Forward) {
		Is_Forward = is_Forward;
	}
	@XmlElement(name="Forward_Addr")
	public String getForward_Addr() {
		return Forward_Addr;
	}
	public void setForward_Addr(String forward_Addr) {
		Forward_Addr = forward_Addr;
	}
	@XmlElement(name="Url")
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	@XmlElement(name="Data")
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	
	
	@Override
	public String toString() {
		return "UrlData [Url=" + Url + ", Data=" + Data + "]";
	}
	
	

}
