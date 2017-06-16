package com.dongly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.dongly")
public class SpringServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringServletApplication.class, args);
	}
}
