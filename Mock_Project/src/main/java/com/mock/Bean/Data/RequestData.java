package com.mock.Bean.Data;

import javax.xml.bind.annotation.XmlElement;

import com.mock.Bean.Proxy.Proxy;

public class RequestData {

	private String Param;
	private String Is_Forward;
	private String Data;
	private String ParamId;
	private String Is_Disable;
	private Proxy Proxy;
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
	@XmlElement(name="Param")
	public String getParam() {
		return Param;
	}
	public void setParam(String param) {
		Param = param;
	}
	@XmlElement(name="Is_Forward")
	public String getIs_Forward() {
		return Is_Forward;
	}
	public void setIs_Forward(String is_Forward) {
		Is_Forward = is_Forward;
	}
	@XmlElement(name="Data")
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	
	@XmlElement(name="ParamId")
	public String getParamId() {
		return ParamId;
	}
	public void setParamId(String paramId) {
		ParamId = paramId;
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
		return "RequestData [Param=" + Param + ", Is_Forward=" + Is_Forward + ", Data=" + Data + ", ParamId=" + ParamId
				+ ", Is_Disable=" + Is_Disable + "]";
	}	
}
