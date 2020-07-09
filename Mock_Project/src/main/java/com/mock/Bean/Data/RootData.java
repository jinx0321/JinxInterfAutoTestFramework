package com.mock.Bean.Data;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mock.Cache.CacheObject;

@XmlRootElement(name="ROOT")
public class RootData extends CacheObject{
	
	public RootData() {
		this.type="UrlData";
	} 
	
	private List<UrlData> UrlData=new LinkedList<UrlData>();

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
