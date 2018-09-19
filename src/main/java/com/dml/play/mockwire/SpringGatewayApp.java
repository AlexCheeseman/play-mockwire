package com.dml.play.mockwire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class SpringGatewayApp {

	@Bean
	public SimplePreFilter simpleFilter() {
		return new SimplePreFilter();
	}
	  
	public static void main(String[] args) {
		SpringApplication.run(SpringGatewayApp.class, args);
	}

}