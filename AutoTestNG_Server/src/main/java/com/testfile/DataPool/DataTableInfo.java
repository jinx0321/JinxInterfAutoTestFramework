package com.testfile.DataPool;

import java.util.List;
import java.util.Map;

/**
 * 瞬时表信息
 * @author jinxh29224
 *
 */
public class DataTableInfo {
	private String id;
	private String tablename;
	private String partcapacity;
	
    //数据量
	private int datacount;
	//分区信息
	/*
	 * {
	 * id:{
	 *   index:
	 *   count：}
	 * }
	 * */
	private Map<String,Map<String,String>> partinfo;
	
	//表字段
	private List<String> titles;
	
	//表主键
	private List<String> primarykey;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getPartcapacity() {
		return partcapacity;
	}

	public void setPartcapacity(String partcapacity) {
		this.partcapacity = partcapacity;
	}

	public int getDatacount() {
		return datacount;
	}

	public void setDatacount(int datacount) {
		this.datacount = datacount;
	}

	public Map<String, Map<String, String>> getPartinfo() {
		return partinfo;
	}

	public void setPartinfo(Map<String, Map<String, String>> partinfo) {
		this.partinfo = partinfo;
	}

	public List<String> getTitles() {
		return titles;
	}

	public void setTitles(List<String> titles) {
		this.titles = titles;
	}

	public List<String> getPrimarykey() {
		return primarykey;
	}

	public void setPrimarykey(List<String> primarykey) {
		this.primarykey = primarykey;
	}

	@Override
	public String toString() {
		return "DataTableInfo [id=" + id + ", tablename=" + tablename + ", partcapacity=" + partcapacity
				+ ", datacount=" + datacount + ", partinfo=" + partinfo + ", titles=" + titles + ", primarykey="
				+ primarykey + "]";
	}
    
}
