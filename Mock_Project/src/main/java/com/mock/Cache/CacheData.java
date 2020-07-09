package com.mock.Cache;

import org.springframework.stereotype.Component;

import com.mock.Bean.Data.EnvVar;
import com.mock.Bean.Data.RootData;
import com.mock.Dao.XmlUtils.XmlUtils;

@Component
public class CacheData {
	public  RootData RootData=new RootData();
	
	public EnvVar EnvVar=new EnvVar();
	
}
