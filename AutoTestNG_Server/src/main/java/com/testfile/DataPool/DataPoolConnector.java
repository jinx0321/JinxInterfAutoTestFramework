package com.testfile.DataPool;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.testfile.DataPool.Create.DataCreatePoolTable;

/**
 * 数据池连接器
 * 底层与DataPoolBean连接所用
 * @author jinxh29224
 *
 */
@Component
public class DataPoolConnector {
	
	//默认数据池容量
	@Value("${DataPool.TableCapacity}")
	public String defaultCapacity;
	
	//xml操作工具
	@Autowired
	DataPoolXmlUtils datapoolxmlutils;
	
	
	/**
	 * 创建表
	 * @param datapooltable
	 */
	public void CreateTable(DataCreatePoolTable datapooltable){
		String uuid=UUID.randomUUID().toString().replaceAll("-", "");
		datapooltable.setId(uuid);
	    if(datapooltable.getPartCapacity()==null) {
	    	datapooltable.setPartCapacity(Integer.valueOf(defaultCapacity));
	    }
	    //是否创建表标志
	    boolean flag=true;
		if(datapooltable.GetTablename()==null||datapooltable.GetTablename().equals("")) {
			System.out.println("表名不能为空");
			flag=false;
		}
		
		if(datapooltable.GetTitle()==null||datapooltable.GetTitle().size()==0) {
			System.out.println("表title不能为空");
			flag=false;
		}
		
		if(flag) {
			System.out.println("正在创建表");
			datapoolxmlutils.createTable(datapooltable);
			System.out.println("创建表成功");
		}else {
			System.out.println("创建表失败");
		}
		
	}
	
	
	/**
	 * 获取瞬时表信息
	 * @return
	 */
	public DataTableInfo Get_TempTableInfo(String TableName){
		DataTableInfo dti=new DataTableInfo();
		datapoolxmlutils.Get_Table_Info(dti, TableName);
		return dti;
	} 
	
	
 
	
	@Test
	public void test() {
		String uuid=UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(uuid);
	}

}
