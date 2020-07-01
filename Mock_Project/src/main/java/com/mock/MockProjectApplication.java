package com.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mock.Bean.Data.CacheData;
import com.mock.Dao.XmlUtils.XmlUtils;

@SpringBootApplication
public class MockProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockProjectApplication.class, args);
	}

}
