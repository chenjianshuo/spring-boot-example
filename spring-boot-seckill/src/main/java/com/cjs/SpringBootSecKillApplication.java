package com.cjs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cjs.mapper")
public class SpringBootSecKillApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecKillApplication.class, args);
	}
}
