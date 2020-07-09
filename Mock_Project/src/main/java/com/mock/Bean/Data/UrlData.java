	package com.mock.Bean.Data;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.mock.Bean.Proxy.Proxy;

public class UrlData {
	
	private String Url;
	private String Data;
	private String Is_Forward;
	private String Forward_Addr;
    private String Is_Disable;
    private Proxy Proxy;
	private List<RequestData> RequestData=new LinkedList<RequestData>();
    private String id;
	
	@XmlElement(name="id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement(name="Is_Disable")
	public String getIs_Disable() {
		return Is_Disable;
	}
	public void setIs_Disable(String is_Disable) {
		Is_Disable = is_Disable;
	}
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
	
	@XmlElement(name="Request_Data")
	public List<RequestData> getRequestData() {
		return RequestData;
	}
	
	public void setRequestData(List<RequestData> requestData) {
		RequestData = requestData;
	}
	
	@XmlElement(name="Proxy")
	public Proxy getProxy() {
		return Proxy;
	}
	public void setProxy(Proxy proxy) {
		Proxy = proxy;
	}
	@Override
	public String toString() {
		return "UrlData [Url=" + Url + ", Data=" + Data + "]";
	}
	
	

}
