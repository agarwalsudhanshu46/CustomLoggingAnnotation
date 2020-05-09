package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.*")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CustomLoggingAnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomLoggingAnnotationApplication.class, args);
	}

}
