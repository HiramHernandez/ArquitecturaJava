package com.muebleria.polizas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PolizasApplication {

	String home(){
		return "Hello World";
	}

	public static void main(String[] args) {

		SpringApplication.run(PolizasApplication.class, args);
		System.out.println("Arrancando API REST Mueblería");
	}

}
