package com.cjs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cjs.mapper")
public class SpringBoot2MybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2MybatisApplication.class, args);
	}
}
