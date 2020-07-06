package com.mock.Service.URLDealService.CommonInter;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mock.Bean.Data.CacheData;
import com.mock.Bean.Data.RootData;

@Service("CacheOpImpl")
public class CacheOpImpl_Xml implements CacheOp<RootData> {

	@Override
	public RootData GetCache() {
		return CacheData.RootData;
	}

	@Override
	public void addUrldata(RootData data) {
		CacheData.XmlUtils.UpdateXml(data);
		
	}

	@Override
	public void modUrldata(RootData data) {
		CacheData.XmlUtils.UpdateXml(data);
		
	}

	@Override
	public void delUrldata(RootData data) {
		CacheData.XmlUtils.UpdateXml(data);
		
	}

	@Override
	public void modreqdata(RootData data) {
		CacheData.XmlUtils.UpdateXml(data);
		
	}

	@Override
	public void addreqdata(RootData data) {
		CacheData.XmlUtils.UpdateXml(data);
		
	}

	@Override
	public void delreqdata(RootData data) {
		CacheData.XmlUtils.UpdateXml(data);
		
	}




}
