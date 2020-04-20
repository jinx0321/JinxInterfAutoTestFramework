package com.testfile.DataPool.Create;

import java.util.LinkedList;
import java.util.List;

/**
 * 数据创建表基础信息
 * @author jinxh29224
 *
 */
public class DataCreatePoolTable {
	
	//表id
	private String id;
	//表名
	private String Tablename;
	//表字段
	private List<String> Title=new LinkedList<String>();
	//分区容量
	private Integer partCapacity;
	//主键,插入数据操作时主键必须存在,且需要保持唯一性
	private List<String> primarykey=new LinkedList<String>();
	
	//设置id
	public void setId(String id) {
		this.id = id;
	}
	
	public String GetId() {
		return this.id;
	}
	//设置表名
	public void setTablename(String tablename) {
		Tablename = tablename;
	}
	
	public String GetTablename() {
		return this.Tablename;
	}

	//设置title
	public void AddTitle(String title){
		Title.add(title);
	}
	

	//设置titles
	public void SetTitle(List<String> titles) {
		Title=titles;
	}
	
	//修改title
	public void ModTitle(String title,int index) {
		Title.set(index, title);
	}
	
	//获得title
	public List<String> GetTitle(){
		return Title;
	}

	//设置表容量
	public Integer getPartCapacity() {
		return partCapacity;
	}

	public void setPartCapacity(int maxCapacity) {
		partCapacity = maxCapacity;
	}
	
	//增加一个主键
	public void addprimarykey(String key) {
		primarykey.add(key);
	}

	
	//获得主键
	public List<String> getPrimarykey() {
		return primarykey;
	}
	

}
