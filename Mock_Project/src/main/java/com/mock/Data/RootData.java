package com.mock.Data;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ROOT")
public class RootData {
	
	private List<UrlData> UrlData;

    @XmlElement(name="UrlData")
	public List<UrlData> getUrldata() {
		return UrlData;
	}

	public void setUrldata(List<UrlData> urldata) {
		this.UrlData = urldata;
	}
	@Override
	public String toString() {
		return "RootData [UrlData=" + UrlData + "]";
	}
	
	

}
