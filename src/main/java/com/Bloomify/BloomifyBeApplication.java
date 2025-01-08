package com.Bloomify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BloomifyBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloomifyBeApplication.class, args);
	}

}
