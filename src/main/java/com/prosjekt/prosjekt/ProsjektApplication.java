package com.prosjekt.prosjekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProsjektApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProsjektApplication.class, args);
	}

}
