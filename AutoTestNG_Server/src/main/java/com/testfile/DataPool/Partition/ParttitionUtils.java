package com.testfile.DataPool.Partition;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
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
	@Value("${DataPool.TableCapacity}")
	private String defaultcount;
	
	/**
	 * 单条数据分区
	 * [{
	 *    index:num
	 *    count:num
	 *    liststart:num
	 *    listend:num
	 * 
	 * }]
	 * return：
	 * @param dti
	 */
	public PartInfo SingleSelect(DataTableInfo dti) {
		int cap=Integer.valueOf(dti.getPartcapacity());
		int used=dti.used_Partcapacity();
		PartInfo pi=new PartInfo();
		if(used==0) {
			System.out.println(dti.getTablename()+"的容量已经用完");
			pi.setIs_insert(false);
		}else {
			pi.setIs_insert(true);
			Map<String,String> localpart=new LinkedHashMap<String, String>(); 
			if(dti.get_CurrentPartCount()==Integer.valueOf(defaultcount)) {
				localpart.put("index", String.valueOf(dti.get_CurrentPart()+1));
				pi.setIs_part(true);
			}else {
				localpart.put("index", String.valueOf(dti.get_CurrentPart()));
				pi.setIs_part(false);
			}
			
			localpart.put("count", String.valueOf(1));
			
			List<Map<String,String>> list=new LinkedList<Map<String,String>>();
			
			pi.setPartlocal(list);
		}
		return pi;
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
