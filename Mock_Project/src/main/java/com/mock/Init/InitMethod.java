package com.mock.Init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mock.Bean.Data.CacheData;
import com.mock.Dao.XmlUtils.XmlUtils;

@Component
@Order(value = 2)
public class InitMethod implements CommandLineRunner {
	/**
	 * 初始化方法
	 */
	@Override
	public void run(String... args) throws Exception {

		try {
			CacheData.RootData = CacheData.XmlUtils.XmlRead();

		} catch (Exception e) {

			System.out.println("数据初始化失败");
			e.printStackTrace();
		}

	}

}
