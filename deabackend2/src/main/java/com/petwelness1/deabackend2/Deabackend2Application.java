package com.petwelness1.deabackend2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class Deabackend2Application {

	public static void main(String[] args) {
		SpringApplication.run(Deabackend2Application.class, args);
	}

}
