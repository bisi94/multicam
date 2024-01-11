package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo","com.team.controller","com.team"})
@MapperScan(basePackages = {"com.team.mapper"})
public class SpringTeamplTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTeamplTestApplication.class, args);
	}

}
