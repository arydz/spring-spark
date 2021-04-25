package com.arydz.sparkspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;

@SpringBootApplication(exclude = GsonAutoConfiguration.class)
public class SpringSparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSparkApplication.class, args);
	}
}
