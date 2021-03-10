package com.study.mobileback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@Configuration
public class MobileBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileBackEndApplication.class, args);
	}

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//multipart 5mb limit
		factory.setMaxFileSize(DataSize.ofBytes(1048576L * 5));
		factory.setMaxRequestSize(DataSize.ofBytes(1048576L * 5));
		return factory.createMultipartConfig();
	}


}
