package com.example.aeroporti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//public class AeroportiApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(AeroportiApplication.class, args);
//	}
//
//}

@SpringBootApplication
@ServletComponentScan
public class AeroportiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AeroportiApplication.class, args);
	}

	// Necessario per WAR deployment
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AeroportiApplication.class);
	}
}
