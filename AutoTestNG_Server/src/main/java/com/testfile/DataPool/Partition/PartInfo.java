package com.testfile.DataPool.Partition;

import java.util.List;
import java.util.Map;

public class PartInfo {
	boolean is_insert=true;
	public boolean isIs_insert() {
		return is_insert;
	}

	public void setIs_insert(boolean is_insert) {
		this.is_insert = is_insert;
	}

	//是否需要新增分区
	boolean is_part=false;
	
	//数据落在哪个分区上
	/*
	 * [{
	 *    index:num
	 *    count:num
	 *    liststart:num
	 *    listend:num
	 * 
	 * }]
	 */
	List<Map<String,String>> partlocal;

	public boolean isIs_part() {
		return is_part;
	}

	public void setIs_part(boolean is_part) {
		this.is_part = is_part;
	}

	public List<Map<String, String>> getPartlocal() {
		return partlocal;
	}

	public void setPartlocal(List<Map<String, String>> partlocal) {
		this.partlocal = partlocal;
	}
	
	

}
