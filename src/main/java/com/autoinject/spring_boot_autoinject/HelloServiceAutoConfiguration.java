package com.autoinject.spring_boot_autoinject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HelloServiceProperties.class)
@ConditionalOnClass(HelloService.class)
@ConditionalOnProperty(prefix="hello",value="enabled", matchIfMissing=true)
public class HelloServiceAutoConfiguration {

	//When add this object into the method parameter, the object will be autowired automatically
	@Autowired
	private HelloServiceProperties helloServiceProperties;
	
	@Bean
	@ConditionalOnMissingBean(HelloService.class)
	public HelloService getHelloService() {
		HelloService helloService = new HelloService();
		helloService.setMsg(helloServiceProperties.getMsg());
		return helloService;
	}
}
