package com.stefan.publish;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPublish {
	
	@Test
	public void publishService() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");
		System.in.read();
	}
	
}
