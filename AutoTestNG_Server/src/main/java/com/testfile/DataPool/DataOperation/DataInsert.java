package com.testfile.DataPool.DataOperation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testfile.DataPool.DataPoolConnector;
import com.testfile.DataPool.DataTableInfo;
import com.testfile.DataPool.Partition.ParttitionUtils;

@Service
public class DataInsert {
	
	@Autowired
	DataPoolConnector datapoolconnector;
	
	@Autowired
	ParttitionUtils partutils;
	
	/**
	 * 插入数据操作表字段大小写会区分,且如果map中key对应不上表字段,则默认为空
	 * @param data
	 * @param TableName
	 */
	public synchronized void DataInsert(Map<String,String> data,String TableName) {
		
	}

	/**
	 * 采用悲观锁的方式
	 * @param data
	 * @param TableName
	 */
    public synchronized void DataInsert(List<String> data,String TableName){
    	//获取瞬时表信息
    	DataTableInfo dti=datapoolconnector.Get_TempTableInfo(TableName);
    	System.out.println(dti.getPartinfo());
    	//获取分区信息
    	Map<String,Object> partinfo=(Map<String, Object>) partutils.SingleSelect(dti);
    	
    	System.out.println(dti.getPartinfo());
    	
    	if(partinfo.get("is_create_new").equals("false")) {
    	    //如果不需要分区
    		//Insert_SingleData(dti,null,null);
    	}else {
    		
    	}
    	
    	
    	System.out.println(partutils.SingleSelect(dti));
		
	}
    
    /**
	 * 批量插入
	 * @param data
	 * @param TableName
	 */
	public synchronized void DataInsertBatch2(List<Map<String,String>> data,String TableName) {
		
	}
    
	/**
	 * 批量插入
	 * @param data
	 * @param TableName
	 */
    public synchronized void DataInsertBatch1(List<List<String>> data,String TableName){
    	
    	//获取瞬时表信息
    	DataTableInfo dti=datapoolconnector.Get_TempTableInfo(TableName);
    	//获取分区信息
    	Map<String,String> partinfo=partutils.MultipleSelect(dti, data.size());
		
	}
}
