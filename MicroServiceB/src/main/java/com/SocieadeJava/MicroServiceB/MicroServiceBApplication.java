package com.SocieadeJava.MicroServiceB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroServiceBApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceBApplication.class, args);
	}

}
