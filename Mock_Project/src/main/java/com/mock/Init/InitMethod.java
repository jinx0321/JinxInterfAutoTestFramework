package com.mock.Init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.mock.Service.URLDealService.CommonInter.CacheOp;
import com.mock.Service.URLDealService.CommonInter.CacheOpImpl_RootData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mock.Bean.Data.EnvVar;
import com.mock.Bean.Data.RootData;
import com.mock.Bean.Proxy.Proxy;
import com.mock.Cache.CacheData;
import com.mock.Dao.XmlUtils.XmlUtils;

@Component
@Order(value = 2)	
public class InitMethod implements CommandLineRunner {
	
	@Autowired
	@Qualifier("CacheOpImpl")
	CacheOp<RootData> CacheOp;

	@Autowired
	@Qualifier("CacheOpImpl_Env")
	CacheOp<EnvVar> CacheOp_Env;
	
	
	

	/**
	 * 初始化方法
	 */
	@Override
	public void run(String... args) throws Exception {

		try {
			System.out.println("数据初源初始化_______________");
			CacheOp.CreateSource();
			CacheOp_Env.CreateSource();
			
			System.out.println("数据初始化_______________");
			CacheOp.CacheDataLoad();
			CacheOp_Env.CacheDataLoad();
			
		} catch (Exception e) {

			System.out.println("数据初始化失败_______________");
			e.printStackTrace();
		}

	}

}
