package com.mock;

import java.lang.reflect.Field;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.thymeleaf.spring5.expression.Fields;

import com.mock.Bean.Data.CacheData;
import com.mock.Dao.XmlUtils.XmlUtils;

@SpringBootApplication
public class MockProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context=SpringApplication.run(MockProjectApplication.class, args);
		
	}

}
