package com.testfile.DataPool.Partition;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.testfile.DataPool.DataTableInfo;

/**
 * 分区工具
 * 
 * @author jinxh29224
 *
 */
@Component
public class ParttitionUtils {
	
	/**
	 * 单条数据分区
	 * 
	 * return：
	 * {
	 *    is_create_new:true|false, --是否需要创建新分区
	 *    addcount  :  , --新增数量
	 *    addpartno :  {[1,2,3]}新增分区号
	 *    addpartinfo :{
	 *                   partid : count ,--分区新增数量
	 *                   new : count
	 *                 }
	 * }
	 * @param dti
	 */
	public Map<String,Object> SingleSelect(DataTableInfo dti) {
		int cap=Integer.valueOf(dti.getPartcapacity());
		Map<String,Object> partinfo=new HashMap<String, Object>();
		partinfo.put("is_create_new", "true");
	    //新增分区号
 	    List<String> addpartno=new LinkedList<String>();
 	    //分区数量信息
 	    Map<String,String> addpartinfo=new HashMap<String, String>();
 	    
 	    partinfo.put("addpartno", addpartno);
	    partinfo.put("addpartinfo", addpartinfo);
	    partinfo.put("addcount", "0");
	   
	    int addcount=0;
	    String lastindex = "";
	    for(Entry<String, Map<String,String>> e:dti.getPartinfo().entrySet()) {
	        if(Integer.valueOf(e.getValue().get("count"))<cap) {
	        	   partinfo.put("is_create_new", "false");
	        	   partinfo.put("addcount", String.valueOf(Integer.valueOf((String) partinfo.get("partinfo"))+1));
	        	   addpartinfo.put(e.getValue().get("id"), "1");
	           }
	           //获取最后区号
	           lastindex=e.getKey();
	    }
		
		if(partinfo.get("is_create_new").equals("true")) {
			addpartinfo.put("new", "1");
			addpartno.add(String.valueOf(Integer.valueOf(lastindex)+1));
		}
		return partinfo;
	}

	

	/**
	 * 多条数据分区
	 * 
	 * return：
	 * {
	 *    is_create_new:true|false, --是否需要创建新分区
	 *    part_loc :  , --数据落在哪个分区 
	 * 
	 * }
	 * @param dti
	 */
	public Map<String,String> MultipleSelect(DataTableInfo dti,int count) {
		int cap=Integer.valueOf(dti.getPartcapacity());
		Map<String,String> partinfo=new HashMap<String, String>();
		partinfo.put("is_create_new", "true");
		dti.getPartinfo().forEach((k,v)->{
           if(Integer.valueOf(v.get("count"))<cap) {
        	   int currcount=Integer.valueOf(v.get("count"));
        	   //当前分区还剩下容量
        	   if((cap-currcount)>=count) {
        		   partinfo.put("is_create_new", "false");
            	   partinfo.put("part_loc",k);
        	   }
        	   //当前分区容量不足
        	   else{
        		   
        	   }
        	  
           }
		});
		return partinfo;
	}

}
