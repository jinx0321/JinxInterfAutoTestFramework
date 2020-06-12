	package com.mock.Data;

import javax.xml.bind.annotation.XmlElement;

public class UrlData {
	
	private String Url;
	private String Data;
	
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
