package com.project.uptime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UptimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(UptimeApplication.class, args);
	}

}
