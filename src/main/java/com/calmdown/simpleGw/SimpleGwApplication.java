package com.calmdown.simpleGw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy // AOP 추가
@SpringBootApplication
public class SimpleGwApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleGwApplication.class, args);
	}
}
