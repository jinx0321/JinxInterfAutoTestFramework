package com.mock.Service.URLDealService.CommonInter;

import java.io.File;
import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mock.Bean.Data.EnvVar;
import com.mock.Bean.Data.RootData;
import com.mock.Cache.CacheData;
import com.mock.Dao.XmlUtils.XmlUtils;

@Service("CacheOpImpl_Env")
public class CacheOpImpl_Env implements CacheOp<EnvVar> {
	@Autowired
	CacheData CacheData;
	
	@Autowired 
	XmlUtils XmlUtils;
	
    @Value("${DataSource.prefix}")
	String source;
    @Value("${DataSource.Env}")
	String typefile;
	
	
	@Override
	public EnvVar GetCache() {
		return CacheData.EnvVar;
	}
	
	

	@Override
	public void addUrldata(EnvVar data) {
		XmlUtils.UpdateXml(data,source,typefile,data.getClass());
		
	}

	@Override
	public void modUrldata(EnvVar data) {
		XmlUtils.UpdateXml(data,source,typefile,data.getClass());
		
	}

	@Override
	public void delUrldata(EnvVar data) {
		XmlUtils.UpdateXml(data,source,typefile,data.getClass());
		
	}

	@Override
	public void modreqdata(EnvVar data) {
		XmlUtils.UpdateXml(data,source,typefile,data.getClass());
		
	}

	@Override
	public void addreqdata(EnvVar data) {
		XmlUtils.UpdateXml(data,source,typefile,data.getClass());
		
	}

	@Override
	public void delreqdata(EnvVar data) {
		XmlUtils.UpdateXml(data,source,typefile,data.getClass());
		
	}

	@Override
	public void CacheDataLoad() {
	   CacheData.EnvVar=(EnvVar)XmlUtils.CacheDataLoad( source, typefile,EnvVar.class);
		
	}

	@Override
	public void CreateSource() {
		if(!new File(XmlUtils.getdir(source, typefile)).exists()) {
			XmlUtils.CreateXml(source, typefile, EnvVar.class);
		}
		
	}



	@Override
	public void UpdateDataDao() {
		XmlUtils.UpdateXml(CacheData.EnvVar,source,typefile,EnvVar.class);
		
	}




}
