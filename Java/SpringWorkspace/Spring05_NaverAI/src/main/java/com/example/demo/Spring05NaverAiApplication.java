package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo","com.multi.controller","com.multi"})
@MapperScan(basePackages = {"com.multi.mapper"})
public class Spring05NaverAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring05NaverAiApplication.class, args);
	}

}
