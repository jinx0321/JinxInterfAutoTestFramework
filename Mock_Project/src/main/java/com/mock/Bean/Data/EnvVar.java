package com.mock.Bean.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.mock.Bean.Proxy.Proxy;
import com.mock.Cache.CacheObject;

@XmlRootElement(name="ENV")
public class EnvVar extends CacheObject {
	
	public EnvVar() {
		this.type="ENV";
	}
	private List<Proxy> proxylist=new LinkedList<Proxy>();
	
	@XmlElement(name="EnvData")
	public List<Proxy> getProxylist() {
		return proxylist;
	}
	public void setProxylist(List<Proxy> proxylist) {
		this.proxylist = proxylist;
	}
	@Override
	public String toString() {
		return "EnvVar [proxylist=" + proxylist + "]";
	}
	
	
	
}
