package com.mock.Service.URLDealService.CommonInter;

import org.springframework.stereotype.Service;

import com.mock.Bean.Data.CacheData;
import com.mock.Bean.Data.RootData;

@Service
public class DataUpdateImp implements DataUpdateInter{

	@Override
	public void addUrldata(RootData RootData) {
		CacheData.XmlUtils.UpdateXml(RootData);
		
	}

	@Override
	public void modUrldata(RootData RootData) {
		CacheData.XmlUtils.UpdateXml(RootData);
		
	}

	@Override
	public void delUrldata(RootData RootData) {
		CacheData.XmlUtils.UpdateXml(RootData);
		
	}

	@Override
	public void modreqdata(RootData RootData) {
		CacheData.XmlUtils.UpdateXml(RootData);
		
	}

	@Override
	public void addreqdata(RootData RootData) {
		CacheData.XmlUtils.UpdateXml(RootData);
		
	}

	@Override
	public void delreqdata(RootData RootData) {
		CacheData.XmlUtils.UpdateXml(RootData);
		
	}

}
