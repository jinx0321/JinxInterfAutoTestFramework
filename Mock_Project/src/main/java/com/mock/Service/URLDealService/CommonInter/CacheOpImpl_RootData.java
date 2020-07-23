package com.mock.Service.URLDealService.CommonInter;

import java.io.File;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mock.Bean.Data.EnvVar;
import com.mock.Bean.Data.RootData;
import com.mock.Bean.Data.UrlData;
import com.mock.Cache.CacheData;
import com.mock.Dao.XmlUtils.XmlUtils;

@Service("CacheOpImpl")
public class CacheOpImpl_RootData implements CacheOp<RootData> {
	@Autowired
	CacheData CacheData;
	
	@Autowired 
	XmlUtils XmlUtils;
	
    @Value("${DataSource.prefix}")
	String source;
    @Value("${DataSource.UrlMapping}")
	String typefile;
	
	
	@Override
	public RootData GetCache() {
		return CacheData.RootData;
	}

	@Override
	public void addUrldata(RootData data) {
		XmlUtils.UpdateXml(data,source,typefile,data.getClass());
		
	}

	@Override
	public void modUrldata(RootData data) {
		XmlUtils.UpdateXml(data,source,typefile,data.getClass());
		
	}

	@Override
	public void delUrldata(RootData data) {
		XmlUtils.UpdateXml(data,source,typefile,data.getClass());
		
	}

	@Override
	public void modreqdata(RootData data) {
		XmlUtils.UpdateXml(data,source,typefile,data.getClass());
		
	}

	@Override
	public void addreqdata(RootData data) {
		XmlUtils.UpdateXml(data,source,typefile,data.getClass());
		
	}

	@Override
	public void delreqdata(RootData data) {
		XmlUtils.UpdateXml(data,source,typefile,data.getClass());
		
	}

	@Override
	public void CacheDataLoad() {
	   CacheData.RootData=(RootData) XmlUtils.CacheDataLoad( source, typefile,RootData.class);
		
	}

	@Override
	public void CreateSource() {
		if(!new File(XmlUtils.getdir(source, typefile)).exists()){
			XmlUtils.CreateXml(source, typefile, RootData.class);
		}
		
	}

	@Override
	public void UpdateDataDao() {
		XmlUtils.UpdateXml(CacheData.RootData,source,typefile,RootData.class);
		
	}




}
