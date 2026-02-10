package com.ludwigit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class LudwigitApplication {

	public static void main(String[] args) {
		SpringApplication.run(LudwigitApplication.class, args);
	}

}
