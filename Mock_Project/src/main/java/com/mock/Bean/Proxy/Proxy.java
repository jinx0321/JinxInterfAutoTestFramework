package com.mock.Bean.Proxy;

import javax.xml.bind.annotation.XmlElement;

/**
 * 代理配置
 * @author jinxh29224
 *
 */
public class Proxy {
	
	private String is_proxy;
	private String ip;
	private String port;
	private String dns;
    private String id;
	
	@XmlElement(name="id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@XmlElement(name="is_proxy")
	public String getIs_proxy() {
		return is_proxy;
	}
	public void setIs_proxy(String is_proxy) {
		this.is_proxy = is_proxy;
	}
	@XmlElement(name="ip")
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@XmlElement(name="port")
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	@XmlElement(name="dns")
	public String getDns() {
		return dns;
	}
	public void setDns(String dns) {
		this.dns = dns;
	}
	
	

}
