package com.mock.Service.URLDealService.CommonInter;

import com.mock.Bean.Data.RootData;

public interface CacheOp<T> {
	public T GetCache();
	public void CacheDataLoad();
	public void addUrldata(T data);
	public void modUrldata(T data);
	public void delUrldata(T data);
	public void modreqdata(T data);
	public void addreqdata(T data);
	public void delreqdata(T data);
	public void CreateSource();
	public void UpdateDataDao();

	


}
