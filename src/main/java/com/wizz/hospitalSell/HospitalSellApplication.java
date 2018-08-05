package com.wizz.hospitalSell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("com.wizz.hospitalSell.domain.mapper")
public class HospitalSellApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalSellApplication.class, args);
	}
}
