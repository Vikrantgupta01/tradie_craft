package com.viks.intuit.craft.tradie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CraftTradieApplication {

	public static void main(String[] args) {
		SpringApplication.run(CraftTradieApplication.class, args);
	}

}
