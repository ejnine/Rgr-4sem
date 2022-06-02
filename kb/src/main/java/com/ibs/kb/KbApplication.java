package com.ibs.kb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.ibs.kb"})
@SpringBootApplication
public class KbApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(KbApplication.class, args);
	}
}
