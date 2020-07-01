package com.mock.Bean.Data;

import javax.xml.bind.annotation.XmlElement;

public class RequestData {
	private String Param;
	private String Is_Forward;
	private String Data;
	private String ParamId;
	
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
	
	
	
	

	
}