package com.usv.technotronus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TechnotronusApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnotronusApplication.class, args);
	}

}
