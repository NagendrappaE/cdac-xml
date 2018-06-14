package com.evolvus.cda.cdacesign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.evolvus")
public class CdacEsignApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdacEsignApplication.class, args);
	}
}
