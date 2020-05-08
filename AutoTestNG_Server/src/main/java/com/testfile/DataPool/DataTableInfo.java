package com.testfile.DataPool;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

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
	 *   count：
	 *   }
	 * }
	 * */
	private Map<String,Map<String,String>> partinfo;
	
	//表字段
	private List<String> titles;
	
	//表主键
	private List<String> primarykey;
	
	int count=0;
	public int used_Partcapacity() {
		count=0;
		partinfo.forEach((k,v)->{
			v.forEach((k2,v2)->{
				if(k2.equals("count")) {
					count=count+Integer.valueOf(v2);
				}
			});
		});
		return count;
	}
	
	/**
	 * 获取当前的分区号
	 * @return
	 */
	public int get_CurrentPart() {
		
		return partinfo.size()==1?1:Integer.valueOf(partinfo.entrySet().stream().reduce((c,n)->{
			if(Integer.valueOf(c.getValue().get("index"))>Integer.valueOf(n.getValue().get("index")))
			{
				return c;
			}else {
				return n;
			}
				
		}).get().getValue().get("index"));
			
		

	}
	/**
	 * 获得当前分区数量
	 * @return 
	 * @return 
	 * @return
	 */
	public int get_CurrentPartCount() {
		int cur=get_CurrentPart();
		for(Entry<String, Map<String,String>> e:partinfo.entrySet()) {
			if(Integer.valueOf(e.getValue().get("index"))==cur) {
				return Integer.valueOf(e.getValue().get("count"));
			}
		}
		return 1;
	}

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

	
	@Test
	public void test() {
		Map<String,Map<String,String>> map=new HashMap<String, Map<String,String>>();
		
		Map<String,String> x=new HashMap<String, String>();
		
		map.put("3", x);
		map.put("1", x);
		map.put("2", x);
		
	
	Object o=Stream.of(map).reduce((cur,next)->{
		System.out.println(cur);
		return cur;
	    
	}).get();
		System.out.println(o);
		
		
		
		
	}
	@Override
	public String toString() {
		return "DataTableInfo [id=" + id + ", tablename=" + tablename + ", partcapacity=" + partcapacity
				+ ", datacount=" + datacount + ", partinfo=" + partinfo + ", titles=" + titles + ", primarykey="
				+ primarykey + "]";
	}
    
}
