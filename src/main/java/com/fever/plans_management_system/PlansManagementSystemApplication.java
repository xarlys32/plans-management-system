package com.fever.plans_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PlansManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlansManagementSystemApplication.class, args);
	}

}
