package com.testfile.DataPool.DataOperation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testfile.DataPool.DataPoolConnector;
import com.testfile.DataPool.DataTableInfo;

@Service
public class DataInsert {
	
	@Autowired
	DataPoolConnector datapoolconnector;
	
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
    	
    	DataTableInfo dti=datapoolconnector.Get_TempTableInfo(TableName);
    	
		
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
    	
    	DataTableInfo dti=datapoolconnector.Get_TempTableInfo(TableName);
		
	}
}
