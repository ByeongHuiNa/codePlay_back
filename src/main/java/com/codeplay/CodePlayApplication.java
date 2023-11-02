package com.codeplay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class CodePlayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodePlayApplication.class, args);
	}

}
