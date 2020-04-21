package com.testfile.DataPool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.testfile.DataPool.Create.DataCreatePoolTable;

/**
 * 数据池底层操作
 */
@Component
public class DataPoolXmlUtils {
	
	@Value("${DataPool.Dir}")
	public String datapooldir;
	
	
	/**
	 * 读取xml表
	 * @param Table
	 */
	public void ReadXml() {
		 try {
		     Document doc = null;
			 doc = DocumentHelper.parseText(datapooldir);
             Element root = doc.getRootElement(); // 获取根节点
          
			} catch (DocumentException e) {	
				e.printStackTrace();
			} 
	}
	
	
	/**
	 * 创建表信息
	 * @param datapooltable
	 */
	public void createTable(DataCreatePoolTable datapooltable) {
		//初始化表时初始化文件夹
		System.out.println("创建文件夹:"+datapooldir+DataPoolDict.Table);
		new File(datapooldir+DataPoolDict.Table+datapooltable.GetTablename()).mkdir();
		System.out.println("创建文件夹:"+datapooldir+DataPoolDict.Table+datapooltable.GetTablename()+DataPoolDict.part);
		new File(datapooldir+DataPoolDict.Table+datapooltable.GetTablename()+DataPoolDict.part).mkdir();
		//
		String tabledir=datapooldir+DataPoolDict.Table+datapooltable.GetTablename()+"\\"+datapooltable.GetTablename()+DataPoolDict.Info;
		Document doc = DocumentHelper.createDocument();

		//创建根节点
		Element datapool = doc.addElement("datapool");
		datapool.addAttribute("id", datapooltable.GetId());
		datapool.addAttribute("tablename", datapooltable.GetTablename());
		datapool.addAttribute("partcapacity", String.valueOf(datapooltable.getPartCapacity()));
		//创建子节点数据量

		Element datacount = datapool.addElement("datacount");
		datacount.setText("0");
		
		//创建分区信息

		Element partition = datapool.addElement("partition");
		//创建第一张分区
		Element part = partition.addElement("part");
		String partid=UUID.randomUUID().toString().replaceAll("-", "");
		part.addAttribute("id", partid);
		part.addAttribute("index", "1");
		createPartData(partid,1,datapooltable.GetTablename());
		//设置数据量为0
		part.setText("0");
		
		//创建表头信息

		Element titles = datapool.addElement("titles");
		for(int i=1;i<=datapooltable.GetTitle().size();i++) {
			Element title = titles.addElement("title");
			title.addAttribute("index",String.valueOf(i));
			title.setText(datapooltable.GetTitle().get(i-1));
		}
		//创建主键
	
		Element primarykey = datapool.addElement("primarykey");
		String primarykeystr="";
		for(int i=1;i<=datapooltable.getPrimarykey().size();i++) {
			primarykeystr=primarykeystr+datapooltable.getPrimarykey().get(i-1)+",";
		}
		primarykey.setText(primarykeystr.substring(0, primarykeystr.length()-1));
		
		UpdateXml(doc,tabledir);
	}
	
	/**
	 * 创建分区数据
	 * @param index
	 * @param Tablename
	 */
	public void createPartData(String partid,int index,String Tablename) {
		//创建分区时先创建文件夹
		System.out.println("创建文件夹:"+datapooldir+DataPoolDict.Table+Tablename+DataPoolDict.part+index);
		new File(datapooldir+DataPoolDict.Table+Tablename+DataPoolDict.part+index).mkdir();
		
		String partdir=datapooldir+DataPoolDict.Table+Tablename+DataPoolDict.part+index+"\\"+Tablename+DataPoolDict.Data;
		Document doc = DocumentHelper.createDocument();
		//创建根节点
		Element datapool = doc.addElement("datapool");
		datapool.addAttribute("id", partid);
		datapool.addAttribute("index", String.valueOf(index));
		datapool.addAttribute("tablename",Tablename);
		datapool.addAttribute("partitionno", String.valueOf(index)); 
		UpdateXml(doc,partdir);
	}
	
