package com.mock.Dao.XmlUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.mock.Bean.Data.RootData;
import com.mock.Bean.Data.UrlData;
import com.mock.Cache.CacheData;
import com.mock.Cache.CacheObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@Component
public class XmlUtils {
	
	
	public CacheObject CacheDataLoad( String source,String typefile,Class clazz) {
		return XmlRead(source, typefile,clazz);
	}
	
	
	    public CacheObject XmlRead(String source,String typefile,Class clazz) {
	        File file = new File(getdir(source,typefile));
	        try {
	        	System.out.println("this.class="+clazz.getName());
	            JAXBContext context = JAXBContext.newInstance(clazz);
	            Unmarshaller unMar = context.createUnmarshaller();
	            CacheObject RootData = (CacheObject) unMar.unmarshal(file);
	   
	            return RootData;
	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }
			return new CacheObject();
	    }

	    
	    public synchronized void UpdateXml(CacheObject rootdata,String source,String typefile, Class clazz) {
                 
	 
	    	 try {
	             JAXBContext context = JAXBContext.newInstance(clazz);
	             Marshaller mar = context.createMarshaller();
	             mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	             mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
	             Marshaller marshaller = context.createMarshaller();
                 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
                 FileWriter fw= new FileWriter(getdir(source,typefile));
                 if (null != rootdata){
                     marshaller.marshal(rootdata, fw);
                 }
                 fw.flush();
                 fw.close();
	         } catch (JAXBException e) {
	             e.printStackTrace();
	         } catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    public void CreateXml(String source,String typefile,Class clazz) {
	   	 try {
             JAXBContext context = JAXBContext.newInstance(clazz);
             Marshaller mar = context.createMarshaller();
             mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
             mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
             Marshaller marshaller = context.createMarshaller();
             marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
             FileWriter fw= new FileWriter(getdir(source,typefile));
             marshaller.marshal(clazz.newInstance(), fw);
             fw.flush();
             fw.close();
         } catch (Exception e) {
             e.printStackTrace();
         } 
	    	
	    }

		public String getdir(String source,String typefile) {
			return System.getProperty("user.dir")+"/"+source+"/"+typefile;
			
		}
	}

