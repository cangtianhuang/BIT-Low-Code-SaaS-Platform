package com.example.top;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class TopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopApplication.class, args);
	}

}