	/**
	 * 获取表信息
	 * @param dti
	 */
	public void Get_Table_Info(DataTableInfo dti,String tablename) {
		String tabledir=datapooldir+DataPoolDict.Table+tablename+"\\"+tablename+DataPoolDict.Info;
		System.out.println(tabledir);
		 try {
			 Document doc = null;
			 SAXReader saxReader = new SAXReader(false);
			 doc = saxReader.read(tabledir);
		     Element datapool = doc.getRootElement();
		     
		     //获取表主属性
		     dti.setId(datapool.attributeValue("id"));
		     dti.setTablename(datapool.attributeValue("tablename"));
		     dti.setPartcapacity(datapool.attributeValue("partcapacity"));
		  
		     
		     //获取表数据量
		     dti.setDatacount(Integer.valueOf(datapool.elementText("datacount")));
		     
		     //获取表分区信息
		    Element parts = datapool.element("partition");
		    Iterator<Element> partit=parts.elementIterator("part");
		    Map<String,Map<String,String>> partmap=new LinkedHashMap<String,Map<String,String>>();
		    while(partit.hasNext()){
		    	Element part=partit.next();
		    	Map<String,String> partinfo=new LinkedHashMap<String, String>();
		    	partinfo.put("id",part.attributeValue("id"));
		    	partinfo.put("count",part.getText());
		    	partmap.put(part.attributeValue("index"), partinfo);
		    	
		    }
		    Map<String, Map<String, String>> partmap2=new LinkedHashMap<String, Map<String,String>>();
			partmap.entrySet().stream()
		                      .sorted(Map.Entry.comparingByKey())
		                      .forEachOrdered(e -> partmap2.put(e.getKey(), e.getValue()));;

		    dti.setPartinfo(partmap2);
		    
		    //设置titles
		    Element titles = datapool.element("titles");
		    Map<Integer,String> titlemap=new LinkedHashMap<Integer, String>();
		    Iterator<Element> titleit=titles.elementIterator("title");
		    List<String> titlelist=new LinkedList<String>();
		    while(titleit.hasNext()){
		    	Element title=titleit.next();
		    	titlemap.put(Integer.valueOf(title.attributeValue("index")), title.getText());
		    }
		    titlemap.entrySet().stream()
		                       .sorted(Map.Entry.comparingByKey())
		                       .forEach(e->{
		                    	   titlelist.add(e.getValue());
		                       });
		    dti.setTitles(titlelist);
		    
		    //设置主键
		    String keys=datapool.element("primarykey").getText();
		    List<String> primarykey=new LinkedList<String>();
		    if(keys!=null||!keys.equals("")) {
		    	String[] keyss=keys.split(",");
		    	for(int i=0;i<keyss.length;i++) {
		    		primarykey.add(keyss[i]);
		    	}
		    }
		    dti.setPrimarykey(primarykey);
		           
		    
		} catch (DocumentException e) {
			e.printStackTrace();
		}
     
		
	}
	/**
	 * xml输出
	 * @param doc
	 * @param dir
	 */
	private void UpdateXml(Document doc,String dir) {
		System.out.println("创建xml文件:"+dir);
		if(new File(dir).exists()) {
			System.out.println("文件已存在");
		}else {
		  Writer out;
		  OutputFormat format = OutputFormat.createPrettyPrint();
		  format.setEncoding("utf-8");
	        try {
	            //创建一个输出流对象
	            out = new FileWriter(dir);
	            //创建一个dom4j创建xml的对象
	            XMLWriter writer = new XMLWriter(out,format);
	            //调用write方法将doc文档写到指定路径
	            writer.write(doc);
	            writer.close();
	            System.out.print("生成XML文件成功");
	        } catch (IOException e) {
	            System.out.print("生成XML文件失败");
	            e.printStackTrace();
	        }
		}
	}
	@Test
	public void test() {
		
	}
	

}
