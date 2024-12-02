package com.SocieadeJava.MicroServiceA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.SocieadeJava.MicroServiceA.intraclient")
public class MicroServiceAApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceAApplication.class, args);
	}

}

