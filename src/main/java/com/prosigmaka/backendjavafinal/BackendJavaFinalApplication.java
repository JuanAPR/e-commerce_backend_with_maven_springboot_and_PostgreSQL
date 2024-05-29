package com.prosigmaka.backendjavafinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class BackendJavaFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendJavaFinalApplication.class, args);
	}

}
