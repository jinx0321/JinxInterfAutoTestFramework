package com.mock.Dao.XmlUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mock.Bean.Data.CacheData;
import com.mock.Bean.Data.RootData;
import com.mock.Bean.Data.UrlData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class XmlUtils {
	{
		 getdir();
		
	}
	
	public String xmldir;
	
	public void UpdateCacheData() {
		CacheData.RootData=XmlRead();
	}
	
	
	    public RootData XmlRead() {
	        File file = new File(xmldir);
	        try {
	            JAXBContext context = JAXBContext.newInstance(RootData.class);
	            Unmarshaller unMar = context.createUnmarshaller();
	            RootData RootData = (RootData) unMar.unmarshal(file);
	            return RootData;
	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }
			return new RootData();
	    }

	    
	    public synchronized void UpdateXml(RootData rootdata) {
	    	 try {
	             JAXBContext context = JAXBContext.newInstance(RootData.class);
	             Marshaller mar = context.createMarshaller();
	             mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	             mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
	             Marshaller marshaller = context.createMarshaller();
                 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
                 FileWriter fw= new FileWriter(xmldir);
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

		public void getdir() {
			xmldir=System.getProperty("user.dir")+"/datasource/UrlMapping.xml";
			
		}
	}

